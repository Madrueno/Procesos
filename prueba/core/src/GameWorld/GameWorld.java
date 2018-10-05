package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
import GameObjects.Shots;
import GameObjects.ObstacleGroups;

public class GameWorld {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy = new ListInvaders();
    private Shots shotsPlayer;
    private ObstacleGroups allObstacle;

    private int signo = 1;

    public GameWorld(float x, float y) {
        playerShip = new PlayerShip(x, y, 25, 25);
        allObstacle= new ObstacleGroups(x,y);
        shotsPlayer =new Shots(playerShip.getPosition(),0);
        shotsPlayer.setScreenY(y);
    }

    public void update(float delta) {
        playerShip.update(delta);
        for (int i=0; i<invadersArmy.getArmy().size(); i++){
            if(invadersArmy.getArmy().get(i).getShots()!=null) {
                if (playerShip.getHitbox().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                    System.out.println("player (" + playerShip.getHitbox().x + " , " + playerShip.getHitbox().y + " )");
                    System.out.println("invader" + (invadersArmy.getArmy().get(i).getShots().getPosition()).x + (invadersArmy.getArmy().get(i).getShots().getPosition()).y);

                    //playerShip.setLives(0);
                }

                for(int j=0; i<allObstacle.getObstacleActive1().size(); i++)
                    if(allObstacle.getObstacleActive1().get(i).getStatus())
                        if(allObstacle.getObstacleActive1().get(i).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                            allObstacle.getObstacleActive1().get(i).setStatus(false);
                            invadersArmy.getArmy().get(i).getShots().setInactive();
                        }
                for(int j=0; i<allObstacle.getObstacleActive2().size(); i++)
                    if(allObstacle.getObstacleActive2().get(i).getStatus())
                        if(allObstacle.getObstacleActive2().get(i).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                            allObstacle.getObstacleActive2().get(i).setStatus(false);
                            invadersArmy.getArmy().get(i).getShots().setInactive();
                        }
                for(int j=0; i<allObstacle.getObstacleActive3().size(); i++)
                    if(allObstacle.getObstacleActive3().get(i).getStatus())
                        if(allObstacle.getObstacleActive3().get(i).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                            allObstacle.getObstacleActive3().get(i).setStatus(false);
                            invadersArmy.getArmy().get(i).getShots().setInactive();
                        }
                for(int j=0; i<allObstacle.getObstacleActive4().size(); i++)
                    if(allObstacle.getObstacleActive4().get(i).getStatus())
                        if(allObstacle.getObstacleActive4().get(i).getRec().overlaps(invadersArmy.getArmy().get(i).getShots().getRec())) {
                            allObstacle.getObstacleActive4().get(i).setStatus(false);
                            invadersArmy.getArmy().get(i).getShots().setInactive();
                        }
            }
            if ((i==0) && invadersArmy.getArmy().get(i).getPosition().x>107){
                signo = signo*-1;
                invadersArmy.update();
            }
            else if ((i==1) && invadersArmy.getArmy().get(i).getPosition().x<10){
                signo = signo*-1;
                invadersArmy.update();
            }
            invadersArmy.getArmy().get(i).update(delta, signo);
        }
        if (shotsPlayer.isActive()) {
            shotsPlayer.update();
            for (int i=0; i<invadersArmy.getArmy().size(); i++){
                //Aqui mato marcianitos
                if(invadersArmy.getArmy().get(i).isAlive())
                    if (shotsPlayer.isActive()&&(shotsPlayer.getDeaths()==0) && invadersArmy.getArmy().get(i).getHitbox().overlaps(shotsPlayer.getRec())) {
                        shotsPlayer.setInactive();               //Pa no matar a toda la columna de marcianitos
                        invadersArmy.kill(i);                   //Pongo al marcianito a no alive
                        playerShip.setScore(100);               //Aumento puntuacion
                    }
            }
            for(int i=0; i<allObstacle.getObstacleActive1().size(); i++)
                if(allObstacle.getObstacleActive1().get(i).getStatus())
                    if(allObstacle.getObstacleActive1().get(i).getRec().overlaps(shotsPlayer.getRec())) {
                        allObstacle.getObstacleActive1().get(i).setStatus(false);
                        shotsPlayer.setInactive();
                    }
            for(int i=0; i<allObstacle.getObstacleActive2().size(); i++)
                if(allObstacle.getObstacleActive2().get(i).getStatus())
                    if(allObstacle.getObstacleActive2().get(i).getRec().overlaps(shotsPlayer.getRec())) {
                        allObstacle.getObstacleActive2().get(i).setStatus(false);
                        shotsPlayer.setInactive();
                    }
            for(int i=0; i<allObstacle.getObstacleActive3().size(); i++)
                if(allObstacle.getObstacleActive3().get(i).getStatus())
                    if(allObstacle.getObstacleActive3().get(i).getRec().overlaps(shotsPlayer.getRec())) {
                        allObstacle.getObstacleActive3().get(i).setStatus(false);
                        shotsPlayer.setInactive();
                    }
            for(int i=0; i<allObstacle.getObstacleActive4().size(); i++)
                if(allObstacle.getObstacleActive4().get(i).getStatus())
                    if(allObstacle.getObstacleActive4().get(i).getRec().overlaps(shotsPlayer.getRec())) {
                        allObstacle.getObstacleActive4().get(i).setStatus(false);
                        shotsPlayer.setInactive();
                    }
        }


    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
    public ListInvaders getInvadersArmy() {
        return invadersArmy;

    }

    public Shots getShotsPlayer() {
        return shotsPlayer;
    }

    public ObstacleGroups getAllObstacle() {
        return allObstacle;
    }
}