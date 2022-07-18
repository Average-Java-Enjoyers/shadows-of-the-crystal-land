package com.averagejavaenjoyers.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;

import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_HEIGHT;
import static com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame.SCREEN_WIDTH;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
		config.setTitle("Shadows Of The Crystal Land");
		new Lwjgl3Application(new ShadowsOfTheCrystalLandGame(), config);
	}
}
