package com.vodden.music.yaeta.tuner;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class BasicPitchDetectionHandle implements com.vodden.music.yaeta.tuner.PitchDetectionHandle {

  private volatile com.vodden.music.yaeta.tuner.note.PlayedNote note;

  @Inject
  public BasicPitchDetectionHandle() {

  }

  @Override
  public com.vodden.music.yaeta.tuner.note.PlayedNote getNote() {
    return note;
  }

  @Override
  public void setNote(com.vodden.music.yaeta.tuner.note.PlayedNote note) {
    this.note = note;
  }
}
