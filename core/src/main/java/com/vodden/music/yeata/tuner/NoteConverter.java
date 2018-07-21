package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;

public interface NoteConverter {
    Note toNote(Integer pitch);
}
