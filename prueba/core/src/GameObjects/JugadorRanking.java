package GameObjects;

public class JugadorRanking {
    private String name;
    private int Score;
    private String rutaImagen;

    public JugadorRanking(String name, int Score){
        this.name=name;
        this.Score=Score;
    }

    public int getScore() {
        return Score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        Score = score;
    }
}
