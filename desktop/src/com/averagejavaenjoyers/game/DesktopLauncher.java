package com.averagejavaenjoyers.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.averagejavaenjoyers.game.ShadowsOfTheCrystalLandGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Shadows of the Crystal Land");
		new Lwjgl3Application(new ShadowsOfTheCrystalLandGame(), config);
	}
}
