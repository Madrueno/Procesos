package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.PlayerShip;

public class GameWorld {

    private PlayerShip playerShip;

    public GameWorld(int midPointY) {
        playerShip = new PlayerShip(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        playerShip.update(delta);
    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
}