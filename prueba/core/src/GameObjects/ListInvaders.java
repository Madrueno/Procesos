package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import Screens.GameScreen;

import static java.lang.StrictMath.abs;

public class ListInvaders {
    static ArrayList<Invaders> invadersAlive;
    static int nuevos = 0;
    static int bajada=1;
    Invaders superEnemy = new Invaders(10,0,15,15, false);

    public ListInvaders(){
        this.invadersAlive = new ArrayList<Invaders>();
        this.invadersAlive.add(new Invaders(10,10,15,15, true));
        this.invadersAlive.add(new Invaders(10,25,15,15, true));
        this.invadersAlive.add(new Invaders(10,40,15,15, true));
        this.invadersAlive.add(new Invaders(30,10,15,15, true));
        this.invadersAlive.add(new Invaders(30,25,15,15, true));
        this.invadersAlive.add(new Invaders(30,40,15,15, true));
        this.invadersAlive.add(new Invaders(50,10,15,15, true));
        this.invadersAlive.add(new Invaders(50,25,15,15, true));
        this.invadersAlive.add(new Invaders(50,40,15,15, true));
        this.invadersAlive.add(new Invaders(70,10,15,15, true));
        this.invadersAlive.add(new Invaders(70,25,15,15, true));
        this.invadersAlive.add(new Invaders(70,40,15,15, true));
        /*
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/18,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/30,4*Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/18,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/10,4*Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/18,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(Gdx.graphics.getWidth()/6,4*Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(7*Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(7*Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/18,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        this.invadersAlive.add(new Invaders(7*Gdx.graphics.getWidth()/30,4*Gdx.graphics.getHeight()/45,Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/30, true));
        */
    }

    public void setSuperEnemy(){
        Invaders enemy = new Invaders(10,1,15,15, true);
        this.superEnemy = enemy;
    }

    public Invaders getSuperEnemy(){
        return superEnemy;
    }

    public void update() {
        bajada++;
        for (int i=0; i<invadersAlive.size(); i++){
            invadersAlive.get(i).getVelocity().x=abs((int) invadersAlive.get(i).getVelocity().x)+1;
            invadersAlive.get(i).getPosition().y+=bajada;
            invadersAlive.get(i).updateHitbox();
            invadersAlive.get(i).shots.update(); //No me lo borreis pls

        }

    }

    public ArrayList<Invaders> getArmy(){
        return invadersAlive;
    }

    public void kill(int i){
        this.getArmy().remove(i);
    }

    public void newInvader(float time){
        int seconds = (int) time;
        int nuevos=invadersAlive.size() - 12;

        if (seconds%10==0 && seconds!=0 && nuevos<time/3){
            setSuperEnemy();
        }
        deleteSuperEnemy();
    }

    public void deleteSuperEnemy(){
        if (superEnemy.getX()>200){    //Cambiar por tamano pantalla (mira posicion superenemy)
            superEnemy.kill();
        }

    }
}
