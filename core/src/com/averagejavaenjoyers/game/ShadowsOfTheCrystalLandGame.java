package com.averagejavaenjoyers.game;

import com.averagejavaenjoyers.game.screen.MainMenuScreen;
import com.badlogic.gdx.Game;

import java.util.Optional;


public class ShadowsOfTheCrystalLandGame extends Game {
	
	private static ShadowsOfTheCrystalLandGame game;
	
	private ShadowsOfTheCrystalLandGame() {
	}
	
	public static ShadowsOfTheCrystalLandGame instance() {
		return Optional.ofNullable(game).orElseGet(() -> {
			game = new ShadowsOfTheCrystalLandGame();
			return game;
		});
	}
	
	@Override
	public void create() {
		setScreen(new MainMenuScreen());
	}
	
	@Override
	public void render() {
	}
	
	@Override
	public void dispose() {
	
	}
}
