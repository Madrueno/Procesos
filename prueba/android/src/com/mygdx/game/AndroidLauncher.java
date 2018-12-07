package com.mygdx.game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.w3c.dom.Text;

import java.io.File;

import GameObjects.PlayerShip;
import Helpers.AssetLoader;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AndroidLauncher extends AndroidApplication {
	private  String name;
	private  static String rutaImagenHecha="";

	private static String dirRuta="MisImagenes/";
	private static String rutaFoto=dirRuta+"fotosSpaceInvaders";
	private static  TextureRegion text;
	private static  Texture t;
	//private ImageView image;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SpaceInvadersAnd(), config);
		SpaceInvadersAnd.setAndroidLauncher(this);
		//image = new ImageView(this);
		//image = findViewById(R);
        //name=getName();

	}



	public void hacerFoto(PlayerShip playerShip) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA,READ_EXTERNAL_STORAGE},100);
		}
		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
		StrictMode.setVmPolicy(builder.build());
		File fileImage = new File(Environment.getExternalStorageDirectory(),rutaFoto);
		boolean fotoHecha=fileImage.exists();
		String nombreImagen="";
		if (!fotoHecha){
			fotoHecha=fileImage.mkdirs();
		}

		if (fotoHecha){
			nombreImagen= SpaceInvadersAnd.getName()+playerShip.getScore()+".jpg";
		}
		rutaImagenHecha= Environment.getExternalStorageDirectory()+File.separator+rutaFoto+nombreImagen;
		File imagen=new File(rutaImagenHecha);
		Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagen));
		name=SpaceInvadersAnd.getName();

		playerShip.setPath(rutaImagenHecha);
		playerShip.setNamePlayer(name);
		startActivityForResult(intent,0);


	}
	@Override
	protected  void onActivityResult (int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode,resultCode,data);
		MediaScannerConnection.scanFile(this, new String[]{rutaImagenHecha}, null,
				new MediaScannerConnection.OnScanCompletedListener() {
					@Override
					public void onScanCompleted(String path, Uri uri) {
						Log.i("Ruta de almacenamiento","Ruta: "+rutaImagenHecha);

					}
				});

		//Bitmap bit =BitmapFactory.decodeFile(rutaImagenHecha);
		//image.setImageBitmap(bit);

	}



	/*public String getName (){
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
		Gdx.input.getTextInput(textListener, "Your Name: ", "Introduzca su nombre", "");
		return name;
	}*/
}
