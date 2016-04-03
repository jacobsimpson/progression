package com.progression;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.progression.staff.Entry;
import com.progression.staff.Key;
import com.progression.staff.StaffView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.progression.staff.Note.A5_Q;
import static com.progression.staff.Note.B5_Q;
import static com.progression.staff.Note.C5_Q;
import static com.progression.staff.Note.D4_Q;
import static com.progression.staff.Note.D5_Q;
import static com.progression.staff.Note.E4_Q;
import static com.progression.staff.Note.F4_Q;
import static com.progression.staff.Note.G4_Q;
import static com.progression.staff.Slur.slur;

public class MainActivity extends AppCompatActivity {
    private static final List<Entry[]> PATTERNS = new ArrayList<>();

    static {
        // Two note patterns.
        PATTERNS.add(new Entry[]{slur(F4_Q, G4_Q), slur(G4_Q, A5_Q), slur(A5_Q, B5_Q), slur(B5_Q, C5_Q)});
        PATTERNS.add(new Entry[]{F4_Q, F4_Q, G4_Q, G4_Q, A5_Q, A5_Q, B5_Q, B5_Q});
        PATTERNS.add(new Entry[]{slur(F4_Q, E4_Q), slur(G4_Q, F4_Q), slur(A5_Q, G4_Q), slur(B5_Q, A5_Q)});
        PATTERNS.add(new Entry[]{F4_Q, A5_Q, G4_Q, B5_Q, A5_Q, C5_Q, B5_Q, D5_Q});

        // Three note patterns.
        PATTERNS.add(new Entry[]{slur(F4_Q, G4_Q, A5_Q), slur(G4_Q, A5_Q, B5_Q), slur(A5_Q, B5_Q, C5_Q), slur(B5_Q, C5_Q, D5_Q)});
        PATTERNS.add(new Entry[]{slur(F4_Q, E4_Q, D4_Q), slur(G4_Q, F4_Q, E4_Q), slur(A5_Q, G4_Q, F4_Q), slur(B5_Q, A5_Q, G4_Q)});
        PATTERNS.add(new Entry[]{slur(F4_Q, G4_Q, E4_Q), slur(G4_Q, A5_Q, F4_Q), slur(A5_Q, B5_Q, G4_Q), slur(B5_Q, C5_Q, A5_Q)});
        PATTERNS.add(new Entry[]{slur(F4_Q, E4_Q, F4_Q), slur(G4_Q, F4_Q, G4_Q), slur(A5_Q, G4_Q, A5_Q), slur(B5_Q, A5_Q, B5_Q)});
    }

    private static final List<Key> SCALES = new ArrayList<>();

    static {
        SCALES.addAll(Arrays.asList(Key.values()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // As long as the app is active, keep the screen on so it doesn't
        // timeout and go blank while practicing.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        updateExercise();

        final Button btnNext = (Button) findViewById(R.id.btnNext);
        if (btnNext != null) {
            btnNext.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    updateExercise();
                }
            });
        }

    }

    private void updateExercise() {
        final Random random = new Random();
        Key scale = SCALES.get(random.nextInt(SCALES.size()));

        final TextView txtScale = (TextView) findViewById(R.id.txtScale);
        if (txtScale != null) {
            txtScale.setText(scale.toString());
        }

        final StaffView staffScaleSignature = (StaffView) findViewById(R.id.staffScaleSignature);
        if (staffScaleSignature != null) {
            staffScaleSignature.withKey(scale);
        }

        final StaffView staffPattern = (StaffView) findViewById(R.id.staffPattern);
        if (staffPattern != null) {
            staffPattern.withMusic(PATTERNS.get(random.nextInt(PATTERNS.size())));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
