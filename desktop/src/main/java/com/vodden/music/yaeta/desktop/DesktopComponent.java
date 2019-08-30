package com.vodden.music.yaeta.desktop;

import com.vodden.music.yaeta.CoreModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DesktopProvideModule.class, DesktopBindModule.class, CoreModule.class})
interface DesktopComponent {
  void inject(DesktopLauncher desktopLauncher);
}
