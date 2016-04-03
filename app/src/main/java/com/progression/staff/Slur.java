package com.progression.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slur implements Entry {
    private final List<Note> notes;

    public static Slur slur(Note note1, Note note2, Note... notes) {
        ArrayList<Note> n = new ArrayList<>();
        n.add(note1);
        n.add(note2);
        n.addAll(Arrays.asList(notes));
        return new Slur(n);
    }

    private Slur(List<Note> notes) {
        this.notes = Collections.unmodifiableList(notes);
    }

    public List<Note> getNotes() {
        return notes;
    }
}
