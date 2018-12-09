package GameWorld;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.AndroidLauncher;
import com.mygdx.game.SpaceInvaders;
import com.mygdx.game.SpaceInvadersAnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.Obstacle;
import GameObjects.ObstacleGroups;
import GameObjects.PlayerShip;
import GameObjects.Ranking;
import GameObjects.Shots;
import GameObjects.older13;
import Helpers.AssetLoader;


public class GameRendererAnd {

    TextureRegion texture;

    private GameWorldAnd myWorld;
    private PlayerShip playerShip;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private Sound soundShot, soundGameOver;
    private Music musicEsp;
    private FileHandle file;
    private File f;
    private Skin skin;
    private TextField textField;
    private TextField.DefaultOnscreenKeyboard keyboard;
    private String name;
    private int midPointY;
    private int gameHeight;
    private static String dirRuta="MisImagenes/";
    private static String rutaFoto=dirRuta+"fotosSpaceInvaders/";

    private older13 myOld;
    public static boolean gameover = false;
    public static boolean jugadorGuardado=false;
    public int gameoverIterator=0;
    private boolean nono =false;

    private boolean pressed=false;
    private boolean rankingPulsado=false;
    Ranking ranking;

    private ArrayList<ArrayList<Obstacle>> obstacles;

    public static ListInvaders invadersAlive;


    public static ObstacleGroups obstacleActive;

    public GameRendererAnd(GameWorldAnd world, int gameHeight, int midPointY) {
        myWorld = world;
        playerShip= world.getPlayerShip();
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.soundShot= Gdx.audio.newSound(Gdx.files.getFileHandle("data/las.mp3",FileType.Internal));
        this.soundGameOver= Gdx.audio.newSound(Gdx.files.getFileHandle("data/go.mp3",FileType.Internal));
        this.musicEsp=Gdx.audio.newMusic(Gdx.files.getFileHandle("data/CARIBEMIXDOHMILDIECIHOCHO.mp3",FileType.Internal));
        this.musicEsp.play();
        this.musicEsp.setLooping(true);
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

    }

    public void cambioMusica (float time){
        int seconds = (int) time;

        if ((seconds%20)==0 && seconds!=0) {
            Random generator = new Random();
            int aux = generator.nextInt(100);
            int aux2 = aux % 10;
            //String auxString = Integer.toString(aux);
            //Character aux2 = auxString.charAt(auxString.length()-1);
            this.musicEsp.stop();

            if (aux2==2 || aux2==5 || aux2==8 || aux2==0){
                this.musicEsp = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/Countdown.mp3",FileType.Internal));
                this.musicEsp.play();
            }
            else if (aux2==3 || aux2==6 || aux2==9){
                this.musicEsp = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/Never.mp3",FileType.Internal));
                this.musicEsp.play();
            }
            else if (aux2==4 || aux2==7 || aux2==1){
                this.musicEsp = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/music3.mp3",FileType.Internal));
                this.musicEsp.play();
            }
        }

    }
    public void buttons(final PlayerShip playerShip){
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);





