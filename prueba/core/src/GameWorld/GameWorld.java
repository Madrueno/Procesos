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
            if (playerShip.getHitbox().contains(invadersArmy.getArmy().get(i).getShots().getPosition())){
                System.out.println("player" + playerShip.getHitbox().x + playerShip.getHitbox().y);
                System.out.println("invader" + (invadersArmy.getArmy().get(i).getShots().getPosition()).x + (invadersArmy.getArmy().get(i).getShots().getPosition()).y);

                playerShip.setLives(0);
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