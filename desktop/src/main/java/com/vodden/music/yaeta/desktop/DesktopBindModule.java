package com.vodden.music.yaeta.desktop;

import com.vodden.music.yaeta.desktop.player.DesktopPlayer;
import com.vodden.music.yaeta.player.Player;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public abstract class DesktopBindModule {
  @Binds
  @Singleton
  abstract Player providePlayer(DesktopPlayer desktopPlayer);
}
