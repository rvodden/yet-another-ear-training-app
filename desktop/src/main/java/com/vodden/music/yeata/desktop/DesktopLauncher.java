package com.vodden.music.yeata.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vodden.music.yeata.Application;

import javax.inject.Inject;

class DesktopLauncher {

    @Inject
    Application application;

    @Inject
    DesktopLauncher() {
        super();
        DesktopComponent desktopComponent = DaggerDesktopComponent.builder().desktopModule(new DesktopModule()).build();
        desktopComponent.inject(this);
    }

	public static void main (String[] arg) {
        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.launch();
	}

	private void launch() {
        new LwjglApplication(this.application, new LwjglApplicationConfiguration());
    }

}
