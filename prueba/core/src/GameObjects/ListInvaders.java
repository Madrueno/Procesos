package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import Screens.GameScreen;

public class ListInvaders {
    static ArrayList<Invaders> invadersAlive;
    static int nuevos = 0;

    public ListInvaders(){
        this.invadersAlive = new ArrayList<Invaders>();

        this.invadersAlive.add(new Invaders(30,10,15,15, true));
        this.invadersAlive.add(new Invaders(45,10,15,15, true));
        this.invadersAlive.add(new Invaders(60,10,15,15, true));
        this.invadersAlive.add(new Invaders(75,10,15,15, true));
        this.invadersAlive.add(new Invaders(90,10,15,15, true));
    }

    public ArrayList<Invaders> getArmy(){
        return invadersAlive;
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
