package com.vodden.music.yaeta.tuner;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioProcessor;
import com.badlogic.gdx.Gdx;

import javax.inject.Inject;

public class Tuner {

  private final AudioDispatcher audioDispatcher;
  private final AudioProcessor audioProcessor;

  @Inject
  public Tuner(AudioDispatcher audioDispatcher, AudioProcessor audioProcessor) {
    this.audioDispatcher = audioDispatcher;
    this.audioProcessor = audioProcessor;
  }

  public void initTuner() {
    Gdx.app.log("yeata", "Starting tuner.");
    audioDispatcher.addAudioProcessor(audioProcessor);
    new Thread(audioDispatcher, "Audio Dispatcher").start();
  }
}
