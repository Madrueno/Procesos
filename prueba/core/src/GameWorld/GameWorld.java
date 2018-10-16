package GameWorld;

import java.util.ArrayList;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.Obstacle;
import GameObjects.PlayerShip;
import GameObjects.Shots;
import GameObjects.ObstacleGroups;
import GameObjects.older13;

public class GameWorld {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy ;
    private older13 old;
    private Shots shotsPlayer;
    private ObstacleGroups allObstacle;
    private int invadersDeath=0;
    private float screenX,screenY;

    private int signo = 1;

    public GameWorld(float x, float y) {
        this.screenX=x;
        this.screenY=y;
        invadersArmy = new ListInvaders();
        old = new older13(false);
        playerShip = new PlayerShip(x, y, 25, 25);
        allObstacle= new ObstacleGroups(x,y);
        shotsPlayer =new Shots(playerShip.getPosition(),0);
        shotsPlayer.setScreenY(y);
    }

    public void update(float delta) {
        if (old.getOld()) {
            playerShip.update(delta);
            invadersArmy.getSuperEnemy().updateSuperEnemy(delta);

            ArrayList<ArrayList <Obstacle>> obstacles = setObstacles();
            ShootArmy(delta, obstacles);

            if (shotsPlayer.isActive()) {
                ShootPlayer(obstacles);
            }
        }
    }

    public void ShootPlayer(ArrayList<ArrayList <Obstacle>> obstacles){
        shotsPlayer.update();
        for (int i = 0; i < invadersArmy.getArmy().size(); i++) {
            //Aqui mato marcianitos
            if (invadersArmy.getArmy().get(i).isAlive())
                if (shotsPlayer.isActive() && (shotsPlayer.getDeaths() == 0) && invadersArmy.getArmy().get(i).getHitbox().overlaps(shotsPlayer.getRec())) {
                    updateDeathInvader(i);
                }
        }
        for (int i = 0; i < allObstacle.getObstacleActive1().size(); i++) {
            for (ArrayList<Obstacle> obs : obstacles) {
                if (obs.get(i).getStatus())
                    if (obs.get(i).getRec().overlaps(shotsPlayer.getRec())) {
                        obs.get(i).setStatus(false);
                        shotsPlayer.setInactive();
                        changeColor(invadersArmy);
                    }
            }
        }

    }

    public void ShootArmy(float delta, ArrayList<ArrayList <Obstacle>> obstacles){
        for (int i = 0; i < invadersArmy.getArmy().size() && playerShip.getLives() > 0; i++) { //comienzo for
            if (!invadersArmy.getArmy().get(i).isAlive())  //Mira si hemos exterminado por completo el ejercito
                invadersDeath++;
            else
                invadersDeath=0;

            if (invadersArmy.getArmy().get(i).getShots().isActive()) {
                if (playerShip.getHitbox().overlaps(invadersArmy.getArmy().get(i).getShots().getRec()) || playerShip.getHitbox().overlaps(invadersArmy.getSuperEnemy().getShots().getRec())) {
                    updateDeathPlayer(i);
                }
                for (int j = 0; j < allObstacle.getObstacleActive1().size(); j++) {
                   for (ArrayList <Obstacle> obs: obstacles){
                      checkObstacleShooted(obs, i, j);          //comprueba con cada barrera
                   }
                }
            }
            updateInvadersArmy(i, delta);
        } //fin for
    }

    public void updateDeathPlayer(int i){
        playerShip.minumLive();
        invadersArmy.getArmy().get(i).getShots().setInactive();
        invadersArmy.getSuperEnemy().getShots().setInactive();
    }

    public void updateDeathInvader(int i){
        shotsPlayer.setInactive();               //Pa no matar a toda la columna de marcianitos
        invadersArmy.kill(i);                   //Pongo al marcianito a no alive
        playerShip.setScore(100);               //Aumento puntuacion
    }

    public void updateInvadersArmy(int i, float delta){
        if ((i == 0) && (invadersArmy.getArmy().get(0).getPosition().x < 10) && (signo==-1)){
            signo = 1;
            invadersArmy.update();
        }
        invadersArmy.getArmy().get(i).update(delta, signo);
        if ((i == invadersArmy.getArmy().size() - 1) && (invadersArmy.getArmy().get(invadersArmy.getArmy().size() - 1).getPosition().x > 107) && (signo==1)){
            signo = -1;
            invadersArmy.update();
        }
    }

    public void checkObstacleShooted(ArrayList <Obstacle> obs, int i, int j){
        //  si es disparada se elimina:
        if (obs.get(j).getStatus())
            if (obs.get(j).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())
                    || obs.get(j).getRec().overlaps(invadersArmy.getSuperEnemy().getShots().getRec())) {
                obs.get(j).setStatus(false);
                invadersArmy.getArmy().get(i).getShots().setInactive();
                invadersArmy.getSuperEnemy().getShots().setInactive();
                changeColor(invadersArmy);
            }
        //  Si choca contra un invader se elimina:
        if (obs.get(j).getStatus())
            if (obs.get(j).getRec().overlaps(invadersArmy.getArmy().get(i).getHitbox())) {
                obs.get(j).setStatus(false);
            }
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
        if (invadersArmy.getArmy().get(0).getColor()==4)
            color=0;
        else
            color=invadersArmy.getArmy().get(0).getColor()+1;

        for(Invaders invader: invadersArmy.getArmy()){
            invader.setColor(color);
        }
    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
    public ListInvaders getInvadersArmy() {
        return invadersArmy;

    }

    public int getInvadersDeath(){
        return invadersDeath;
    }

    public older13 getOlder() {
        return old;

    }

    public Shots getShotsPlayer() {
        return shotsPlayer;
    }

    public ObstacleGroups getAllObstacle() {
        return allObstacle;
    }
    public void restPlay(){
        playerShip = new PlayerShip(this.screenX, this.screenY, 25, 25);
        allObstacle= new ObstacleGroups(this.screenX,this.screenY);
        shotsPlayer =new Shots(playerShip.getPosition(),0);
        shotsPlayer.setScreenY(this.screenY);
        invadersArmy = new ListInvaders();
    }

    public void setOld(older13 old) {
        this.old = old;
    }
}