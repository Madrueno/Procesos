package GameWorld;

import com.badlogic.gdx.Gdx;

import java.awt.Rectangle;

import GameObjects.PlayerShip;

public class GameWorld {

    private PlayerShip playerShip;

    public GameWorld(float x, float y) {
        playerShip = new PlayerShip(50, 150, 25, 25);
    }

    public void update(float delta) {
        playerShip.update(delta);
    }

    public PlayerShip getPlayerShip() {
        return playerShip;

    }
}