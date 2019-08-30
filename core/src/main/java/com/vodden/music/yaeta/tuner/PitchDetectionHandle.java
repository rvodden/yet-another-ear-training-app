package com.vodden.music.yaeta.tuner;

public interface PitchDetectionHandle {
  com.vodden.music.yaeta.tuner.note.PlayedNote getNote();

  void setNote(com.vodden.music.yaeta.tuner.note.PlayedNote note);
}
