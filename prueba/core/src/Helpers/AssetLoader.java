package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLoader {
    public static Texture bg;
    public static TextureRegion textureBg;
    public static Texture player;
    public static TextureRegion texturePlayer;
    public static Texture invader;
    public static TextureRegion textureInvader;
    public static Texture gameOver;
    public static TextureRegion textureGameOver;
    public static Texture laser;
    public static TextureRegion textureLaser;



    public static void loadBg() {
         bg = new Texture (Gdx.files.internal("data/bg.jpg"));
         textureBg =new TextureRegion(bg);
         player = new Texture (Gdx.files.internal("data/pikachu.png"));
         texturePlayer =new TextureRegion(player);
         invader = new Texture (Gdx.files.internal("data/unown.gif"));
         textureInvader =new TextureRegion(invader);
         gameOver = new Texture (Gdx.files.internal("data/gameOver.png"));
         textureGameOver =new TextureRegion(gameOver);
         textureGameOver.flip(false, true);
         laser= new Texture (Gdx.files.internal("data/disparo_sprite.png"));
         textureLaser = new TextureRegion(laser);

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
