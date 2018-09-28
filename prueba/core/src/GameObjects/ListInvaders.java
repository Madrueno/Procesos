package GameObjects;

import java.util.ArrayList;

public class ListInvaders {
    static ArrayList<Invaders> invadersAlive;

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
}
