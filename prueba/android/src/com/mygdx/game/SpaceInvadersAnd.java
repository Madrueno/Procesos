package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import GameObjects.PlayerShip;
import GameWorld.GameScreenAnd;
import Helpers.AssetLoader;
import Screens.GameScreen;
import android.os.Environment;

public class SpaceInvadersAnd extends Game{
	private static AndroidLauncher androidLauncher;
	private static String name;
	private static String path;
	@Override
	public void create() {
		Gdx.app.log("SpaceInvadersGame", "created");
		AssetLoader.loadBg();
		setScreen(new GameScreenAnd());


	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	public static String askForName(){
		Input.TextInputListener textListener = new Input.TextInputListener()
		{

			@Override
			public void input(String input)
			{
				System.out.println(input);
				name =input;
			}

			@Override
			public void canceled()
			{
				System.out.println("Aborted");


			}
		};
		Gdx.input.getTextInput(textListener, "Please, insert a name: ", "", "");


		return name;
	}

	public static String getName(){
		return name;
	}

	public static void setName(String n){
		name=n;
	}

	public static void setAndroidLauncher(AndroidLauncher androidL) {
		androidLauncher = androidL;
	}
	public static void hacerFoto(PlayerShip player){
		androidLauncher.hacerFoto(player);

	}
}
