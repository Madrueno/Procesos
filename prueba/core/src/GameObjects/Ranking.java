package GameObjects;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Ranking {
    Map<Integer, String> ranking = new TreeMap<Integer, String>(Collections.reverseOrder());

    public Ranking() {
        ranking.put(300, "Ruben");
        ranking.put(200, "Sandra");
        ranking.put(100, "Rodrigo");
        ranking.put(50, "David");
        ranking.put(20, "Natalia");
    }

    public String[] mejoresJugadores(){
        String[] mejores = new String[5];
        int cupo = 0;
        for (Integer i : ranking.keySet()) {
            if (cupo < 5) {
                mejores[cupo] = ranking.get(i);
                cupo++;
            }
        }
        return mejores;
    }

    public String[] getRanking(){
        String[] mejores = mejoresJugadores();
        return mejores;
    }
}
