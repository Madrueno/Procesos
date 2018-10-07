package GameWorld;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Files.FileType;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
import GameObjects.Shots;
import GameObjects.ObstacleGroups;
import GameObjects.Obstacle;
import GameObjects.older13;
import Helpers.AssetLoader;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private Sound soundShot, soundGameOver;
    private Music musicEsp;

    private int midPointY;
    private int gameHeight;

    private older13 myOld;
    private boolean gameover = false;
    private boolean nono =false;

    private boolean pressed=false;

    public static ListInvaders invadersAlive;
    public Shots shot;

    public static ObstacleGroups obstacleActive;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.soundShot= Gdx.audio.newSound(Gdx.files.getFileHandle("data/las.mp3",FileType.Internal));
        this.soundGameOver= Gdx.audio.newSound(Gdx.files.getFileHandle("data/go.mp3",FileType.Internal));
        this.musicEsp=Gdx.audio.newMusic(Gdx.files.getFileHandle("data/esp1.mp3",FileType.Internal));
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

        ImageButton buttonLeft = AssetLoader.buttonLeft(stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonLeft);

        ImageButton buttonRight = AssetLoader.buttonRight(7*stage.getWidth()/20 -17, stage.getHeight()/20 -15);
        stage.addActor(buttonRight);

        ImageButton buttonShoot = AssetLoader.buttonShoot(15*stage.getWidth()/20, stage.getHeight()/20 -15);
        stage.addActor(buttonShoot);

        // aqui va la funcionalidad de los botones
        /*buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonLeft", "Boton izquierdo pulsado");
                playerShip.setMove(1);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonLeft", "Boton izquierdo pulsado");
                playerShip.setMove(0);

            }
        });*/
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

        buttonShoot.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonShoot", "Boton de disparo pulsado");
                if(!shot.isActive()) {
                    shot.setPosition(new Vector2(playerShip.getPosition().x + playerShip.getWidth() / 2 - 5, playerShip.getPosition().y));
                    shot.shoot(shot.getPosition(), 0);
                    soundShot.play();
                    shot.setDeaths(0);
                }
                return true;
            }});

        stage.act();
        stage.draw();
        if (gameover){
            stage.dispose();
        }
    }

    public void invaders (SpriteBatch batcher, float time){

        //invadersAlive.newInvader(time); opcion de aumentar los invaders

        for (Invaders invader : invadersAlive.getArmy()) {
            if (invader.isAlive()) {
                if (invader.getPosition().y>140){
                    gameover = true;
                    batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);

                }
                else {
                        batcher.draw(AssetLoader.textureInvader2, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());

                    if(invader.getShots().isActive())
                        batcher.draw(AssetLoader.textureLaser,invader.getShots().getX(),invader.getShots().getY(),invader.getShots().getWidth(),invader.getShots().getHeight());
                }
            }
        }

    }

    public void obstacles (SpriteBatch batcher){

        //invadersAlive.newInvader(time); opcion de aumentar los invaders
        //Primera barrera
        for (Obstacle obstacle : obstacleActive.getObstacleActive1()) {
            if (obstacle.getStatus()) {
                batcher.draw(AssetLoader.textureObstacle, obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

            }
        }

        //Segunda Barrera
        for (Obstacle obstacle : obstacleActive.getObstacleActive2()) {
            if (obstacle.getStatus()) {
                batcher.draw(AssetLoader.textureObstacle, obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

            }
        }
        //Tercera barrera
        for (Obstacle obstacle : obstacleActive.getObstacleActive3()) {
            if (obstacle.getStatus()) {
                batcher.draw(AssetLoader.textureObstacle, obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

            }
        }
        //Cuarta barrera
        for (Obstacle obstacle : obstacleActive.getObstacleActive4()) {
            if (obstacle.getStatus()) {
                batcher.draw(AssetLoader.textureObstacle, obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

            }
        }


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
        font.draw(batcher, "¿Eres mayor de 13 años? " , 20, 2*Gdx.graphics.getHeight()/20 +80);
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

    public void gameOver(float runTime, PlayerShip playerShip){
        soundGameOver.loop();
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

        TextButton buttonRetry = AssetLoader.buttonYes("Retry", Gdx.graphics.getWidth()/20 +75 , 2*Gdx.graphics.getHeight()/20 -25);
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
        stageGameOv.act();
        stageGameOv.draw();
    }
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

        /*if (pressed){
            soundGameOver.pause();
            start(runTime);
        }*/

    }


    public void winner(float runTime, PlayerShip playerShip){

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
        batcher.enableBlending();

        batcher.draw(AssetLoader.textureWin, 3, 20, 128, 128);

        BitmapFont font = new BitmapFont(true);
        font.getData().setScale(0.95f, 0.95f);
        font.draw(batcher, "Your final score: " + String.valueOf(playerShip.getScore()), 8, 160);

        batcher.end();

    }

    public void render(float runTime) {

        myOld = (myWorld.getOlder());
        if (!myOld.getOld()){
            if(nono ==true){
                gameOverNO(runTime);
            }else{
                start(runTime);
            }

        }
        else {

            PlayerShip playerShip = myWorld.getPlayerShip();
            if (gameover){
                gameOver(runTime, playerShip);
            }else {
                soundGameOver.stop();
                invadersAlive = myWorld.getInvadersArmy();
                shot = myWorld.getShotsPlayer();
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
                obstacles(batcher); //Para las barreras
                //Dibujar Balas activas
                if (shot.isActive())
                    batcher.draw(AssetLoader.textureLaser, shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight());

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
                }
            }
        }
    }

}