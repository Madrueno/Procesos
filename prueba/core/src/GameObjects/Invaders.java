package GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.Random;


public class Invaders {

    Vector2 velocity;
    Vector2 position;
    Vector2 acceleration;
    int width;
    int height;
    boolean alive;
    Rectangle hitbox;
    Shots shots;

    public Invaders(float x, float y, int width, int height, boolean alive) {
        position = new Vector2(x, y);
        velocity = new Vector2(35, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.alive=alive;
        this.shots = new Shots(this.position,1);
        //hitbox = new Rectangle(0, 0, 1000, 1000);
        hitbox = new Rectangle(x, y, 15, 15);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void kill(){
        this.alive=false;
    }

    public void updateHitbox(){
        hitbox.x=this.position.x;
        hitbox.y=this.position.y;
        hitbox.width=this.width;
        hitbox.height=this.height;
    }

    public void update(float delta, int signo) {

        //Actualizar hitbox
        updateHitbox();


        velocity.set(velocity.x*signo, velocity.y);

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        Random generator = new Random();
        int randomNumber=generator.nextInt(1000);
        if (randomNumber==1)
            this.shots.shoot(this.position,1);
            this.shots.update();

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
}
