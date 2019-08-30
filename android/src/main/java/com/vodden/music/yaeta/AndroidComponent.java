package com.vodden.music.yaeta;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidModule.class, com.vodden.music.yaeta.CoreModule.class})
interface AndroidComponent {
  void inject(AndroidLauncher androidLauncher);
}
