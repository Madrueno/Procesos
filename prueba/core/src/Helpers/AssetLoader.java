package Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class AssetLoader {
    public static Texture bg, title;
    public static TextureRegion textureBg, textureTitle;

    public static Texture player, superEnemy, obstacle, laser;
    public static TextureRegion texturePlayer, textureObstacle, textureLaser, textureSuperEnemy;

    public static Texture gameOver, win, ranking, ranking1, ranking2;
    public static TextureRegion textureGameOver, textureWin, textureRanking, textureRanking1, textureRanking2;

    public static Texture invader2, invader3, invader4, invader5, invader6;
    public static TextureRegion textureInvader2, textureInvader3, textureInvader4, textureInvader5, textureInvader6;

    public static TextureAtlas text;
    public static Texture text1;
    public static TextureRegion text2;

    /*public static ArrayList<Texture> list;
    public static ArrayList <TextureRegion> listRegion;*/

    public static void loadBg() {
         bg = new Texture (Gdx.files.internal("data/bbb.png"));
         textureBg =new TextureRegion(bg);
         player = new Texture (Gdx.files.internal("data/nave2.png"));
         texturePlayer =new TextureRegion(player);
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
         ranking = new Texture (Gdx.files.internal("data/ranking.png"));
         textureRanking =new TextureRegion(ranking);
         textureRanking.flip(false, true);
         ranking1 = new Texture (Gdx.files.internal("data/ranking1.png"));
         textureRanking1 =new TextureRegion(ranking1);
         textureRanking1.flip(false, true);
         ranking2 = new Texture (Gdx.files.internal("data/ranking2.png"));
         textureRanking2 =new TextureRegion(ranking2);
         textureRanking2.flip(false, true);
         superEnemy= new Texture (Gdx.files.internal("data/superEnemy.png"));
         textureSuperEnemy = new TextureRegion(superEnemy);
         textureSuperEnemy.flip(false, true);

         text1=new Texture(Gdx.files.internal("data/bg.jpg"));
         text2 = new TextureRegion(text1);
         text= new TextureAtlas();
         text.addRegion("text",text2);
         text.addRegion("text",text1,0,0,20,30);
         loadColorInvaders();


    }

    public static void loadColorInvaders() {
        invader2 = new Texture (Gdx.files.internal("data/alien1.png"));
        textureInvader2=new TextureRegion(invader2);

        invader3 = new Texture (Gdx.files.internal("data/invader2.png"));
        textureInvader3=new TextureRegion(invader3);

        invader4 = new Texture (Gdx.files.internal("data/invader3.png"));
        textureInvader4=new TextureRegion(invader4);

        invader5 = new Texture (Gdx.files.internal("data/invader4.png"));
        textureInvader5=new TextureRegion(invader5);

        invader6 = new Texture (Gdx.files.internal("data/invader5.png"));
        textureInvader6=new TextureRegion(invader6);
    }

    public static ImageButton buttonTrofeo(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/trofeo.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonCamara(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/Camara2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 +10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonLeft(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/left2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -20, Gdx.graphics.getWidth()/5 -20, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }
    public static ImageButton buttonRight(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/right2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5-20, Gdx.graphics.getWidth()/5-20, pixmap2.getFormat());
        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonUp(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/uppink.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -20, Gdx.graphics.getWidth()/5 -20, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static ImageButton buttonDown(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/downGreen.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -20,Gdx.graphics.getWidth()/5 -20, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }



    public static ImageButton buttonRetry(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/retryIcon.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static TextButton buttonYes(String text, int x, int y){
        TextButton button = new TextButton(text, mySkin());
        button.setPosition(x, y);
        return button;
    }
    public static TextButton buttonYes(String text, int x, int y, float width, float height){
        TextButton button = new TextButton(text, mySkin());
        button.setPosition(x, y);
        button.setWidth(width);
        button.setHeight(height);
        return button;
    }
    public static TextButton buttonNo(String text, int x, int y){
        TextButton button = new TextButton(text, mySkin2());
        button.setPosition(x, y);
        return button;
    }

    public static ImageButton buttonShoot(float x, float y){
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/disparo2.png"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        ImageButton button = new ImageButton(createDefaultDrawable(pixmap1, pixmap2));
        button.setPosition(x, y);

        return button;
    }

    public static TextField fieldText(float x, float y){
        //Skin skin = new Skin(Gdx.files.internal("data/right.png"));

        String name = new String();
        TextField textField = new TextField(name,textFieldStyle());
        textField.setPosition(x,y);
        textField.setDisabled(true);
        return textField;
    }

    public static TextField.TextFieldStyle textFieldStyle (){
        BitmapFont font = new BitmapFont();
        Pixmap pixmap2 = new Pixmap(Gdx.files.internal("data/bg.jpg"));
        Pixmap pixmap1 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        Pixmap pixmap4 = new Pixmap(Gdx.files.internal("data/cursor.JPG"));
        Pixmap pixmap3 = new Pixmap(Gdx.graphics.getWidth()/5 -10, Gdx.graphics.getWidth()/5 -10, pixmap2.getFormat());

        return new TextField.TextFieldStyle(font,Color.WHITE,createDefaultDrawable(pixmap3, pixmap4),null,createDefaultDrawable(pixmap1, pixmap2));
    }



    public static Skin mySkin(){
        Pixmap pixmap = new Pixmap(18*Gdx.graphics.getWidth()/20 -175, 3*Gdx.graphics.getHeight()/20 -10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GREEN);           // Color fondo
        pixmap.fill();
        return createDefaultSkin(pixmap);
    }

    private static Skin mySkin2(){
        Pixmap pixmap = new Pixmap(18*Gdx.graphics.getWidth()/20 -175, 3*Gdx.graphics.getHeight()/20 -10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.RED);           // Color fondo
        pixmap.fill();
        return createDefaultSkin(pixmap);
    }
    public static Skin mySkin3(){
        Pixmap pixmap = new Pixmap(18*Gdx.graphics.getWidth()/20 -175, 3*Gdx.graphics.getHeight()/20 -10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);           // Color fondo
        pixmap.fill();
        return createDefaultSkin(pixmap);
    }

    public static TextureRegionDrawable createDefaultDrawable(Pixmap pixmap1, Pixmap pixmap2){
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

        return myTexRegionDrawable;
    }

    public static Skin createDefaultSkin(Pixmap pixmap){
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

    public static void dispose() {

    }


}
