package GameObjects;


import com.badlogic.gdx.math.Vector2;


import java.awt.geom.Rectangle2D;

public class PlayerShip {
    private static Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private static int width;
    private static int height;
    private float screenWidth;
    private float screenHeight;
    private Shots shots;


    public PlayerShip(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.screenWidth=x;
        this.screenHeight= y;
        position = new Vector2(x/2, 140);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        shots= new Shots(position,4);

    }
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        if (position.x > this.screenWidth-width) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = this.screenWidth-width;
        }

        if (position.x < 0) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = 0;
        }

        if(this.shots.isActive()){
            this.shots.update(position.x);
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
    }

    public void setLeft(){
        position.x = position.x - 3;
    }

    public void setRight(){
        position.x = position.x + 3;
    }

    public static float getX() {
        return position.x;
    }

    public static float getY() {
        return position.y;
    }

    public static Vector2 getPosition() {
        return position;
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public Shots getShots() {
        return shots;
    }
}
