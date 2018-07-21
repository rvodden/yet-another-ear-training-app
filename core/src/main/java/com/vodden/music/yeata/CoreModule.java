package com.vodden.music.yeata;

import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import com.vodden.music.yeata.tuner.*;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CoreModule {
  private static final Integer SAMPLE_RATE = 44100;
  private static final Integer BUFFER_SIZE = 2048;
  private static final Integer SAMPLE_OVERLAP = 1536;

  private static final PitchEstimationAlgorithm ALGORITHM = PitchEstimationAlgorithm.FFT_YIN;

  @Provides
  static AudioProcessor provideAudioProcessor(PitchDetectionHandler pitchDetectionHandler) {
    return new PitchProcessor(ALGORITHM, SAMPLE_RATE, BUFFER_SIZE, pitchDetectionHandler);
  }

  @Binds
  abstract NoteConverter provideNoteConverter(NoteConverterImpl noteConverter);

  @Binds
  abstract PitchDetectionHandler providePitchDetectionHandler(TunerPitchDetectionHandler pitchDetectionHandler);

  @Binds
  abstract PitchDetectionHandle providePitchDetectionHandle(PitchDetectionHandleImpl pitchDetectionHandle);

}
