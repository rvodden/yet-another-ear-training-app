package com.vodden.music.yaeta;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import dagger.Module;
import dagger.Provides;

@Module
class AndroidModule {
  private static final Integer SAMPLE_RATE = 44100;
  private static final Integer BUFFER_SIZE = 2048;
  private static final Integer SAMPLE_OVERLAP = 1536;

  @Provides
  AudioDispatcher provideAudioDispatcher() {
    return AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE, BUFFER_SIZE, SAMPLE_OVERLAP);
  }

}
