package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;
import com.vodden.music.yeata.tuner.note.Shift;
import com.vodden.music.yeata.tuner.note.Value;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.vodden.music.yeata.tuner.note.Value.C;
import static com.vodden.music.yeata.tuner.note.Shift.Natural;


@Singleton
public class PitchDetectionHandleImpl implements PitchDetectionHandle {

    private Note note;
    private Double discrepancy;

    @Inject
    public PitchDetectionHandleImpl() {

    }

    @Override
    public void setDiscrepancy(Double discrepancy) {
        synchronized (discrepancy) {
            this.discrepancy = discrepancy;
        }
    }

    @Override
    public Double getDiscrepancy() {
        synchronized (discrepancy) {
            return discrepancy;
        }
    }

    @Override
    public void setNote(Note note) {
        synchronized (note) {
            this.note = note;
        }
    }

    @Override
    public Note getNote() {
        synchronized (note) {
            return note;
        }
    }
}
