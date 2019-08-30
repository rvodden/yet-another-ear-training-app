package com.vodden.music.yaeta.tuner;

import com.vodden.music.yaeta.tuner.note.Note;

public interface NoteConverter {
  Note toNote(Integer pitch);
}
