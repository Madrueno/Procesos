package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;

public class GameWorld {

    private PlayerShip playerShip;
    private ListInvaders invadersArmy = new ListInvaders();

    public GameWorld(float x, float y) {
        playerShip = new PlayerShip(50, 150, 25, 25);
    }

    public void update(float delta) {
        playerShip.update(delta);
        for (int i=0; i<invadersArmy.getArmy().size(); i++){
                invadersArmy.getArmy().get(i).update(delta);
        }

    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
    public ListInvaders getInvadersArmy() {
        return invadersArmy;

    }
}