package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import Helpers.AssetLoader;
import Screens.GameScreen;

public class SpaceInvaders extends Game{

	@Override
	public void create() {
		Gdx.app.log("SpaceInvadersGame", "created");
		AssetLoader.loadBg();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
