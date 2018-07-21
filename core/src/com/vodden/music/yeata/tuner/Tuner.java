package com.vodden.music.yeata.tuner;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.util.PitchConverter;
import com.badlogic.gdx.Gdx;
import com.vodden.music.yeata.tuner.note.Note;

import javax.inject.Inject;
import javax.sound.sampled.LineUnavailableException;

public class Tuner {

    private AudioDispatcher audioDispatcher;
    private PitchDetectionHandler pitchDetectionHandler;
    private AudioProcessor audioProcessor;

    @Inject
    public Tuner(AudioDispatcher audioDispatcher, PitchDetectionHandler pitchDetectionHandler, AudioProcessor audioProcessor) {
        this.audioDispatcher = audioDispatcher;
        this.pitchDetectionHandler = pitchDetectionHandler;
        this.audioProcessor = audioProcessor;
    }

    public void initTuner() throws LineUnavailableException {
        Gdx.app.log("yeata", "Starting tuner.");
        audioDispatcher.addAudioProcessor(audioProcessor);
        new Thread(audioDispatcher, "Audio Dispatcher").start();
    }
}
