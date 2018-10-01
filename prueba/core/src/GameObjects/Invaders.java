package GameObjects;

import com.badlogic.gdx.math.Vector2;
import GameObjects.Shots;
import java.util.ArrayList;

public class Invaders {

    Vector2 velocity;
    Vector2 position;
    Vector2 acceleration;
    int width;
    int height;
    boolean alive;
    Shots shots;

    public Invaders(float x, float y, int width, int height, boolean alive) {
        position = new Vector2(x, y);
        velocity = new Vector2(35, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        this.alive=alive;
        this.shots = new Shots(this.position, this.velocity, this.width, this.height);
    }

    public void update(float delta, int signo) {

        velocity.set(velocity.x*signo, velocity.y);

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));

        shots.setPosition(this.position); //Actualizamos la posicion de los disparos
        shots.setVelocity(this.velocity); // Actualizamos la velocidad de los disparos
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
