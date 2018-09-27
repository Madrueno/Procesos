package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLoader {


    public static void load() {

    }

    public static ImageButton buttonLeft(float x, float y){
        Texture myTexture = new Texture(Gdx.files.internal("data/left.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonRight(float x, float y){
        Texture myTexture = new Texture(Gdx.files.internal("data/right.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonShoot(float x, float y){
        Texture myTexture = new Texture(Gdx.files.internal("data/pokeball.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static void dispose() {

    }

}
