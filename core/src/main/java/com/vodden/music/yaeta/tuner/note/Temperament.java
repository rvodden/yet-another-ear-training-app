package com.vodden.music.yaeta.tuner.note;

public abstract class Temperament {
  /* Returns the frequency of a note in the defined temperament */
  public abstract Double frequencyOfNote(com.vodden.music.yaeta.tuner.note.Note note);

  public abstract com.vodden.music.yaeta.tuner.note.Note noteFromFrequency(Double frequencyInHertz);

  public abstract com.vodden.music.yaeta.tuner.note.PlayedNote playedNoteFromFrequency(Double frequencyInHertz);
}
