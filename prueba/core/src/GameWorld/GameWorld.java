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
            if(invadersArmy.getArmy().get(i).getShots().isActive())
                invadersArmy.getArmy().get(i).getShots().update();
            invadersArmy.getArmy().get(i).update(delta, signo);
        }
        if (shotsPlayer.isActive())
            shotsPlayer.update();


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