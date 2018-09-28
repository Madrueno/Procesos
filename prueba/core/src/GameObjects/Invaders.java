package GameObjects;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Invaders {

    Vector2 velocity;
    Vector2 position;
    int width;
    int height;
    int x;
    int y;
    boolean alive;



    public Invaders(float x, float y, int width, int height, boolean alive) {
        position = new Vector2(x, y);
        velocity = new Vector2(10, 10);
        this.x=(int)x;
        this.y=(int)y;
        this.width = width;
        this.height = height;
        this.alive=true;
    }



    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean isAlive(){
        return this.alive;
    }
}