        ImageButton buttonLeft = AssetLoader.buttonLeft(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20 );
        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonRight", "Boton izquierdo pulsado");
                /* Si alguien intenta touchUp hay que des-comentar esto
                playerShip.setVelocity(-5,0); */
                playerShip.setLeft();
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                playerShip.setVelocity(0,0);
                super.touchUp(event,  x, y,  pointer,  button);
            }
        });
        stage.addActor(buttonLeft);

        ImageButton buttonRight = AssetLoader.buttonRight(7*Gdx.graphics.getWidth()/20 , Gdx.graphics.getHeight()/20 );
        buttonRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonRight", "Boton derecho pulsado");
                /* Si alguien intenta touchUp hay que des-comentar esto
                playerShip.setVelocity(5,0); */
                playerShip.setRight();
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                playerShip.setVelocity(0,0);
            }
        });
        stage.addActor(buttonRight);

        ImageButton buttonUp = AssetLoader.buttonUp(4*Gdx.graphics.getWidth()/20 , Gdx.graphics.getWidth()/5);
        stage.addActor(buttonUp);

        ImageButton buttonDown = AssetLoader.buttonDown(4*Gdx.graphics.getWidth()/20 , Gdx.graphics.getWidth()/20 -15);
        stage.addActor(buttonDown);

        ImageButton buttonShoot = AssetLoader.buttonShoot(15*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonShoot);





        buttonUp.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("1111");
                playerShip.setUp();
                return true;
            }});

        buttonDown.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("2222");
                playerShip.setDown();
                return true;
            }});


        buttonShoot.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonShoot", "Boton de disparo pulsado");
                playerShip.getShots().add(new Shots(new Vector2(PlayerShip.getPosition().x+playerShip.getHitbox().width/2-5, PlayerShip.getPosition().y+20), 0, playerShip.getScreenHeight()));
                soundShot.play();
                /*if(!shot.isActive()) {
                    shot.setPosition(new Vector2(playerShip.getPosition().x + playerShip.getWidth() / 2 - 5, playerShip.getPosition().y));
                    shot.shoot(shot.getPosition(), 0);
                    soundShot.play();
                    shot.setDeaths(0);
                }*/
                return true;
            }});

        stage.act();
        stage.draw();
        if (gameover){
            stage.dispose();
        }
    }

    public void invaders (SpriteBatch batcher, float time){
        invadersAlive.newInvader(time); //opcion de aumentar los invaders

        if (invadersAlive.getSuperEnemy().isAlive())        //superEnemy
            batcher.draw(AssetLoader.textureSuperEnemy, invadersAlive.getSuperEnemy().getX(), invadersAlive.getSuperEnemy().getY(), invadersAlive.getSuperEnemy().getWidth(), invadersAlive.getSuperEnemy().getHeight());
        for (int h=0; h<invadersAlive.getSuperEnemy().getShots().size();h++)
            batcher.draw(AssetLoader.textureLaser,invadersAlive.getSuperEnemy().getShots().get(h).getX(),invadersAlive.getSuperEnemy().getShots().get(h).getY(),invadersAlive.getSuperEnemy().getShots().get(h).getWidth(),invadersAlive.getSuperEnemy().getShots().get(h).getHeight());

        for (Invaders invader : invadersAlive.getArmy()) {
            if (invader.isAlive()) {
                if (invader.getPosition().y>140){
                    gameover = true;
                    batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);

                }
                else {
                    selectTextureInvader(invader);
                    if(invader.getShots()!=null)
                        batcher.draw(AssetLoader.textureLaser,invader.getShots().getX(),invader.getShots().getY(),invader.getShots().getWidth(),invader.getShots().getHeight());
                }
            }
        }
    }

    public void selectTextureInvader(Invaders invader){
        if (invader.getColor()==0)
            batcher.draw(AssetLoader.textureInvader2, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
        else if (invader.getColor()==1)
            batcher.draw(AssetLoader.textureInvader3, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
        else if (invader.getColor()==2)
            batcher.draw(AssetLoader.textureInvader4, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
        else if (invader.getColor()==3)
            batcher.draw(AssetLoader.textureInvader5, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
        else if (invader.getColor()==4)
            batcher.draw(AssetLoader.textureInvader6, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
    }

    public void obstacles (SpriteBatch batcher, ArrayList<ArrayList <Obstacle>> obstacles){

        for (ArrayList <Obstacle> obstacleGroup : obstacles){
            for (Obstacle obstacle : obstacleGroup) {
                if (obstacle.getStatus()) {
                    batcher.draw(AssetLoader.textureObstacle, obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

                }
            }
        }
        //invadersAlive.newInvader(time); opcion de aumentar los invaders
    }

    public void start(float runTime){


        soundGameOver.stop();
        batcher.begin();
        batcher.disableBlending();


        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);

        batcher.enableBlending();



        batcher.draw(AssetLoader.textureTitle, 0, 10, 140, 100);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.6f, 0.6f);
        font.draw(batcher, "¿Eres mayor de 13 años? " , 5, 120);
        //System.out.println(Gdx.graphics.getHeight()*3/10+"Width: \n"+ Gdx.graphics.getWidth()/10);
     //   font.draw(batcher, "de 13 años? " , 25, 90);

        batcher.end();





        Stage stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        TextButton buttonYes = AssetLoader.buttonYes("Si",0,0  );
        buttonYes.setBounds(Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight()/10 +40, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
        buttonYes.getLabel().setFontScale(Math.min(buttonYes.getWidth()/35, buttonYes.getHeight()/35));

        TextButton buttonNo = AssetLoader.buttonNo("No", 0, 0);
        buttonNo.setBounds(5*Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight()/10 +40, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
        buttonNo.getLabel().setFontScale(Math.min(buttonNo.getWidth()/35, buttonNo.getHeight()/35));

        buttonYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myOld.setOld(true);
                return true;
            }});
        buttonNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pulsado");
                myOld.setOld(false);
                nono=true;
                return true;
            }});


        stage.addActor(buttonYes);
        stage.addActor(buttonNo);

        stage.act();
        stage.draw();

    }

    public void gameOver(float runTime, PlayerShip playerShip){
        //soundGameOver.loop();
        /*if(gameoverIterator==0) {
            int punt = playerShip.getScore();
            ranking.add(punt, SpaceInvadersAnd.getName(),null);
        }*/
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();
        batcher.draw(AssetLoader.textureGameOver, 3, 10, 128, 128);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.95f, 0.95f);
        font.draw(batcher, "Your final score: " + String.valueOf(playerShip.getScore()), 5, 120);

        batcher.end();
        Stage stageGameOv = new Stage();
        Gdx.input.setInputProcessor(stageGameOv);

        buttonTrofeo(stageGameOv);
        buttonCamara(stageGameOv);



        if ((playerShip.getScore())>=500) {
            TextButton buttonRetry = AssetLoader.buttonYes("Retry", Gdx.graphics.getWidth()*1/10,  Gdx.graphics.getHeight()* 2/ 10, Gdx.graphics.getWidth()*8/10, Gdx.graphics.getHeight()*1/10+10);
            buttonRetry.getLabel().setFontScale(Gdx.graphics.getWidth() / 140);
            System.out.println(Gdx.graphics.getWidth()*1/10+"\nHeight:"+  Gdx.graphics.getHeight()* 2/ 10);



            buttonRetry.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    //myWorld.getPlayerShip().setLives(1);
                    //myWorld.getPlayerShip().setScore(0);
                    //myWorld.getInvadersArmy().setBajada(1);
                    myWorld.restPlay();
                    jugadorGuardado=false;
                    gameover = false;
                    gameoverIterator = 0;
                    return true;
                }
            });
            stageGameOv.addActor(buttonRetry);
        }
        stageGameOv.act();
        stageGameOv.draw();
    }


    /*
    public void gameOverNO(float runTime){
        soundGameOver.loop();
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.85f, 0.85f);
        font.draw(batcher, "Lo siento, necesitas" , 12, 130);

        BitmapFont font2 = new BitmapFont(true);
        font2.getData().setScale(0.85f, 0.85f);
        font2.draw(batcher, "ser mayor de 13 años " , 8, 150);

        batcher.end();

        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ImageButton buttonRetry = AssetLoader.buttonRetry(stage.getWidth()/20+100, stage.getHeight()/20);
        stage.addActor(buttonRetry);
        stage.addActor(buttonRetry);

        stage.act();
        stage.draw();

        buttonRetry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonRetry", "Boton retry pulsado");
                pressed=true;
                myWorld.setOld(new older13(false));
                nono=false;
                return true;
            }});
     */
        /*if (pressed){
            soundGameOver.pause();
            start(runTime);
        }*/

   // }


    public void winner(float runTime, PlayerShip playerShip){
        /*if(gameoverIterator==0) {
            int punt = playerShip.getScore();
            ranking.add(punt,SpaceInvadersAnd.getName(),null);
        }*/
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureWin, 3, 20, 128, 128);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.90f, 0.90f);
        font.draw(batcher, "Your final score: " + String.valueOf(playerShip.getScore()), 8, 150);




        batcher.end();

        Stage stageGameOv = new Stage();
        Gdx.input.setInputProcessor(stageGameOv);

        buttonTrofeo(stageGameOv);
        buttonCamara(stageGameOv);

        if ((playerShip.getScore())>=500) {
        TextButton buttonRetry = AssetLoader.buttonYes("Retry", Gdx.graphics.getWidth()*1/10,  Gdx.graphics.getHeight()* 2/ 10, Gdx.graphics.getWidth()*8/10, Gdx.graphics.getHeight()*1/10+10);
        buttonRetry.getLabel().setFontScale(Gdx.graphics.getWidth()/140);

        buttonRetry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameover=false;
                myWorld.getPlayerShip().setLives(1);
                myWorld.getPlayerShip().setScore(0);
                myWorld.restPlay();
                return true;
            }});
            stageGameOv.addActor(buttonRetry);
        }

        stageGameOv.act();
        stageGameOv.draw();
    }

    public void buttonCamara(Stage stage){

        ImageButton buttonCamara = AssetLoader.buttonCamara(5*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonCamara);

        buttonCamara.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonCamara", "Boton camara pulsado");

                    SpaceInvadersAnd.hacerFoto(myWorld.getPlayerShip());

                return true;
            }});

    }

    public void buttonTrofeo(Stage stage){

        ImageButton buttonTrofeo = AssetLoader.buttonTrofeo(15*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonTrofeo);

        buttonTrofeo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonTrofeo", "Boton Trofeo pulsado");
                if (!rankingPulsado) {
                    rankingPulsado = true;
                    if(!jugadorGuardado) {
                        if (playerShip.getPath()!=null) {
                            Texture t = new Texture(Gdx.files.absolute(playerShip.getPath()));
                            TextureRegion text = new TextureRegion(t);
                            ranking.add(playerShip.getScore(), playerShip.getNamePlayer(), text);
                            System.out.println(playerShip.getPath() + playerShip.getNamePlayer());
                            jugadorGuardado = true;
                        }
                    }
                }
                else
                    rankingPulsado = false;
                return true;
            }});

    }


    public void ranking(){

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureRanking1, 3, -30, 128, 128);
        batcher.draw(AssetLoader.textureRanking2, 3, 20, 128, 128);


        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.70f, 0.70f);
        for (int i=0; i<ranking.getRanking().size(); i++){
            font.draw(batcher, i+1 + " . " + ranking.getRanking().get(i).getName()+" : "+ranking.getRanking().get(i).getScore(), 20, 120+(i*20));
            if (ranking.getRanking().get(i).getFoto()!=null) {
                texture = new TextureRegion(ranking.getRanking().get(i).getFoto());
                int x = texture.getRegionWidth();
                int y = (int)( texture.getRegionHeight()/1.7);
                texture.setRegion(0, 0, x,  y);
                texture.flip(false, true);
                batcher.draw(texture, 100, 110 + (i * 20), 20, 20);
            }
        }

        batcher.end();
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

       buttonTrofeo(stage);
       buttonCamara(stage);

        stage.act();
        stage.draw();

    }


    public void render(float runTime) {
        ranking = myWorld.getRanking();
        myOld = (myWorld.getOlder());

        //cambioMusica(runTime);
        if (!myOld.getOld() && nono==false){
            if(nono ==true){
             // ranking(ranking);
                start(runTime);

            }else{
               //ranking(ranking);
               start(runTime);
           }

        }
        else {

            PlayerShip playerShip = myWorld.getPlayerShip();

            if (gameover){

                gameOver(runTime, playerShip);


                gameoverIterator++;
                if (rankingPulsado){
                    ranking();
                }

            }else {
                soundGameOver.stop();
                invadersAlive = myWorld.getInvadersArmy();

                obstacleActive = myWorld.getAllObstacle();

                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                shapeRenderer.setColor(Color.BLUE);
                shapeRenderer.rect(0, 0, 136, midPointY + 66);

                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(0, midPointY + 77, 136, 52);

                shapeRenderer.end();

                batcher.begin();
                batcher.disableBlending();
                batcher.draw(AssetLoader.textureBg, 0, 0, 200, 500);
                batcher.enableBlending();
                batcher.draw(AssetLoader.texturePlayer, PlayerShip.getX(), PlayerShip.getY(), PlayerShip.getWidth(), PlayerShip.getHeight());

                if (playerShip.getLives() == 0) {
                    gameover = true;
                    batcher.draw(AssetLoader.textureGameOver, Gdx.graphics.getWidth()*1/5, Gdx.graphics.getHeight()*2/10, Gdx.graphics.getWidth()*3/5, Gdx.graphics.getHeight()*3/10);
                }
                float time = runTime;
                invaders(batcher, time);
                obstacles = myWorld.setObstacles();
                obstacles(batcher, obstacles); //Para las barreras
                //Dibujar Balas activas
                for (int j=0; j<playerShip.getShots().size(); j++)
                    batcher.draw(AssetLoader.textureLaser, playerShip.getShots().get(j).getX(), playerShip.getShots().get(j).getY(), playerShip.getShots().get(j).getWidth(), playerShip.getShots().get(j).getHeight());

                //SCORE
                BitmapFont font = new BitmapFont(true);
                font.getData().setScale(0.6f, 0.6f);
                font.draw(batcher, "Score: " + String.valueOf(playerShip.getScore()), 5, 5);
                //


                batcher.end();


                buttons(playerShip); // Pone los botones

                //Mira si hemos exterminado por completo el ejercito
                if (myWorld.getInvadersDeath()==invadersAlive.getArmy().size()){
                    winner(runTime, playerShip);
                    gameoverIterator++;
                    if (rankingPulsado){
                        ranking();
                    }
                }
            }
        }
    }

}