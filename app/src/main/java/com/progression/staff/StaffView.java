package com.progression.staff;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.StrictMath.abs;

public class StaffView extends View {
    private static final String SHARP = "♯";
    private static final String FLAT = "♭";
    private static final String QUARTER_NOTE = "♩";

    private final Paint paint = new Paint();
    private final ArrayList<Entry> staffEntries = new ArrayList<>();
    private Key key;
    private int beatsPerMeasure;
    private int oneBeat;

    public StaffView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StaffView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);

        StaffCursor cursor = new StaffCursor(getPaddingLeft(), getPaddingTop());

        drawLedgeLines(canvas, width, cursor);

        cursor.next(30, 0);

        drawSignature(canvas, cursor);

        drawNotes(canvas, cursor);
    }

    private void drawLedgeLines(Canvas canvas, int width, StaffCursor cursor) {
        paint.setStyle(Paint.Style.FILL);
        for (int i = -4; i < 5; i = i + 2) {
            Coordinate c = cursor.next(0, i);
            canvas.drawLine(c.x, c.y, width - c.x, c.y, paint);
        }
    }

    private void drawNotes(Canvas canvas, StaffCursor cursor) {
        if (staffEntries != null) {
            for (int i = 0; i < staffEntries.size(); i++) {
                Entry entry = staffEntries.get(i);
                if (entry instanceof Note) {
                    drawQuarterNote(canvas, cursor, ((Note) entry).getGClefLedgeLine());
                } else if (entry instanceof Slur) {
                    Slur slur = (Slur) entry;
                    Coordinate start = null;
                    for (Note note : slur.getNotes()) {
                        drawQuarterNote(canvas, cursor, note.getGClefLedgeLine());
                        if (start == null) start = cursor.previous();
                    }
                    Coordinate end = cursor.previous();
                    drawSlur(canvas, start, end);
                }
            }
        }
    }

    private void drawSlur(Canvas canvas, Coordinate start, Coordinate end) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(new RectF(start.x, start.y - 40, end.x + 30, end.y + 40), 30, 105, false, paint);
    }

    private void drawSignature(Canvas canvas, StaffCursor cursor) {
        if (key != null) {
            List<Signature> signatures = key.getSignatures();
            for (int i = 0; i < signatures.size(); i++) {
                Signature signature = signatures.get(i);
                if (signature.getAccidental() == Accidental.SHARP) {
                    drawSharp(canvas, cursor, signature.getgClefLedgerLine());
                } else {
                    drawFlat(canvas, cursor, signature.getgClefLedgerLine());
                }
            }
        }
    }

    private void drawSharp(Canvas canvas, StaffCursor cursor, int staffLine) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        Coordinate c = cursor.next(20, staffLine);
        canvas.drawText(SHARP, c.x, c.y + 11, paint);
    }

    private void drawFlat(Canvas canvas, StaffCursor cursor, int staffLine) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setFakeBoldText(true);
        Coordinate c = cursor.next(20, staffLine);
        canvas.drawText(FLAT, c.x, c.y + 11, paint);
        paint.setFakeBoldText(false);
    }

    private void drawQuarterNote(Canvas canvas, StaffCursor cursor, int staffLine) {
        paint.setStyle(Paint.Style.FILL);

        // If the note to be drawn is sufficiently outside the main staff lines, some extra,
        // short, ledger lines have to be filled in to give the reader reference for how far
        // above, or below, the staff a note is.
        if (staffLine < -5 || staffLine > 5) {
            int sign = Integer.signum(staffLine);
            for (int i = 6; i <= abs(staffLine); i = i + 2) {
                Coordinate c = cursor.next(0, i * sign);
                canvas.drawLine(c.x - 3, c.y, c.x + 36, c.y, paint);
            }
        }

        // Draw the quarternote last so the position information of the note, rather than the
        // extra ledger lines isn't the value available from 'previous' in the cursor.
        Coordinate c = cursor.next(40, staffLine);
        paint.setTextSize(125);
        canvas.drawText(QUARTER_NOTE, c.x, c.y + 10, paint);
    }

    public StaffView withKey(Key key) {
        this.key = key;
        invalidate();
        return this;
    }

    public StaffView withTimeSignature(int beatsPerMeasure, int oneBeat) {
        this.beatsPerMeasure = beatsPerMeasure;
        this.oneBeat = oneBeat;
        invalidate();
        return this;
    }

    public StaffView withMusic(Entry... notes) {
        this.staffEntries.clear();
        this.staffEntries.addAll(Arrays.asList(notes));
        invalidate();
        return this;
    }

    private static class Coordinate {
        public final int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class StaffCursor {
        private final Coordinate staff;
        private Coordinate next;
        private Coordinate previous;

        public StaffCursor(int staffX, int staffY) {
            this.staff = new Coordinate(staffX, staffY);
            next = previous = new Coordinate(staffX + 20, lineLoc(0));
        }

        public Coordinate previous() {
            return previous;
        }

        public Coordinate next(int width, int ledgerLine) {
            previous = new Coordinate(next.x, lineLoc(ledgerLine));
            next = new Coordinate(next.x + width, lineLoc(ledgerLine));
            return previous;
        }

        private int lineLoc(int i) {
            return staff.y + 50 + (10 * (-i));
        }
    }
}