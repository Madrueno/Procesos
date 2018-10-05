package GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import GameObjects.Invaders;
import GameObjects.ListInvaders;
import GameObjects.PlayerShip;
import GameObjects.Shots;
import GameObjects.ObstacleGroups;
import GameObjects.Obstacle;
import Helpers.AssetLoader;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;


    public static ListInvaders invadersAlive;
    public Shots shot;

    public static ObstacleGroups obstacleActive;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;


        // The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

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

        ImageButton buttonLeft = AssetLoader.buttonLeft(25, 10);
        stage.addActor(buttonLeft);

        ImageButton buttonRight = AssetLoader.buttonRight(125, 10);
        stage.addActor(buttonRight);

        ImageButton buttonShoot = AssetLoader.buttonShoot(200, 10);
        stage.addActor(buttonShoot);

        // aqui va la funcionalidad de los botones
        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("buttonLeft", "Boton izquierdo pulsado");
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
                shot.shoot(playerShip.getPosition(),0);
                shot.setDeaths(0);
                return true;
            }});



        stage.act();
        stage.draw();
    }

    public void invaders (SpriteBatch batcher, float time){

        //invadersAlive.newInvader(time); opcion de aumentar los invaders

        for (Invaders invader : invadersAlive.getArmy()) {
            if (invader.isAlive()) {
                if (invader.getPosition().y>140){

                    batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);

                }
                else {
                    batcher.draw(AssetLoader.textureInvader, invader.getPosition().x, invader.getPosition().y, invader.getWidth(), invader.getHeight());
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

    }

    public void render(float runTime) {
        PlayerShip playerShip = myWorld.getPlayerShip();
        invadersAlive = myWorld.getInvadersArmy();
        shot = myWorld.getShotsPlayer();
        obstacleActive= myWorld.getAllObstacle();

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
            batcher.draw(AssetLoader.textureBg,0, 0, 200, 500);
            batcher.enableBlending();
            batcher.draw(AssetLoader.texturePlayer,PlayerShip.getX(),PlayerShip.getY(), PlayerShip.getWidth(),PlayerShip.getHeight());

            if (playerShip.getLives()==0){
                batcher.draw(AssetLoader.textureGameOver, 3, 20, 128, 128);
            }
            float time = runTime;
            invaders(batcher,time);
            obstacles(batcher); //Para las barreras
            //Dibujar Balas activas
            if(shot.isActive())
                batcher.draw(AssetLoader.textureLaser,shot.getX(),shot.getY(),shot.getWidth(),shot.getHeight());

            //SCORE
            BitmapFont font = new BitmapFont(true);
            font.draw(batcher, "Score: " +String.valueOf(playerShip.getScore()), 10, 10);
            //


        batcher.end();


        buttons(playerShip); // Pone los botones

        //Falta el sprite
    }

}