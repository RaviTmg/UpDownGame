package com.example.ravitmg.updown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravitmg.updown.Fragments.WrongAnswerFragment;
import com.example.ravitmg.updown.Model.Song;
import com.example.ravitmg.updown.Utilities.JsonDataUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    TextView name1, name2;
    Button before, after;
    TextView releasedate;
    TextView textScore;

    ArrayList<Song> songArrayList = new ArrayList<>();

    Song song1, song2;
    int index = 0;
    int score = 0;

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Bundle bundle = getIntent().getExtras();
        category = bundle.getString("CATEGORY");
        if (category != null) {
            Toast.makeText(GameActivity.this, category, Toast.LENGTH_LONG).show();
        }
        initViews();
        collectDataFromServer(category);


        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer("before");
            }
        });

        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer("after");
            }
        });
    }

    private void collectDataFromServer(String category) {
        DatabaseReference db = dbref.child(category);
        db.orderByChild("prediction").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Song song = data.getValue(Song.class);
                    Log.d("received data", song.getName());
                    songArrayList.add(song);


                }
                getSongs(index);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getSongs(int x) {
        Log.e("songarray", songArrayList.size() + "");

        if (song1 == null) {
            song1 = songArrayList.get(x);
        } else {
            song1 = song2;
        }
        song2 = songArrayList.get(x + 1);


        String release = String.valueOf(song1.getRelease().get("day")) + "/" +
                String.valueOf(song1.getRelease().get("month")) + "/" +
                String.valueOf(song1.getRelease().get("year"));
        name1.setText(song1.getName());
        releasedate.setText(release);

        name2.setText(song2.getName());

        textScore.setText(String.valueOf(score));
    }

    private void checkAnswer(String status) {
        String answer = isBefore() ? "before" : "after";


        Log.d("attempted", song1.getAttempted() + "");
        if (status.equals(answer)) {
            score++;
            if (index + 2 < songArrayList.size()) {
                index++;
                getSongs(index);
                song1.setAttempted(song1.getAttempted() + 1);
                song1.setCorrectlyattempted(song1.getCorrectlyattempted() + 1);
                try {
                    song1.setPrediction((song1.getCorrectlyattempted() * 100) / song1.getAttempted());

                } catch (ArithmeticException e) {
                    song1.setPrediction((song1.getCorrectlyattempted() * 100));
                }
                dbref.child(category).child(song1.getSid()).setValue(song1);

            } else {
                Toast.makeText(GameActivity.this, "limit of qn reached", Toast.LENGTH_LONG).show();
                index = 0;
            }


        } else {
            song2.setAttempted(song2.getAttempted() + 1);
            try {
                song2.setPrediction((song2.getCorrectlyattempted() * 100) / song2.getAttempted());

            } catch (ArithmeticException e) {
                song2.setPrediction((song2.getCorrectlyattempted() * 100));
            }
            dbref.child(category).child(song2.getSid()).setValue(song2);
            WrongAnswerFragment wrongAnswerFragment = new WrongAnswerFragment();
            wrongAnswerFragment.show(getSupportFragmentManager(), "WrongAnswer");
        }

    }

    private boolean isBefore() {
        boolean before = false;
        if (song1.getRelease().get("year") > song2.getRelease().get("year")) {
            before = true;
        } else if (song1.getRelease().get("year") == song2.getRelease().get("year")) {
            if (song1.getRelease().get("month") > song2.getRelease().get("month")) {
                before = true;
            } else if (song1.getRelease().get("month") == song2.getRelease().get("month")) {
                if (song1.getRelease().get("day") >= song2.getRelease().get("day"))
                    before = true;
            }
        }
        return before;
    }

    private void initViews() {
        name1 = findViewById(R.id.tv_songname1);
        name2 = findViewById(R.id.tv_songname2);
        before = findViewById(R.id.btn_before);
        after = findViewById(R.id.btn_after);
        releasedate = findViewById(R.id.tv_releasedate1);
        textScore = findViewById(R.id.tv_score);
    }

}
