package com.vodden.music.yeata;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AndroidModule.class, CoreModule.class})
interface AndroidComponent {
  void inject(AndroidLauncher androidLauncher);
}
