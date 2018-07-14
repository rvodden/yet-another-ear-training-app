package com.vodden.music.yeata.tuner;

import com.vodden.music.yeata.tuner.note.Note;

public interface NoteConverter {
    public Note toNote(Integer pitch);
}
