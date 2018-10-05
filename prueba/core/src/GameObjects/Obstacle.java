package GameObjects;

import com.badlogic.gdx.math.*;

public class Obstacle { //Mi clase obstaculo es solo un pixel de cada barrera, despues habrá que crear la barrera entera
                        //Lo he dividido así porque lo veo más fácil para programar más tarde la destrucción de la barrera tanto
                        //por abajo como por arriba. Destruyendose así cada Obstacle poco a poco mediante status.

    Vector2 position;
    boolean status; //Status será true o false, true se mostrara, y false no se mostrará (cuando le disparen deja de mostrarse y de ser barrera)
    int width;
    int height;

    public Obstacle ( float x, float y){
        this.position= new Vector2(x,y);
        this.status=true;
        this.height=6;
        this.width=5;

    }
    public Obstacle (Vector2 v){
        this.position= v;
        this.status=true;
    }

    public Vector2 getPosition (){
        return this.position;
    }

    public boolean getStatus (){
        return this.status;
    }

    public void setStatus(boolean s){
        this.status = s;
    }

    public void setPosition (float x, float y){
        this.position.x= x;
        this.position.y=y;
    }

    public void setPositionV2(Vector2 v){
        this.position= v;
    }

    public int getWidth() { //Devolvemos la anchura
        return width;
    }

    public int getHeight() { //Devolvemos la altura
        return height;
    }

    public Rectangle getRec(){
        return new Rectangle(position.x, position.y, width, height);
    }


}
