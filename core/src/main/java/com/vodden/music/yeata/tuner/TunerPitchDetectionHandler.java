package com.vodden.music.yeata.tuner;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.util.PitchConverter;
import com.badlogic.gdx.Gdx;
import com.vodden.music.yeata.tuner.NoteConverter;
import com.vodden.music.yeata.tuner.PitchDetectionHandle;
import com.vodden.music.yeata.tuner.note.Note;
import com.vodden.music.yeata.tuner.note.Value;

import javax.inject.Inject;

public class TunerPitchDetectionHandler implements PitchDetectionHandler {
    private static final Integer SAMPLE_RATE = 44100;
    private static final Integer BUFFER_SIZE = 2048;
    private static final Integer SAMPLE_OVERLAP = 1536;
    private static final Integer SILENCE_THRESHOLD = -70;

    private final NoteConverter noteConverter;
    private final PitchDetectionHandle pitchDetectionHandle;

    @Inject
    TunerPitchDetectionHandler(NoteConverter noteConverter, PitchDetectionHandle pitchDetectionHandle) {
        this.noteConverter = noteConverter;
        this.pitchDetectionHandle = pitchDetectionHandle;
    }

    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
        Note note;
        Double discrepancy = 0.0;
        Double pitch = Double.valueOf(pitchDetectionResult.getPitch());

        if (pitch != -1 && !audioEvent.isSilence(SILENCE_THRESHOLD)) {
            note = noteConverter.toNote(PitchConverter.hertzToMidiKey(pitch));

            try {
                if (note.equals(pitchDetectionHandle.getNote())) {
                    return;
                }
                pitchDetectionHandle.setNote(note);
                double targetPitch = PitchConverter.midiKeyToHertz(PitchConverter.hertzToMidiKey(pitch));
                pitchDetectionHandle.setDiscrepancy(PitchConverter.hertzToAbsoluteCent(pitch) - PitchConverter.hertzToAbsoluteCent(targetPitch));
                Gdx.app.log("yeata", String.format("Detected the note %s (out by %f cents)", note.toString(), discrepancy));
            }
            catch (NullPointerException npe) {} // do nothing - a null note is fine.

        } else {
            pitchDetectionHandle.setNote(new Note(Value.Silent));
        }
    }

}
