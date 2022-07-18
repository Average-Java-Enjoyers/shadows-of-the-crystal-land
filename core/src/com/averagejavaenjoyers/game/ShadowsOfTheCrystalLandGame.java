package com.averagejavaenjoyers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class ShadowsOfTheCrystalLandGame extends Game {
	public static int SCREEN_WIDTH = 1600;
	public static int SCREEN_HEIGHT = 900;


	public SpriteBatch batch;
	public Sprite background;

	//public FreeTy

	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public BitmapFont font;

	@Override
	public void create () {
		//background = new Sprite(new Texture(Gdx.files.internal("Padia_pixeled.png"), SCREEN_WIDTH, SCREEN_HEIGHT));
		//background = new Sprite(new Texture(Gdx.files.internal("Padia_pixeled.png")), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("SteamwreckBold-e3Xp.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 100;
		fontParameter.color.set(0.854f, 0.572f, 0.043f, 1);
		fontParameter.borderColor.set(Color.BLACK);
		fontParameter.borderWidth = 5;
		font = fontGenerator.generateFont(fontParameter);
		this.setScreen(new StartMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
