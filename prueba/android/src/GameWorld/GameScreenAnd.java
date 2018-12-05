package GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import Helpers.InputHandler;

public class GameScreenAnd implements Screen {
    private GameWorldAnd world;
    private GameRendererAnd renderer;
    private float runTime;


    // This is the constructor, not the class declaration
    public GameScreenAnd() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorldAnd(gameWidth,gameHeight);
        renderer = new GameRendererAnd(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world.getPlayerShip()));

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.app.log("GameScreenAnd", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreenAnd", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreenAnd", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreenAnd", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}