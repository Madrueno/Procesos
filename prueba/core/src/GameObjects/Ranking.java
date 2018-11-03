package GameObjects;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    Map<Integer, String> ranking;


   public Ranking() {
        ranking=  new TreeMap<Integer, String>(Collections.reverseOrder());
    }
    public void add(int punt,String name){
       ranking.put(punt,name);
    }

    public String[] mejoresJugadores(){
        String[] mejores = new String[5];
        int cupo = 0;
        for (Integer i : ranking.keySet()) {
            if (cupo < 5) {
                mejores[cupo] = ranking.get(i) + " : "+  i;
                cupo++;
            }
        }
        return mejores;
    }

    public void newScore(String name, int score){       //Metodo para introducir el nuevo score
        ranking.put(score, name);
    }

    public String[] getRanking(){
        String[] mejores = mejoresJugadores();
        return mejores;
    }
}
