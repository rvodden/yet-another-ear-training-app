package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class PitchDetectionHandleImpl implements PitchDetectionHandle {

  private volatile Note note;
  private volatile Double discrepancy;

  @Inject
  public PitchDetectionHandleImpl() {

  }

  @Override
  public Double getDiscrepancy() {
    return discrepancy;
  }

  @Override
  public void setDiscrepancy(Double discrepancy) {
    this.discrepancy = discrepancy;
  }

  @Override
  public Note getNote() {
    return note;
  }

  @Override
  public void setNote(Note note) {
    this.note = note;
  }
}
