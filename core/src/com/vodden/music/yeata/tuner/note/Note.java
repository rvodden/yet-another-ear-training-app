package com.vodden.music.yeata.tuner.note;

import java.awt.print.Printable;

public class Note {
    private Value value;
    private Shift shift;

    public Note (Value value, Shift shift) {
        this.value = value;
        this.shift = shift;
    }

    public Note (Value value) {
        this.value = value;
        this.shift = Shift.Natural;
    }

    public Note (Integer pitch) {
        this.shift = Shift.Natural;
        switch (pitch) {
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

    public String toString() {
        return value.toString() + shift.toString();
    }

    public Boolean equals(Note note) {
        return (note.value == this.value && note.shift == this.shift);
    }
}
