package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Rectangle2D;

public class Shots {

    float vel;
    Vector2 position; //Creo un vector2, que cogera la posicion desde donde sale la bala que será la misma de cada nave
    int width;
    int height;
    private Rectangle rec; //Para comprobar impactos de las balas
    private int direction; //Si el disparo va hacia arriba (0) o hacia abajo (1)
    private boolean isActive;
    private float screenY;
    private int deaths;
    //float tiempo; Habrá que crear algo para que dispare cada cierto tiempo, posiblemente con un random Time



    public Shots(Vector2 p,  int dir){ //POR AHORA SON LOS DISPAROS DE LAS NAVES NO DEL PLAYERSHIP
        this.position = new Vector2(p); //Pongo la posición de la nave
        this.vel=7; //poner velocidad constante para todas las balas
        this.width = 10;
        this.height = 15;
        //this.vel=Gdx.graphics.getHeight()/150; //poner velocidad constante para todas las balas
        //this.width = Gdx.graphics.getWidth()/25;
        //this.height = Gdx.graphics.getHeight()/30;
        this.direction=dir;
        this.rec=new Rectangle(p.x, p.y, width, height);
        this.isActive=false;
        deaths = 0;
        }

    public Shots(Vector2 p,  int dir, float y){ //POR AHORA SON LOS DISPAROS DE LAS NAVES NO DEL PLAYERSHIP
        this.position = new Vector2(p); //Pongo la posición de la nave
        this.vel=7; //poner velocidad constante para todas las balas
        this.width = 10;
        this.height = 15;
        //this.vel=Gdx.graphics.getHeight()/150; //poner velocidad constante para todas las balas
        //this.width = Gdx.graphics.getWidth()/25;
        //this.height = Gdx.graphics.getHeight()/30;
        this.direction=dir;
        this.rec=new Rectangle(p.x, p.y, width, height);
        this.isActive=false;
        deaths = 0;
        this.screenY=y;
    }

        public int getDeaths(){
            return deaths;
        }

        public void setDeaths(int deaths){
            this.deaths = deaths;
        }

    public void update() {
        if (this.direction==1)//Bala hacia abajo
            this.position.y=this.position.y+this.vel;
        else
            this.position.y=this.position.y-this.vel;
        this.rec.set(position.x, position.y, 10, 15);
        //this.rec.set(position.x, position.y, width, height);
        if((this.position.y<0)||(this.position.y>this.screenY)) {
            if (this.direction == 1)
                this.direction = 0;
            else
                this.direction = 1;
        }
    }

    /*public boolean isActive() {
        return isActive;
    }*/

    public void setActive() {
        isActive = true;
    }
    public void setInactive() {
        this.isActive=false;
    }

    public Vector2 getImpactedPoint() {
        if(this.direction==0)//Si la bala va pa arriba se va aumentando su posición sumando lo que mide
            this.position.y=this.position.y+this.height;
        return this.position;

    }

    public void setPosition (Vector2 p) {
        this.position = p;
    }
    public void setVelocity (Float v) {
        this.vel = v;
    }

    public Vector2 getPosition(){
        return position;
    }

    //Metodo de disparo
    public boolean shoot(Vector2 initialPosition, int dir) {
        if((!this.isActive)||(this.position.y<0)||(this.position.y>this.screenY)) {
            this.position=new Vector2(initialPosition);
            this.direction=dir;
            this.isActive=true;
        }

        return this.isActive;
    }

    public Rectangle getRec(){ return rec; }

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

    public void setScreenY(float screenY) {
        this.screenY = screenY;
    }

    public int getDirection() {
        return direction;
    }
}
