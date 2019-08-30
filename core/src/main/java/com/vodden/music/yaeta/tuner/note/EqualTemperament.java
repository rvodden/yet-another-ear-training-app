package com.vodden.music.yaeta.tuner.note;

import javax.inject.Inject;

public class EqualTemperament extends Temperament {

  private static final double LOG_TWO = Math.log(2.0);

  @Inject
  EqualTemperament() {
    // permit dagger to construct
  }

  @Override
  public Double frequencyOfNote(com.vodden.music.yaeta.tuner.note.Note note) {
    if(note.getValue() == com.vodden.music.yaeta.tuner.note.Value.Silent) return -1D;
    return midiCentToFrequency(Double.valueOf(note.getMidiKey()));
  }

  @Override
  public com.vodden.music.yaeta.tuner.note.Note noteFromFrequency(Double frequencyInHertz) {
    Double pitchInMidiCent = frequencyToMidiCent(frequencyInHertz);
    Integer midiKey = (int) (long) (Math.round(pitchInMidiCent) - 21) % 12;
    return new com.vodden.music.yaeta.tuner.note.Note(midiKey);
  }

  @Override
  public PlayedNote playedNoteFromFrequency(Double frequencyInHertz) {
    Double midiCent = frequencyToMidiCent(frequencyInHertz);
    Double discrepancy = midiCent - Math.round(midiCent);
    Note note = noteFromFrequency(frequencyInHertz);
    PlayedNote playedNote = new PlayedNote(note, this,discrepancy);
    return playedNote;
  }

  private static Double frequencyToMidiCent(final Double frequencyInHertz) {
    Double pitchInMidiCent = 0.0;
    if (frequencyInHertz != 0) {
      pitchInMidiCent = 12 * Math.log(frequencyInHertz / 440) / LOG_TWO + 69;
    }
    return pitchInMidiCent;
  }

  private static Double midiCentToFrequency(final Double pitchInCents) {
    Double frequencyInHertz = 0.0;
    if (pitchInCents != 0) {
      frequencyInHertz = Math.exp(LOG_TWO * (pitchInCents - 69) / 12) * 440;
    }
    return frequencyInHertz;
  }
}
