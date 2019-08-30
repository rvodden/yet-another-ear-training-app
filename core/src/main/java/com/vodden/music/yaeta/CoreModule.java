package com.vodden.music.yaeta;

import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import com.vodden.music.yaeta.tuner.TunerPitchDetectionHandler;
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
  abstract com.vodden.music.yaeta.tuner.NoteConverter provideNoteConverter(com.vodden.music.yaeta.tuner.NoteConverterImpl noteConverter);

  @Binds
  abstract PitchDetectionHandler providePitchDetectionHandler(TunerPitchDetectionHandler pitchDetectionHandler);

  @Binds
  abstract com.vodden.music.yaeta.tuner.PitchDetectionHandle providePitchDetectionHandle(com.vodden.music.yaeta.tuner.BasicPitchDetectionHandle pitchDetectionHandle);

  @Binds
  abstract com.vodden.music.yaeta.tuner.note.Temperament provideTemperament(com.vodden.music.yaeta.tuner.note.EqualTemperament equalTemperament);

}
