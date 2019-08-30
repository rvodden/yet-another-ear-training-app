package com.vodden.music.yaeta.tuner;

import com.vodden.music.yaeta.tuner.note.PlayedNote;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmoothedPitchDetectionHandle implements com.vodden.music.yaeta.tuner.PitchDetectionHandle {
  /* this is the number of pitch detections over which the value is smoothed */
  static final Integer smoothingDistance = 5;

  private CircularFifoQueue<com.vodden.music.yaeta.tuner.note.PlayedNote> ringOfNotes;

  @Inject
  SmoothedPitchDetectionHandle() {
    ringOfNotes = new CircularFifoQueue<PlayedNote>(smoothingDistance);
  }

  @Override
  public com.vodden.music.yaeta.tuner.note.PlayedNote getNote() {
    return ringOfNotes.get(smoothingDistance - 1);
  }

  @Override
  public void setNote(com.vodden.music.yaeta.tuner.note.PlayedNote note) {
    ringOfNotes.add(note);
  }
}
