package GameObjects;


import com.badlogic.gdx.math.Vector2;


import java.awt.geom.Rectangle2D;

public class PlayerShip {
    private static Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // For handling bird rotation
    private static int width;
    private static int height;
    private Rectangle2D recPlayer;


    public PlayerShip(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x/2, 10);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(260, 0);

    }
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
        velocity.x = -140;
    }

    public static float getX() {
        return position.x;
    }

    public static float getY() {
        return position.y;
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }
}
