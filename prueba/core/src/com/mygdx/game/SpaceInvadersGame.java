package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import Screens.GameScreen;

public class SpaceInvadersGame extends Game {
    @Override
    public void create() {
        Gdx.app.log("SpaceInvadersGame", "created");
        setScreen(new GameScreen());
    }
}
