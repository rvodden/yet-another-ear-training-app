package com.vodden.music.yaeta.tuner.note;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualTemperamentTest {

  private static EqualTemperament underTest = new EqualTemperament();

  private static Map<Note,Double> noteFrequencyMap;
  static {
    noteFrequencyMap = new HashMap<Note,Double>();
    noteFrequencyMap.put( new Note(Value.A, Shift.Natural,4),440.0000D);
    noteFrequencyMap.put( new Note(Value.B, Shift.Natural,4),493.8833D);
    noteFrequencyMap.put( new Note(Value.C, Shift.Natural,4),523.2511D);
    noteFrequencyMap.put( new Note(Value.D, Shift.Natural,4),587.3295D);
    noteFrequencyMap.put( new Note(Value.E, Shift.Natural,4),659.2551D);
    noteFrequencyMap.put( new Note(Value.F, Shift.Natural,4),698.4565D);
  }

  private static Set<Map.Entry<Note,Double>> getNoteFrequencyMap() {
    return noteFrequencyMap.entrySet();
  }

  private static Map<Double,PlayedNote> frequencyPlayedNoteMap;
  static {
    frequencyPlayedNoteMap = new HashMap<Double, PlayedNote>();

    frequencyPlayedNoteMap.put( 440.0000D, new PlayedNote(Value.A, Shift.Natural, underTest,0.0D));
    frequencyPlayedNoteMap.put( 493.8833D, new PlayedNote(Value.B, Shift.Natural, underTest,0.0D));
    frequencyPlayedNoteMap.put( 523.2511D, new PlayedNote(Value.C, Shift.Natural, underTest,0.0D));
    frequencyPlayedNoteMap.put( 587.3295D, new PlayedNote(Value.D, Shift.Natural, underTest,0.0D));
    frequencyPlayedNoteMap.put( 659.2551D, new PlayedNote(Value.E, Shift.Natural, underTest,0.0D));
    frequencyPlayedNoteMap.put( 698.4565D, new PlayedNote(Value.F, Shift.Natural, underTest,0.0D));

    frequencyPlayedNoteMap.put( 450.0000D, new PlayedNote(Value.A, Shift.Natural, underTest,0.3890D));
  }

  private static Set<Map.Entry<Double,PlayedNote>> getFrequencyNoteMap() {
    return frequencyPlayedNoteMap.entrySet();
  }

  @ParameterizedTest()
  @MethodSource("getNoteFrequencyMap")
  public void conversionToFrequency(Map.Entry<Note,Double> entry) {
    EqualTemperament temperament = new EqualTemperament();
    assertEquals(temperament.frequencyOfNote(entry.getKey()),entry.getValue(),0.0001D);
  }

  @ParameterizedTest()
  @MethodSource("getFrequencyNoteMap")
  public void conversionToPlayedNote(Map.Entry<Double,PlayedNote> entry) {
    EqualTemperament temperament = new EqualTemperament();
    PlayedNote calulatedNote = temperament.playedNoteFromFrequency(entry.getKey());
    PlayedNote targetNote = entry.getValue();
    assertEquals(calulatedNote.getDiscrepancy(), targetNote.getDiscrepancy(), 0.0001D);
    assertEquals(calulatedNote.getShift(), targetNote.getShift());
    assertEquals(calulatedNote.getValue(), targetNote.getValue());
  }
}
