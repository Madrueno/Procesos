package GameWorld;

import com.mygdx.game.SpaceInvaders;

import java.util.ArrayList;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.Obstacle;
import GameObjects.ObstacleGroups;
import GameObjects.PlayerShip;
import GameObjects.Ranking;
import GameObjects.older13;
import com.mygdx.game.SpaceInvadersAnd;
import android.os.Environment;

public class GameWorldAnd {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy ;
    private Ranking ranking;
    private older13 old;
    //private Shots shotsPlayer;
    private ObstacleGroups allObstacle;
    private int invadersDeath=0;
    private float screenX,screenY;
    private String name;

    private int signo = 1;

    public GameWorldAnd(float x, float y) {
        this.screenX=x;
        this.screenY=y;
        invadersArmy = new ListInvaders(y);
        name= SpaceInvadersAnd.askForName();
        old = new older13(false);
        playerShip = new PlayerShip(x, y, 25, 25,name);
        allObstacle= new ObstacleGroups(x,y);
        ranking = new Ranking();
    }

    public void update(float delta) {

            playerShip.update(delta);

            invadersArmy.getSuperEnemy().updateSuperEnemy(delta, old.getOld());

            ArrayList<ArrayList <Obstacle>> obstacles = setObstacles();
            for (int i = 0; i < invadersArmy.getArmy().size(); i++)
                updateInvadersArmy(i, delta);
            if (old.getOld()) {
                ShootArmy(delta, obstacles);
                for (int j=0; j<playerShip.getShots().size();j++){
                    ShootPlayer(obstacles,j);
                }
            }

    }

    public void ShootPlayer(ArrayList<ArrayList <Obstacle>> obstacles,int j){
            playerShip.getShots().get(j).update();
            boolean shootDeath = false;
        for (int i = 0; i < invadersArmy.getArmy().size(); i++) {
            //Aqui mato marcianitos
            //POr ahora solo cuenta las balas disparadas por el jugador habría que meter también las otras
            if (invadersArmy.getArmy().get(i).isAlive())
                if (invadersArmy.getArmy().get(i).getHitbox().overlaps(playerShip.getShots().get(j).getRec())) {
                    updateDeathInvader(i,j);
                    shootDeath=true;
                    break;
                }
                if (invadersArmy.getSuperEnemy().isAlive() &&
                        invadersArmy.getSuperEnemy().getHitbox().overlaps(playerShip.getShots().get(j).getRec())){
                    invadersArmy.getSuperEnemy().kill();
                    playerShip.addScore(1000);
                }
        }

        if ((!shootDeath)&&(playerShip.getHitbox().overlaps(playerShip.getShots().get(j).getRec()))&&(playerShip.getShots().get(j).getDirection()==1)){
                updateDeathPlayer();
                shootDeath=true;
        }
        if (!shootDeath) {
            for (int i = 0; i < allObstacle.getObstacleActive1().size(); i++) {
                for (ArrayList<Obstacle> obs : obstacles) {
                    if (obs.get(i).getStatus())
                        if (obs.get(i).getRec().overlaps(playerShip.getShots().get(j).getRec())) {
                            obs.get(i).setStatus(false);
                            playerShip.getShots().remove(j);
                            changeColor(invadersArmy);
                            shootDeath=true;
                            break;
                        }
                }
                if (shootDeath)
                    break;
            }
        }

    }

    public void ShootArmy(float delta, ArrayList<ArrayList <Obstacle>> obstacles){
        boolean shootDeath=false;
        for (int i = 0; i < invadersArmy.getArmy().size() && playerShip.getLives() > 0; i++) { //comienzo for
            if (!invadersArmy.getArmy().get(i).isAlive())  //Mira si hemos exterminado por completo el ejercito
                invadersDeath++;
            else
                invadersDeath=0;

            if (invadersArmy.getArmy().get(i).getShots()!=null) {
                if (playerShip.getHitbox().overlaps(invadersArmy.getArmy().get(i).getShots().getRec()) ) {
                    updateDeathPlayer(i);
                    invadersArmy.getArmy().get(i).removeShoot();
                    shootDeath=true;
                }
                for(int k=0; k<invadersArmy.getArmy().size()&&!shootDeath;k++) {
                    if (invadersArmy.getArmy().get(i).getShots().getDirection() == 0) {
                        if ((invadersArmy.getArmy().get(k).isAlive()) && (invadersArmy.getArmy().get(k).getHitbox().overlaps(invadersArmy.getArmy().get(i).getShots().getRec()))) {
                            invadersArmy.getArmy().get(i).removeShoot();
                            shootDeath = true;
                            updateDeathInvader(k);
                            i=i-1;
                        }
                        if (shootDeath)
                            break;
                    }
                }
                if (!shootDeath)
                    for (int j = 0; j < allObstacle.getObstacleActive1().size(); j++)
                       for (ArrayList <Obstacle> obs: obstacles)
                          checkObstacleShooted(obs, i, j);          //comprueba con cada barrera
            }
        }
        boolean shootDeathSuper=false;
        for (int j=0;j<invadersArmy.getSuperEnemy().getShots().size();j++){
            if(playerShip.getHitbox().overlaps(invadersArmy.getSuperEnemy().getShots().get(j).getRec())) {
                updateDeathPlayerSuper(j);
                shootDeathSuper=true;
            }
            for(int k=0; k<invadersArmy.getArmy().size()&&!shootDeathSuper;k++) {
                if (invadersArmy.getSuperEnemy().getShots().get(j).getDirection() == 0) {
                    if ((invadersArmy.getArmy().get(k).isAlive()) && (invadersArmy.getArmy().get(k).getHitbox().overlaps(invadersArmy.getSuperEnemy().getShots().get(j).getRec()))) {
                        shootDeathSuper = true;
                        updateDeathInvaderSuper(k,j);
                        j=j-1;
                    }
                    if (shootDeathSuper)
                        break;
                }
            }
            if (!shootDeathSuper)
                for (int h = 0; h < allObstacle.getObstacleActive1().size(); h++) {
                    for (ArrayList <Obstacle> obs: obstacles ){
                        checkObstacleShootedSuper(obs,j, h);          //comprueba con cada barrera
                        if (invadersArmy.getSuperEnemy().getShots().size()<=j) {
                            shootDeathSuper = true;
                            j=j-1;
                            break;
                        }
                    }
                    if(shootDeathSuper)
                        break;
                }
            //updateInvadersArmy(i, delta);
        } //fin for
    }

