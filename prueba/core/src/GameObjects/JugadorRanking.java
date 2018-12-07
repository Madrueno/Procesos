package GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class JugadorRanking {
    private String name;
    private int Score;
    private TextureRegion foto;

    public JugadorRanking(String name, int Score,TextureRegion t){
        this.name=name;
        this.Score=Score;
        this.foto=t;
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

    public void setFoto(TextureRegion foto) {
        this.foto = foto;
    }

    public TextureRegion getFoto() {
        return foto;
    }
}
