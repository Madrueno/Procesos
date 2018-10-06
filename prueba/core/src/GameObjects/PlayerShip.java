package GameObjects;


import com.badlogic.gdx.math.Rectangle;
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
    private Rectangle hitbox;

    private int move;// 0 Stopped 1 izq 2 der

    private int lives;
    private int score;

    public PlayerShip(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.move=0;
        this.screenWidth=x;
        this.screenHeight= y;
        position = new Vector2(x/2, screenHeight-(height+23));
        velocity = new Vector2(1, 0);
        acceleration = new Vector2(0, 0);
        hitbox = new Rectangle(x/2, screenHeight-(height+23), 3, 3);
        //hitbox = new Rectangle(0, 0, 0, 0);
        lives=1;
        score=0;
    }

    public void setScore(int score){
        this.score+=score;
    }

    public int getScore(){
        return score;
    }

    public void setLives(int lives){
        this.lives=lives;
    }

    public void updateHitbox(){
        hitbox.x=this.position.x;
        hitbox.y=this.position.y;
        hitbox.width=this.width;
        hitbox.height=this.height;
    }


    public int getLives(){
        return lives;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void update(float delta) {

        //velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        if (position.x > this.screenWidth-width) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = this.screenWidth-width;
        }

        if (position.x < 0) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = 0;
        }

        if (move==0){

        }
        else if (move==1){
            position.x=position.x-velocity.x;
        }
        else{
            position.x=position.x+velocity.x;
        }
        //Actualizar hitbox
        updateHitbox();


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
    public void minumLive(){
        this.lives=this.lives-1;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
