package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
public class GameWorld {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy = new ListInvaders();

    private int signo = 1;

    public GameWorld(float x, float y) {
        playerShip = new PlayerShip(x, y, 25, 25);

    }

    public void update(float delta) {
        playerShip.update(delta);
        for (int i=0; i<invadersArmy.getArmy().size(); i++){
            if ((i==3) && invadersArmy.getArmy().get(i).getPosition().x>115){
                signo = signo*-1;
                invadersArmy.update();
            }
            else if ((i==0) && invadersArmy.getArmy().get(i).getPosition().x<10){
                signo = signo*-1;
                invadersArmy.update();
            }
                invadersArmy.getArmy().get(i).update(delta, signo);
        }


    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
    public ListInvaders getInvadersArmy() {
        return invadersArmy;

    }
}