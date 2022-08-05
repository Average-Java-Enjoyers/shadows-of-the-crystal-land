package com.averagejavaenjoyers.game;

import com.averagejavaenjoyers.game.screen.StartMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Optional;

public class ShadowsOfTheCrystalLandGame extends Game {
	
	private static ShadowsOfTheCrystalLandGame game;
	public static int SCREEN_WIDTH = 1600;
	public static int SCREEN_HEIGHT = 900;


	public SpriteBatch batch;
	public Sprite background;

	//public FreeTy
	
	private ShadowsOfTheCrystalLandGame() {
	}
	
	public static ShadowsOfTheCrystalLandGame instance() {
		return Optional.ofNullable(game).orElseGet(() -> {
			game = new ShadowsOfTheCrystalLandGame();
			return game;
		});
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new StartMenuScreen());
	}
	

	@Override
	public void dispose () {
		batch.dispose();
	}
}
