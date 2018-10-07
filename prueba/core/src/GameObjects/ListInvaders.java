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
        int nuevos=invadersAlive.size() - 3;
        int x, y;

        if (seconds%3==0 && seconds!=0 && nuevos<time/3){
            int size = invadersAlive.size() - 1;
            Invaders lastInvader = invadersAlive.get(size);
            if ((size+1)%5==0) {
                invadersAlive.add(new Invaders(invadersAlive.get(size-4).getPosition().x, invadersAlive.get(size).getPosition().y + 15, 15, 15, true));
            }
            else{
                invadersAlive.add(new Invaders(invadersAlive.get(size).getPosition().x+15, invadersAlive.get(size).getPosition().y, 15, 15, true));
            }

            nuevos++;
        }
    }
}
