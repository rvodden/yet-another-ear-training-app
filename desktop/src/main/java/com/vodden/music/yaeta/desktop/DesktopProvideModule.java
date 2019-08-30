package com.vodden.music.yaeta.desktop;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import com.badlogic.gdx.Gdx;
import dagger.Module;
import dagger.Provides;
import org.jfugue.player.Player;

import javax.inject.Singleton;
import javax.sound.sampled.LineUnavailableException;

@Module
class DesktopProvideModule {
  private static final Integer SAMPLE_RATE = 44100;
  private static final Integer BUFFER_SIZE = 2048;
  private static final Integer SAMPLE_OVERLAP = 1536;

  @Provides
  AudioDispatcher provideAudioDispatcher() {
    try {
      return AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE, BUFFER_SIZE, SAMPLE_OVERLAP);
    } catch (LineUnavailableException lue) {
      Gdx.app.log("yaeta", "Failed to get line." + lue.getMessage() + "\n");
      System.exit(-1);
    }
    return null; /* This will never execute */
  }

  @Provides
  Player providePlayer() {
    return new Player();
  }

}
