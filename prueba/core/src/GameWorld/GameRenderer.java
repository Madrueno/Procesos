package GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.SpaceInvaders;

import GameObjects.Invaders;
import GameObjects.JugadorRanking;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
import GameObjects.Ranking;
import GameObjects.Shots;
import GameObjects.ObstacleGroups;
import GameObjects.Obstacle;
import GameObjects.older13;
import Helpers.AssetLoader;


public class GameRenderer {

    private GameWorld myWorld;
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

    private older13 myOld;
    public static boolean gameover = false;
    public int gameoverIterator=0;
    private boolean nono =false;

    private boolean pressed=false;
    private boolean rankingPulsado=false;

    private ArrayList<ArrayList<Obstacle>> obstacles;

    public static ListInvaders invadersAlive;


    public static ObstacleGroups obstacleActive;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;
        playerShip= world.getPlayerShip();
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.soundShot= Gdx.audio.newSound(Gdx.files.getFileHandle("data/las.mp3",FileType.Internal));
        this.soundGameOver= Gdx.audio.newSound(Gdx.files.getFileHandle("data/go.mp3",FileType.Internal));
        this.musicEsp=Gdx.audio.newMusic(Gdx.files.getFileHandle("data/Music2.mp3",FileType.Internal));
        this.musicEsp.play();
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

    }


    public void buttons(final PlayerShip playerShip){
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ImageButton buttonLeft = AssetLoader.buttonLeft(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20 );
        stage.addActor(buttonLeft);

        ImageButton buttonRight = AssetLoader.buttonRight(7*Gdx.graphics.getWidth()/20 , Gdx.graphics.getHeight()/20 );
        stage.addActor(buttonRight);

        ImageButton buttonUp = AssetLoader.buttonUp(4*Gdx.graphics.getWidth()/20 , Gdx.graphics.getWidth()/5);
        stage.addActor(buttonUp);

        ImageButton buttonDown = AssetLoader.buttonDown(4*Gdx.graphics.getWidth()/20 , Gdx.graphics.getWidth()/20 -15);
        stage.addActor(buttonDown);

        ImageButton buttonShoot = AssetLoader.buttonShoot(15*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonShoot);

        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonRight", "Boton izquierdo pulsado");
                playerShip.setLeft();
                return true;
            }});

        buttonRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonRight", "Boton derecho pulsado");
                playerShip.setRight();
                return true;
            }});

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
                playerShip.getShots().add(new Shots(new Vector2(playerShip.getPosition().x+playerShip.getHitbox().width/2-5,playerShip.getPosition().y+20), 0, playerShip.getScreenHeight()));
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
        font.draw(batcher, "¿Eres mayor de 13 años? " , 10, 120);
     //   font.draw(batcher, "de 13 años? " , 25, 90);

        batcher.end();





        Stage stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        TextButton buttonYes = AssetLoader.buttonYes("Si",0,0  );
        buttonYes.setBounds(Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight()/10 +40, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
        buttonYes.getLabel().setFontScale((float) Math.min(buttonYes.getWidth()/35, buttonYes.getHeight()/35));

        TextButton buttonNo = AssetLoader.buttonNo("No", 0, 0);
        buttonNo.setBounds(5*Gdx.graphics.getWidth()/9, Gdx.graphics.getHeight()/10 +40, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/7);
        buttonNo.getLabel().setFontScale((float) Math.min(buttonNo.getWidth()/35, buttonNo.getHeight()/35));

        buttonYes.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myOld.setOld(true);
                return true;
            }});
        buttonNo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myOld.setOld(false);
                nono=true;
                return true;
            }});


        stage.addActor(buttonYes);
        stage.addActor(buttonNo);

        stage.act();
        stage.draw();

    }

    public void gameOver(float runTime, PlayerShip playerShip, Ranking ranking){
        //soundGameOver.loop();
        if(gameoverIterator==0) {
            int punt = playerShip.getScore();
            ranking.add(punt, SpaceInvaders.getName(),null);
        }
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.95f, 0.95f);
        font.draw(batcher, "Your final score: " + String.valueOf(playerShip.getScore()), 8, 150);

        batcher.end();
        Stage stageGameOv = new Stage();
        Gdx.input.setInputProcessor(stageGameOv);

        buttonTrofeo(stageGameOv);

        if ((playerShip.getScore())>=500) {
            TextButton buttonRetry = AssetLoader.buttonYes("Retry", Gdx.graphics.getWidth() / 20 + 75, 2 * Gdx.graphics.getHeight() / 20 - 25);
            buttonRetry.getLabel().setFontScale(Gdx.graphics.getWidth() / 140);

            buttonRetry.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    //myWorld.getPlayerShip().setLives(1);
                    //myWorld.getPlayerShip().setScore(0);
                    //myWorld.getInvadersArmy().setBajada(1);
                    myWorld.restPlay();
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


    public void winner(float runTime, PlayerShip playerShip, Ranking ranking){
        if(gameoverIterator==0) {
            int punt = playerShip.getScore();
            ranking.add(punt, SpaceInvaders.getName(),null);
        }
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

        if ((playerShip.getScore())>=500) {
            TextButton buttonRetry = AssetLoader.buttonYes("Retry", Gdx.graphics.getWidth() / 20 + 75, 2 * Gdx.graphics.getHeight() / 20 - 25);
            buttonRetry.getLabel().setFontScale(Gdx.graphics.getWidth() / 140);

            buttonRetry.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    gameover = false;
                    myWorld.getPlayerShip().setLives(1);
                    myWorld.getPlayerShip().setScore(0);
                    myWorld.restPlay();
                    return true;
                }
            });
        }
        stageGameOv.act();
        stageGameOv.draw();
    }
    public void buttonTrofeo(Stage stage){

        ImageButton buttonTrofeo = AssetLoader.buttonTrofeo(15*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonTrofeo);

        buttonTrofeo.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonTrofeo", "Boton Trofeo pulsado");
                if (!rankingPulsado)
                    rankingPulsado = true;
                else
                    rankingPulsado = false;
                return true;
            }});

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


    public void ranking(Ranking ranking){

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureRanking1, 3, -30, 128, 128);
        batcher.draw(AssetLoader.textureRanking2, 3, 20, 128, 128);


        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.70f, 0.70f);
        for (int i=0; i<5; i++){
            font.draw(batcher, i+1 + " . " + ranking.getRanking().get(i).getName()+" : "+ranking.getRanking().get(i).getScore(), 20, 120+(i*15));

        }

        batcher.end();
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

       buttonTrofeo(stage);

        stage.act();
        stage.draw();

    }


    public void render(float runTime) {
        Ranking ranking = myWorld.getRanking();
        myOld = (myWorld.getOlder());
        cambioMusica(runTime);
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
                gameOver(runTime, playerShip, ranking);
                gameoverIterator++;
                if (rankingPulsado){
                    ranking(ranking);
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
                    batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);
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
                    winner(runTime, playerShip, ranking);
                    gameoverIterator++;
                    if (rankingPulsado){
                        ranking(ranking);
                    }
                }
            }
        }
    }

}