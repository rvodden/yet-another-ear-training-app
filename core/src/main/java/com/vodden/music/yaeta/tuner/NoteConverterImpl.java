package com.vodden.music.yaeta.tuner;

import com.vodden.music.yaeta.tuner.note.Note;

import javax.inject.Inject;

public class NoteConverterImpl implements com.vodden.music.yaeta.tuner.NoteConverter {

  @Inject
  NoteConverterImpl() {
  }

  /**
   * Takes MIDI Key and converts it to a note name and value.
   *
   * @param pitch a number from 0 to 11 which specifies the pitch.
   * @return a Not object with the correct value and shift.
   */
  @Override
  public Note toNote(Integer pitch) {

    Integer normalizedPitch = (Math.round(pitch) - 21) % 12;
    return new Note(normalizedPitch);
  }
}
