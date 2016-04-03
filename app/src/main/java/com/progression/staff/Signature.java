package com.progression.staff;

public class Signature {
    private final int gClefLedgerLine;
    private final Accidental accidental;

    public Signature(int gClefLedgerLine, Accidental accidental) {
        this.gClefLedgerLine = gClefLedgerLine;
        this.accidental = accidental;
    }

    public int getgClefLedgerLine() {
        return gClefLedgerLine;
    }

    public Accidental getAccidental() {
        return accidental;
    }
}
