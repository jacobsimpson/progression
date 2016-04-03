package com.progression.staff;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.progression.staff.Accidental.FLAT;
import static com.progression.staff.Accidental.SHARP;

public enum Key {
    C_MAJOR("C Major"),
    A_MINOR("A Minor"),

    F_MAJOR("F Major", new Signature(0, FLAT)),
    D_MINOR("D Minor", new Signature(0, FLAT)),

    B_FLAT_MAJOR("B♭_Major", new Signature(0, FLAT), new Signature(3, FLAT)),
    G_MINOR("g Minor", new Signature(0, FLAT), new Signature(3, FLAT)),

    E_FLAT_MAJOR("E♭ Major", new Signature(0, FLAT), new Signature(3, FLAT), new Signature(-1, FLAT)),
    C_MINOR("c Minor", new Signature(0, FLAT), new Signature(3, FLAT), new Signature(-1, FLAT)),

    E_MAJOR("E Major", new Signature(4, SHARP), new Signature(1, SHARP), new Signature(5, SHARP), new Signature(2, SHARP)),
    C_SHARP_MINOR("c♯ Minor", new Signature(4, SHARP), new Signature(1, SHARP), new Signature(5, SHARP), new Signature(2, SHARP)),

    A_MAJOR("A Major", new Signature(4, SHARP), new Signature(1, SHARP), new Signature(5, SHARP)),
    F_SHARP_MINOR("f♯ Minor", new Signature(4, SHARP), new Signature(1, SHARP), new Signature(5, SHARP)),

    D_MAJOR("D Major", new Signature(4, SHARP), new Signature(1, SHARP)),
    B_MINOR("b Minor", new Signature(4, SHARP), new Signature(1, SHARP)),

    G_MAJOR("G Major", new Signature(4, SHARP)),
    E_MINOR("E Minor", new Signature(4, SHARP));

    private final String toString;
    private final List<Signature> signatures;

    Key(String toString, Signature... signatures) {
        this.toString = toString;
        this.signatures = Collections.unmodifiableList(Arrays.asList(signatures));
    }

    @Override
    public String toString() {
        return toString;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }
}
