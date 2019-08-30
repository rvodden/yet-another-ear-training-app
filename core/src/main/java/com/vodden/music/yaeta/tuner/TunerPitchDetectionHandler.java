package com.vodden.music.yaeta.tuner;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import com.badlogic.gdx.Gdx;
import com.vodden.music.yaeta.tuner.note.Shift;

import javax.inject.Inject;

public class TunerPitchDetectionHandler implements PitchDetectionHandler {
  private static final Integer SAMPLE_RATE = 44100;
  private static final Integer BUFFER_SIZE = 2048;
  private static final Integer SAMPLE_OVERLAP = 1536;
  private static final Integer SILENCE_THRESHOLD = -70;

  private final com.vodden.music.yaeta.tuner.note.Temperament temperament;
  private final PitchDetectionHandle pitchDetectionHandle;

  @Inject
  TunerPitchDetectionHandler(com.vodden.music.yaeta.tuner.note.Temperament temperament, PitchDetectionHandle pitchDetectionHandle) {
    this.temperament = temperament;
    this.pitchDetectionHandle = pitchDetectionHandle;
  }

  @Override
  public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
    com.vodden.music.yaeta.tuner.note.PlayedNote playedNote;
    Double frequencyInHertz = Double.valueOf(pitchDetectionResult.getPitch());

    if (frequencyInHertz != -1 && !audioEvent.isSilence(SILENCE_THRESHOLD)) {
      playedNote = temperament.playedNoteFromFrequency(frequencyInHertz);

      try {
        pitchDetectionHandle.setNote(playedNote);
        Gdx.app.log("yeata", String.format("Detected the note %s (out by %f cents)", playedNote.toString(), playedNote.getDiscrepancy()));
      } catch (NullPointerException npe) {

      } // do nothing - a null note is fine.

    } else {
      pitchDetectionHandle.setNote(new com.vodden.music.yaeta.tuner.note.PlayedNote(com.vodden.music.yaeta.tuner.note.Value.Silent,Shift.Natural,temperament,0D));
    }
  }

}
