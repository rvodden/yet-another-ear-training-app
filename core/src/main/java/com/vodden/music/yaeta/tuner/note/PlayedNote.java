package com.vodden.music.yaeta.tuner.note;

/**
 * This class represents a note which is being played. As
 * such it includes offset information from the ideal pitch.
 *
 * In order to calculate the ideal pitch correctly a temperament
 * must be provided.
 */
public class PlayedNote extends com.vodden.music.yaeta.tuner.note.Note {

  private Double discrepancy;
  private Temperament temperament;

  public PlayedNote(com.vodden.music.yaeta.tuner.note.Value value, com.vodden.music.yaeta.tuner.note.Shift shift, Temperament temperament, Double discrepancy) {
    super(value, shift);
    this.discrepancy = discrepancy;
    this.temperament = temperament;
  }

  public PlayedNote(com.vodden.music.yaeta.tuner.note.Note note, Temperament temperament, Double discrepancy) {
    super(note.getValue(), note.getShift() );
    this.discrepancy = discrepancy;
    this.temperament = temperament;
  }

  public Double getFrequency() {
    return super.getFrequency(getTemperament());
  }

  public Double getDiscrepancy() {
    return discrepancy;
  }

  public Temperament getTemperament() {
    return temperament;
  }
}
