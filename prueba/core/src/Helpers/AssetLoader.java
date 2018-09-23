package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass;

    public static TextureRegion ship;


    public static void load() {
        texture = new Texture(Gdx.files.internal("data/pikachu.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        ship = new TextureRegion(texture, 153, 0, 17, 12);
        ship.flip(false, true);


    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }
}
