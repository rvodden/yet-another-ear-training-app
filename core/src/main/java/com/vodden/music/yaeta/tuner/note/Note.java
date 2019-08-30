package com.vodden.music.yaeta.tuner.note;

import sun.plugin.dom.exception.InvalidStateException;

public class Note {
  final static Integer DEFAULT_OCTAVE = 4;
  final static Shift DEFAULT_SHIFT = Shift.Natural;

  private Value value;
  private Shift shift;
  private Integer octave;

  public Note(Value value, Shift shift, Integer octave) {
    this.value = value;
    this.shift = shift;
    this.octave = octave;
  }

  public Note(Value value, Shift shift) {
    this(value,shift, DEFAULT_OCTAVE);
  }

  public Note(Value value) {
    this(value, DEFAULT_SHIFT, DEFAULT_OCTAVE);
  }

  public Note(Integer pitch) {
    this.shift = Shift.Natural;
    switch (pitch) {
      case -1:
        this.value = Value.Silent;
        break;
      case 0:
        this.value = Value.A;
        break;
      case 1:
        this.value = Value.B;
        this.shift = Shift.Flat;
        break;
      case 2:
        this.value = Value.B;
        break;
      case 3:
        this.value = Value.C;
        break;
      case 4:
        this.value = Value.C;
        this.shift = Shift.Sharp;
        break;
      case 5:
        this.value = Value.D;
        break;
      case 6:
        this.value = Value.E;
        this.shift = Shift.Flat;
        break;
      case 7:
        this.value = Value.E;
        break;
      case 8:
        this.value = Value.F;
        break;
      case 9:
        this.value = Value.F;
        this.shift = Shift.Sharp;
        break;
      case 10:
        this.value = Value.G;
        break;
      case 11:
        this.value = Value.A;
        this.shift = Shift.Flat;
        break;
      default:
        throw new IllegalArgumentException(String.format("%d is not a valid pitch", pitch));
    }
  }

  public Double getFrequency(Temperament temperament) {
    return temperament.frequencyOfNote(this);
  }

  public Value getValue() {
    return value;
  }

  public Shift getShift() {
    return shift;
  }

  public Integer getOctave() { return octave; }

  public Integer getMidiKey() {
    Integer noteValue = 0;
    switch (this.getValue()) {
      case A:
        switch (shift) {
          case Natural:
            noteValue = 21;
            break;
          case Flat:
            noteValue =  20;
            break;
          case Sharp:
            noteValue =  22;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case B:
        switch (shift) {
          case Natural:
            noteValue =  23;
            break;
          case Flat:
            noteValue =  22;
            break;
          case Sharp:
            noteValue =  24;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case C:
        switch (shift) {
          case Natural:
            noteValue =  24;
            break;
          case Flat:
            noteValue =  23;
            break;
          case Sharp:
            noteValue =  25;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case D:
        switch (shift) {
          case Natural:
            noteValue =  26;
            break;
          case Flat:
            noteValue =  25;
            break;
          case Sharp:
            noteValue =  27;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case E:
        switch (shift) {
          case Natural:
            noteValue =  28;
            break;
          case Flat:
            noteValue =  27;
            break;
          case Sharp:
            noteValue =  29;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case F:
        switch (shift) {
          case Natural:
            noteValue =  29;
            break;
          case Flat:
            noteValue =  28;
            break;
          case Sharp:
            noteValue =  30;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case G:
        switch (shift) {
          case Natural:
            noteValue =  31;
            break;
          case Flat:
            noteValue =  30;
            break;
          case Sharp:
            noteValue =  32;
            break;
          default:
            throw new InvalidStateException("Invalid Note Shift");
        }
        break;
      case Silent:
        return -1;
      default:
        throw new InvalidStateException("Invalid Note Value");
    }
    return noteValue + getOctave() * 12;
  }

  public String toString() {
    if (value == Value.Silent) {
      return "Silent";
    }
    return value.toString() + shift.toString();
  }

  public Boolean equals(Note note) {
    return (note.value == this.value && note.shift == this.shift);
  }

}
