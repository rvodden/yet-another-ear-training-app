package com.vodden.music.yeata.tuner.note;

public enum Shift {
  Sharp, Flat, DoubleSharp, DoubleFlat, Natural;

  public String toString() {
    switch (this) {
      case Sharp:
        return "\u0023";
      case Flat:
        return "\u044C";
      case DoubleSharp:
        return "\u1D12A";
      case DoubleFlat:
        return "\u1D12B";
      case Natural:
        return "\u266E";
      default:
        throw new IllegalArgumentException();
    }
  }
}
