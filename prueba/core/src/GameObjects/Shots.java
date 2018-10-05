package GameObjects;

import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Shots {

    float vel;
    Vector2 position; //Creo un vector2, que cogera la posicion desde donde sale la bala que será la misma de cada nave
    int width;
    int height;
    private Rectangle2D rec; //Para comprobar impactos de las balas
    private int direction; //Si el disparo va hacia arriba (0) o hacia abajo (1)
    private boolean isActive;
    //float tiempo; Habrá que crear algo para que dispare cada cierto tiempo, posiblemente con un random Time



    public Shots(Vector2 p,  int dir){ //POR AHORA SON LOS DISPAROS DE LAS NAVES NO DEL PLAYERSHIP
        this.position = p; //Pongo la posición de la nave
        this.vel=20; //poner velocidad constante para todas las balas
        this.width = 10;
        //this.height = height;
        this.direction=dir;
        this.height = 6;
        //this.rec=new Rectangle2D.Float();
        this.isActive=false;
        }

    public void update() {
        if (this.direction==1)//Bala hacia abajo
            this.position.y=this.position.y+this.vel;
        else
            this.position.y=this.position.y-this.vel;
        //Actualizar rectangulo para impactos


    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive() {
        isActive = true;
    }
    public void setInactive() {
        this.isActive=false;
    }

    public Vector2 getImpactedPoint() {
        if(this.direction==0)//Si la bala va pa abajo se va aumentando su posición sumando lo que mide
            this.position.y=this.position.y+this.height;
        return this.position;

    }

    public void setPosition (Vector2 p) {
        this.position = p;
    }
    public void setVelocity (Float v) {
        this.vel = v;
    }

    //Metodo de disparo
    public boolean shoot(Vector2 initialPosition, int dir) {
        if(!this.isActive) {
            this.position=new Vector2(initialPosition);
            this.direction=dir;
            this.isActive=true;
        }
        return this.isActive;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX(){
        return this.position.x;
    }

    public float getY(){
        return this.position.y;
    }
}
