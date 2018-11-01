package GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.abs;

public class SuperInvader {
    Vector2 velocity;
    Vector2 position;
    Vector2 acceleration;
    int width;
    int height;
    boolean alive;
    Rectangle hitbox;
    private ArrayList<Shots> shots;
    private float screenY;

    public SuperInvader(float x, float y, int width, int height, boolean alive, float screenY) {
        position = new Vector2(x, y);
        //velocity = new Vector2(Gdx.graphics.getWidth()/25, 0);
        velocity = new Vector2(40, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.alive=alive;
        this.screenY=screenY;
        this.shots = new ArrayList<Shots>();
        //hitbox = new Rectangle(0, 0, 1000, 1000);
        hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void kill(){
        this.alive=false;
    }

    public void updateHitbox() {
        if (this.isAlive()) {
            hitbox.x = this.position.x;
            hitbox.y = this.position.y;
            hitbox.width = this.width;
            hitbox.height = this.height;
        }
    }

    public void updateSuperEnemy(float delta, boolean old) {

        //Actualizar hitbox
        updateHitbox();


        velocity.set(abs(velocity.x), velocity.y);

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        if (isAlive() && old){
            Random generator2 = new Random();
            int randomNumber2 = generator2.nextInt(40);
            if (randomNumber2 == 1) {
                //m.out.println(randomNumber2);
                //this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - 4, this.getPosition().y));
                //this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - this.getShots().width/2, this.getPosition().y));
                //this.shots.shoot(this.shots.getPosition(), 1);
                shots.add(new Shots(new Vector2(this.position.x+this.width/2-5,this.position.y),1,screenY));
            }

        }
        if(old)
            for(int k=0;k<shots.size();k++)
                shots.get(k).update();
    }
    public boolean isAlive(){
        return this.alive;
    }

    public void removeShoot(int j){
        this.shots.remove(j);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }

    public ArrayList<Shots> getShots() {
        return shots;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
