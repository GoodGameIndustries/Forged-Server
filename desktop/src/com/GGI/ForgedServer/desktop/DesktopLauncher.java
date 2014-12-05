package com.GGI.ForgedServer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.GGI.ForgedServer.ForgedServer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		 cfg.width = 800;
	     cfg.height = 450;
		new LwjglApplication(new ForgedServer(), cfg);
		}
}
