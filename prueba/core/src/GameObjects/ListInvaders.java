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

        this.invadersAlive.add(new Invaders(15,10,25,25, true));
        this.invadersAlive.add(new Invaders(40,10,25,25, true));
        this.invadersAlive.add(new Invaders(65,10,25,25, true));
        this.invadersAlive.add(new Invaders(90,10,25,25, true));
    }

    public ArrayList<Invaders> getArmy(){
        return invadersAlive;
    }

    public void newInvader(float time){
        int seconds = (int) time;
        int nuevos=invadersAlive.size() - 3;
        int x, y;
        
        if (seconds%3==0 && seconds!=0 && nuevos<time/3){
            Invaders lastInvader = invadersAlive.get(invadersAlive.size() - 1);
            if (lastInvader.getX()==90){
                x=15;
                y=lastInvader.getY()+30;
            }
            else{
                x=lastInvader.getX()+25;
                y=lastInvader.getY();
            }

            invadersAlive.add(new Invaders(x, y, 25, 25, true));
            nuevos++;
        }
    }
}
