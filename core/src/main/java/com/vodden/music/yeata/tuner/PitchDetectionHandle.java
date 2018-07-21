package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;

public interface PitchDetectionHandle {
  Double getDiscrepancy();

  void setDiscrepancy(Double discrepancy);

  Note getNote();

  void setNote(Note note);
}
