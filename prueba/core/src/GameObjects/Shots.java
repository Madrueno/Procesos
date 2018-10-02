package GameObjects;

import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Shots {

    Vector2 vel;
    Vector2 position; //Creo un vector2, que cogera la posicion desde donde sale la bala que será la misma de cada nave
    int width;
    int height;
    private Rectangle2D rec; //Para comprobar impactos de las balas
    private int direction; //Si el disparo va hacia arriba (0) o hacia abajo (1)
    private boolean isActive;
    //float tiempo; Habrá que crear algo para que dispare cada cierto tiempo, posiblemente con un random Time



    public Shots(Vector2 p, Vector2 v, int width, int height){ //POR AHORA SON LOS DISPAROS DE LAS NAVES NO DEL PLAYERSHIP
        this.position = p; //Pongo la posición de la nave
        this.vel=v; //le doy a la bala la velocidad de la nave
        this.width = width;
        this.height = height;
        this.rec=new Rectangle2D()
        }

    }

    public void update(float delta) { //delta será la posición "y" del PlayerShip
        position.y= delta;

    }

    public void setPosition (Vector2 p) {
        this.position = p;
    }
    public void setVelocity (Vector2 v) {
        this.vel = v;
    }
}
