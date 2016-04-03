package com.progression.staff;

public class Note implements Entry {

    // Quarter notes.
    public static final Note A4_Q = new Note(-8, 4);
    public static final Note B4_Q = new Note(-7, 4);
    public static final Note C4_Q = new Note(-6, 4);
    public static final Note D4_Q = new Note(-5, 4);
    public static final Note E4_Q = new Note(-4, 4);
    public static final Note F4_Q = new Note(-3, 4);
    public static final Note G4_Q = new Note(-2, 4);

    public static final Note A5_Q = new Note(-1, 4);
    public static final Note B5_Q = new Note(0, 4);
    public static final Note C5_Q = new Note(1, 4);
    public static final Note D5_Q = new Note(2, 4);
    public static final Note E5_Q = new Note(3, 4);
    public static final Note F5_Q = new Note(4, 4);
    public static final Note G5_Q = new Note(5, 4);

    public static final Note A6_Q = new Note(6, 4);
    public static final Note B6_Q = new Note(7, 4);
    public static final Note C6_Q = new Note(8, 4);
    public static final Note D6_Q = new Note(9, 4);
    public static final Note E6_Q = new Note(10, 4);
    public static final Note F6_Q = new Note(11, 4);
    public static final Note G6_Q = new Note(12, 4);

    private final int ledgerLine;
    private int noteLength;

    private Note(int ledgerLine, int noteLength) {
        this.ledgerLine = ledgerLine;
        this.noteLength = noteLength;
    }

    public int getGClefLedgeLine() {
        return ledgerLine;
    }
}
