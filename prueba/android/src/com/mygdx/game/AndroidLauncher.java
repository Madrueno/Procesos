package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class AndroidLauncher extends AndroidApplication {
	private TextField.DefaultOnscreenKeyboard keyboard;
	private TextField textField;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SpaceInvaders(), config);

		keyboard= new TextField.DefaultOnscreenKeyboard();

		System.out.println("sisi");
		Input.TextInputListener textListener = new Input.TextInputListener()
		{

			@Override
			public void input(String input)
			{
				keyboard.show(true);
				textField.setOnscreenKeyboard(keyboard);
				System.out.println(input);
			}

			@Override
			public void canceled()
			{
				System.out.println("Aborted");
			}
		};
		Gdx.input.getTextInput(textListener, "Your Name: ", "Introduzca su nombre", "");
	}
}