    public void updateDeathPlayer(int i){
        playerShip.minumLive();
        invadersArmy.getArmy().get(i).removeShoot();
    }
    public void updateDeathPlayer(){
        playerShip.minumLive();

    }

    public void updateDeathPlayerSuper(int i){
        playerShip.minumLive();
        invadersArmy.getSuperEnemy().removeShoot(i);
    }

    public void updateDeathInvader(int i,int j){
        playerShip.getShots().remove(j);               //Pa no matar a toda la columna de marcianitos
        invadersArmy.kill(i);                   //Pongo al marcianito a no alive
        if (playerShip.getLives()>0) {
            playerShip.addScore(100);           //Aumento puntuacion
        }

    }
    public void updateDeathInvaderSuper(int i,int j){
        invadersArmy.getSuperEnemy().getShots().remove(j);               //Pa no matar a toda la columna de marcianitos
        invadersArmy.kill(i);                   //Pongo al marcianito a no alive
    }
    public void updateDeathInvader(int i){

        invadersArmy.kill(i);                   //Pongo al marcianito a no alive

    }

    public void updateInvadersArmy(int i, float delta){
        if ((i == 0) && (invadersArmy.getArmy().get(0).getPosition().x < 10) && (signo==-1)){
            signo = 1;
            invadersArmy.update();
        }
        invadersArmy.getArmy().get(i).update(delta, signo, old.getOld( ));
        if ((i == invadersArmy.getArmy().size() - 1) && (invadersArmy.getArmy().get(invadersArmy.getArmy().size() - 1).getPosition().x > 107) && (signo==1)){
            signo = -1;
            invadersArmy.update();
        }
    }

    public void checkObstacleShooted(ArrayList <Obstacle> obs, int i, int j){
        //  si es disparada se elimina:
        if (obs.get(j).getStatus()) {
                if (invadersArmy.getArmy().get(i).getShots()!=null)
                    if (obs.get(j).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                        if (obs.get(j).getStatus()) {
                            obs.get(j).setStatus(false);
                            invadersArmy.getArmy().get(i).removeShoot();
                            changeColor(invadersArmy);
                        }
                    }
        }
        //  Si choca contra un invader se elimina:
    }

    public void checkObstacleShootedSuper(ArrayList <Obstacle> obs, int i, int j){
        //  si es disparada se elimina:
        if (obs.get(j).getStatus()) {
            if (invadersArmy.getSuperEnemy().getShots().get(i)!=null)
                if (obs.get(j).getRec().overlaps(invadersArmy.getSuperEnemy().getShots().get(i).getRec())){
                    if (obs.get(j).getStatus()) {
                        obs.get(j).setStatus(false);
                        changeColor(invadersArmy);
                        invadersArmy.getSuperEnemy().removeShoot(i);
                    }
                }
        }
        //  Si choca contra un invader se elimina:
    }

    public ArrayList<ArrayList <Obstacle>> setObstacles(){
        ArrayList<ArrayList <Obstacle>> obstacles = new ArrayList<ArrayList <Obstacle>>();
        obstacles.add(allObstacle.getObstacleActive1());
        obstacles.add(allObstacle.getObstacleActive2());
        obstacles.add(allObstacle.getObstacleActive3());
        obstacles.add(allObstacle.getObstacleActive4());
        return obstacles;
    }

    public void changeColor(ListInvaders invadersArmy){
        int color;
        if(invadersArmy.getArmy().size()>0) {
            if (invadersArmy.getArmy().get(0).getColor() == 4)
                color = 0;
            else
                color = invadersArmy.getArmy().get(0).getColor() + 1;

            for (Invaders invader : invadersArmy.getArmy()) {
                invader.setColor(color);
            }
        }
    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
    public ListInvaders getInvadersArmy() {
        return invadersArmy;

    }

    public Ranking getRanking(){
        return ranking;
    }

    public int getInvadersDeath(){
        return invadersDeath;
    }

    public older13 getOlder() {
        return old;

    }



    public ObstacleGroups getAllObstacle() {
        return allObstacle;
    }

    public void restPlay(){
        SpaceInvaders.setName(null);
        playerShip=null;
        invadersArmy=null;
        allObstacle=null;
        name= SpaceInvadersAnd.askForName();

        //AQUI hay que llamar a hacer la foto
        playerShip = new PlayerShip(this.screenX, this.screenY, 25, 25,name);
        allObstacle= new ObstacleGroups(this.screenX,this.screenY);
        invadersArmy = new ListInvaders(screenY);
    }

    public void setOld(older13 old) {
        this.old = old;
    }
}