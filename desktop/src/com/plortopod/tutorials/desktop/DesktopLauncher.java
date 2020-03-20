package com.plortopod.tutorials.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.plortopod.tutorials.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 16 * 80;
        config.height = 9 * 80;
        new LwjglApplication(new Main(), config);
    }
}
