package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;

public interface PitchDetectionHandle {
    void setDiscrepancy(Double discrapancy);

    Double getDiscrepancy();

    void setNote(Note note);

    Note getNote();
}
