package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.Random;

import static java.lang.StrictMath.abs;


public class Invaders {

    Vector2 velocity;
    Vector2 position;
    Vector2 acceleration;
    int width;
    int height;
    boolean alive;
    Rectangle hitbox;
    Shots shots;
    int color;

    public Invaders(float x, float y, int width, int height, boolean alive) {
        position = new Vector2(x, y);
        //velocity = new Vector2(Gdx.graphics.getWidth()/25, 0);
        velocity = new Vector2(10, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.alive=alive;
        this.shots = new Shots(this.position,1);
        //hitbox = new Rectangle(0, 0, 1000, 1000);
        hitbox = new Rectangle(x, y, width, height);
        color=0;
    }

    public void setColor(int i){
        this.color=i;
    }

    public int getColor(){
        return color;
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

    public void update(float delta, int signo, boolean old) {

        //Actualizar hitbox
        updateHitbox();


        velocity.set(abs(velocity.x)*signo, velocity.y);

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        if (old) {
            Random generator = new Random();
            int randomNumber = generator.nextInt(600);
            if (randomNumber == 1) {
                this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - 4, this.getPosition().y));
                //this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - this.getShots().width/2, this.getPosition().y));
                this.shots.shoot(this.shots.getPosition(), 1);
            }
            this.shots.update();
        }
    }

    public void updateSuperEnemy(float delta) {

        //Actualizar hitbox
        updateHitbox();


        velocity.set(abs(velocity.x), velocity.y);

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        if (isAlive()){
            Random generator2 = new Random();
            int randomNumber2 = generator2.nextInt(20);
            if (randomNumber2 == 1) {
                //m.out.println(randomNumber2);
                this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - 4, this.getPosition().y));
                //this.shots.setPosition(new Vector2(this.position.x + (this.width / 2) - this.getShots().width/2, this.getPosition().y));
                this.shots.shoot(this.shots.getPosition(), 1);
            }
            this.shots.update();
        }
    }



   /* public void update(float delta) {
        if (position.x > 110) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = 110;
            velocity.set(velocity.x*-1, velocity.y);
        }

        if (position.x < 15) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = 15;
            velocity.set(velocity.x*-1, velocity.y);
        }

            velocity.add(acceleration.cpy().scl(delta));
            position.add(velocity.cpy().scl(delta));

    }/*
/*
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

    }*/

   public void setVelocity(Vector2 velocity){
       this.velocity=velocity;
   }

    public Shots getShots() {
        return shots;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public Vector2 getVelocity(){
        return this.velocity;
    } //Devuelve la posición

    public boolean isAlive(){
        return this.alive;
    }

    public float getX (){
        return this.position.x;
    }
}
