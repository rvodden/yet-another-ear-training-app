package com.vodden.music.yeata.tuner;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.util.PitchConverter;
import com.badlogic.gdx.Gdx;
import com.vodden.music.yeata.tuner.note.Note;

import javax.sound.sampled.LineUnavailableException;

public class Tuner {

    private static final Integer SAMPLE_RATE = 44100;
    private static final Integer BUFFER_SIZE = 2048;
    private static final Integer SAMPLE_OVERLAP = 1536;
    private static final PitchProcessor.PitchEstimationAlgorithm ALGORITHM = PitchProcessor.PitchEstimationAlgorithm.FFT_YIN;
    private static final Double SILENCE_THRESHOLD = -70.0;

    private AudioDispatcher dispatcher;
    private NoteConverter noteConverter;

    private String sharpOrFlat;

    private Note note;
    private Double discrepency;

    public void initTuner() throws LineUnavailableException {
        if (dispatcher == null) {

            noteConverter = new NoteConverterImpl();

            dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE, BUFFER_SIZE, SAMPLE_OVERLAP);

            PitchDetectionHandler printPitch = new PitchDetectionHandler() {
                @Override
                public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {

                    double pitch = pitchDetectionResult.getPitch();

                    Note newNote;

                    if (pitch != -1 && !audioEvent.isSilence(SILENCE_THRESHOLD)) {
                        newNote = noteConverter.toNote(PitchConverter.hertzToMidiKey(pitch));

                        try {
                            if (newNote.equals(note)) {
                                // return;
                            }
                        } catch (NullPointerException npe) {}; // do nothing - a null note is fine.

                        setNote(newNote);
                        double targetPitch = PitchConverter.midiKeyToHertz(PitchConverter.hertzToMidiKey(pitch));
                        setDiscrepency(PitchConverter.hertzToAbsoluteCent(pitch) - PitchConverter.hertzToAbsoluteCent(targetPitch));
                        Gdx.app.log("yeata", String.format("Detected the note %s (out by %f cents)", note.toString(), discrepency));

                    } else {
                        setNote(null);
                    }
                }
            };

            AudioProcessor audioProcessor = new PitchProcessor(ALGORITHM, SAMPLE_RATE, BUFFER_SIZE, printPitch);
            dispatcher.addAudioProcessor(audioProcessor);

            new Thread(dispatcher, "Audio Dispatcher").start();
        }
    }

    private synchronized void setDiscrepency(double discrepency) {
        this.discrepency = discrepency;
    }

    public synchronized void setNote(Note note) {
        this.note = note;

    }

    public synchronized Note getNote() {
        return note;
    }
}
