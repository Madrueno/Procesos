package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
import GameObjects.Shots;

public class GameWorld {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy = new ListInvaders();
    private Shots shotsPlayer;

    private int signo = 1;

    public GameWorld(float x, float y) {
        playerShip = new PlayerShip(x, y, 25, 25);
        shotsPlayer =new Shots(playerShip.getPosition(),0);
        shotsPlayer.setScreenY(y);
    }

    public void update(float delta) {
        playerShip.update(delta);
        for (int i=0; i<invadersArmy.getArmy().size(); i++){
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
                //System.out.println("Invader: " + invadersArmy.getArmy().get(i).getHitbox().x + invadersArmy.getArmy().get(i).getHitbox().y);
                //System.out.println("Pikachu: " + shotsPlayer.getPosition().x + shotsPlayer.getPosition().y);
                System.out.println(invadersArmy.getArmy().get(i).getHitbox().contains(shotsPlayer.getPosition()));
                if ((shotsPlayer.getDeaths()==0) && invadersArmy.getArmy().get(i).getHitbox().contains(shotsPlayer.getPosition())) {
                    shotsPlayer.setDeaths(1);
                    invadersArmy.getArmy().get(i).kill();
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
}