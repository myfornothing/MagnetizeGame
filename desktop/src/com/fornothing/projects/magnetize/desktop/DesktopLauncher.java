package com.fornothing.projects.magnetize.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fornothing.projects.magnetize.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = MainClass.APP_TITLE + " v" + MainClass.APP_VERSION;
		config.width = MainClass.APP_DESKTOP_WIDTH;
		config.height = MainClass.APP_DESKTOP_HEIGHT;
		config.backgroundFPS = MainClass.APP_FPS;
		config.foregroundFPS = MainClass.APP_FPS;
		new LwjglApplication(new MainClass(), config);
	}
}
