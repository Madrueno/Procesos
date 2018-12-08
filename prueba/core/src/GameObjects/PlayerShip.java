package GameObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.awt.List;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import GameWorld.GameRenderer;

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
    private ArrayList <Shots> shots;

    private String namePlayer; //nombreJugador
    private String path;//rutaFoto
    private TextureRegion foto;

    public PlayerShip(float x, float y, int width, int height,String n) {

        this.width = width;
        this.height = height;
        this.move=0;
        this.screenWidth=x;
        this.screenHeight= y;
        position = new Vector2(x/2, screenHeight-(height+23));
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        hitbox = new Rectangle(x/2, screenHeight-(height+23), 3, 3);
        //hitbox = new Rectangle(0, 0, 0, 0);
        lives=1;
        score=0;
        this.shots=new ArrayList();
        namePlayer=n;
    }



    public void setNamePlayer(String name){
        namePlayer=name;
    }

    public String getNamePlayer(){
        return namePlayer;
    }

    public void setScore(int score){ this.score=score;}

    public void addScore(int score){
        if(!GameRenderer.gameover)
            this.score+=score;
    }

    public ArrayList<Shots> getShots() {
        return shots;
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
        /* Si alguien intenta touchUp hay que des-comentar esto
        position.x=position.x+velocity.x;
        */
        if (velocity.y > 200) {
            velocity.y = 200;
        }

        if (position.x > this.screenWidth-width) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = this.screenWidth-width;
        }

        if (position.y > this.screenHeight-height) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.y = this.screenHeight-height;
        }


        if (position.x < 0) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.x = 0;
        }

        if (position.y < 0) {     //CAMBIAR POR EL TAMANO DE LA PANTALLA
            position.y = 0;
        }

        if (move==0){

        }
        else if (move==1){
            position.x=position.x-velocity.x;
        }
        /*else{

        }*/
        Random generator = new Random();
        int randomNumber = generator.nextInt(1000);
        if (randomNumber == 1) {
            generator = new Random();
            float x = generator.nextFloat();
            generator = new Random();
            float y = generator.nextFloat();
            x= (float) (x/2.5);
            y= (float) (0.25+y/4);
            this.position.x = ((float) Gdx.graphics.getWidth() * x);
            this.position.y = ((float) Gdx.graphics.getHeight()*y);
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

    public void setUp(){
        position.y = position.y - 3;
    }

    public void setDown(){
        position.y = position.y + 3;
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

    public float getScreenWidth() {
        return screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public void setVelocity(int x, int y){
        this.velocity.x= x;
        this.velocity.y= y;
    }
    public String getPath() {
        return path;
    }
    public void setFoto(TextureRegion foto) {
        this.foto = foto;
    }

    public TextureRegion getFoto() {
        return foto;
    }
}
