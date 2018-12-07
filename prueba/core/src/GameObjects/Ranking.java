package GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    private LinkedList<JugadorRanking> ranking;


   public Ranking() {
       ranking=  new LinkedList<JugadorRanking>();
       ranking.addLast(new JugadorRanking( "David", 400,null));
       ranking.addLast(new JugadorRanking( "Ruben", 300,null));
       ranking.addLast(new JugadorRanking( "Sandra", 200,null));
       ranking.addLast(new JugadorRanking( "Rodrigo", 100,null));
    }
    public void add(int punt, String name, TextureRegion text){
       //if ((name==null)||(name.equals("")))
           //name="You";
       int i=0;
       while((ranking.get(i).getScore()>punt)&&i<ranking.size()-1){
           i++;
       }
       if(i==ranking.size()-1)
           i++;
       ranking.add(i,new JugadorRanking(name, punt,text));
   }

    public LinkedList<JugadorRanking> getRanking() {
        return ranking;
    }
}
