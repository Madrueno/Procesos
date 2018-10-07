package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetLoader {
    public static Texture bg;
    public static TextureRegion textureBg;
    public static Texture player;
    public static TextureRegion texturePlayer;
    public static Texture invader2;
    public static TextureRegion textureInvader2;
    public static Texture gameOver;
    public static TextureRegion textureGameOver;
    public static Texture laser;
    public static TextureRegion textureLaser;
    public static Texture obstacle;
    public static TextureRegion textureObstacle;
    public static Texture title;
    public static TextureRegion textureTitle;
    public static Texture win;
    public static TextureRegion textureWin;




    public static void loadBg() {
         bg = new Texture (Gdx.files.internal("data/bbb.png"));
         textureBg =new TextureRegion(bg);
         player = new Texture (Gdx.files.internal("data/nave2.png"));
         texturePlayer =new TextureRegion(player);
         invader2 = new Texture (Gdx.files.internal("data/alien1.png"));
         textureInvader2=new TextureRegion(invader2);
         gameOver = new Texture (Gdx.files.internal("data/gameOver.png"));
         textureGameOver =new TextureRegion(gameOver);
         textureGameOver.flip(false, true);
         laser= new Texture (Gdx.files.internal("data/disparo_sprite.png"));
         textureLaser = new TextureRegion(laser);
         obstacle= new Texture (Gdx.files.internal("data/square.gif"));
         textureObstacle = new TextureRegion(obstacle);
         title= new Texture (Gdx.files.internal("data/invadpt2.png"));
         textureTitle = new TextureRegion(title);
         win = new Texture (Gdx.files.internal("data/win.png"));
         textureWin =new TextureRegion(win);
         textureWin.flip(false, true);





    }

    public static ImageButton buttonLeft(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/left2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());
        pixmap1.drawPixmap(pixmap2,
                0, 0, pixmap2.getWidth(), pixmap2.getHeight(),
                0, 0, pixmap1.getWidth(), pixmap1.getHeight()
        );
        Texture myTexture = new Texture(pixmap1);

        pixmap2.dispose();
        pixmap1.dispose();

        //Texture myTexture = new Texture(Gdx.files.internal("data/left.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonRetry(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/retryIcon.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());
        pixmap1.drawPixmap(pixmap2,
                0, 0, pixmap2.getWidth(), pixmap2.getHeight(),
                0, 0, pixmap1.getWidth(), pixmap1.getHeight()
        );
        Texture myTexture = new Texture(pixmap1);

        pixmap2.dispose();
        pixmap1.dispose();

        //Texture myTexture = new Texture(Gdx.files.internal("data/left.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static TextButton buttonYes(String text, int x, int y){
        TextButton button = new TextButton(text, mySkin());
        button.setPosition(x, y);
        return button;
    }

    private static Skin mySkin(){
        Pixmap pixmap = new Pixmap(18*Gdx.graphics.getWidth()/20 -175, 3*Gdx.graphics.getHeight()/20 -10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GREEN);           // Color fondo
        pixmap.fill();

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("background", new Texture(pixmap));
        skin.add("default", font);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.up = skin.newDrawable("background", Color.WHITE);   //cambia un poco color fondo

        skin.add("default", textButtonStyle);
        return skin;
    }

    public static TextButton buttonNo(String text, int x, int y){
        TextButton button = new TextButton(text, mySkin2());
        button.setPosition(x, y);
        return button;
    }

    private static Skin mySkin2(){
        Pixmap pixmap = new Pixmap(18*Gdx.graphics.getWidth()/20 -175, 3*Gdx.graphics.getHeight()/20 -10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.RED);           // Color fondo
        pixmap.fill();

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();

        skin.add("background", new Texture(pixmap));
        skin.add("default", font);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.up = skin.newDrawable("background", Color.WHITE);   //cambia un poco color fondo

        skin.add("default", textButtonStyle);
        return skin;
    }


    public static ImageButton buttonRight(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/right2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5-10, Gdx.graphics.getWidth()/5-10, pixmap2.getFormat());
        pixmap1.drawPixmap(pixmap2,
                0, 0, pixmap2.getWidth(), pixmap2.getHeight(),
                0, 0, pixmap1.getWidth(), pixmap1.getHeight()
        );
        Texture myTexture = new Texture(pixmap1);


        pixmap2.dispose();
        pixmap1.dispose();

        //Texture myTexture = new Texture(Gdx.files.internal("data/right.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonShoot(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/disparo2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());
        pixmap1.drawPixmap(pixmap2,
                0, 0, pixmap2.getWidth(), pixmap2.getHeight(),
                0, 0, pixmap1.getWidth(), pixmap1.getHeight()
        );
        Texture myTexture = new Texture(pixmap1);

        pixmap2.dispose();
        pixmap1.dispose();

        //Texture myTexture = new Texture(Gdx.files.internal("data/pokeball.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(x, y);

        return button;
    }

    public static void dispose() {

    }

}
