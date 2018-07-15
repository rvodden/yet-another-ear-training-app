package com.vodden.music.yeata.desktop;

import com.vodden.music.yeata.Application;
import com.vodden.music.yeata.CoreModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DesktopModule.class, CoreModule.class})
public interface DesktopComponent {
    void inject(DesktopLauncher desktopLauncher);
}
