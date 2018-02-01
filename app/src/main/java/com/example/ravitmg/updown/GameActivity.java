package com.example.ravitmg.updown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravitmg.updown.Fragments.WrongAnswerFragment;
import com.example.ravitmg.updown.Model.Song;
import com.example.ravitmg.updown.Utilities.JsonDataUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("CATEGORY");
        if (category != null) {
            Toast.makeText(GameActivity.this, category, Toast.LENGTH_LONG).show();
        }
        initViews();
        songArrayList = JsonDataUtil.getJsonForGame(GameActivity.this, category + ".json");

        getSongs(index);


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

    private void getSongs(int x) {


        if (song1 == null) {
            song1 = songArrayList.get(x);
        } else {
            song1 = song2;
        }
        song2 = songArrayList.get(x + 1);
        String release = String.valueOf(song1.getReldate()) + "/" +
                String.valueOf(song1.getRelmon()) + "/" +
                String.valueOf(song1.getRelyr());
        name1.setText(song1.getName());
        releasedate.setText(release);

        name2.setText(song2.getName());

        textScore.setText(String.valueOf(score));
    }

    private void checkAnswer(String status) {
        String answer = isBefore() ? "before" : "after";
        if (status.equals(answer)) {
            score++;
            if (index + 2 < songArrayList.size()) {
                index++;
                getSongs(index);
                song2.setPrediction(song2.getPrediction() + 1);


            } else {
                /*Toast.makeText(GameActivity.this, "limit of qn reached", Toast.LENGTH_LONG).show();*/
                index = 0;
            }


        } else {
            WrongAnswerFragment wrongAnswerFragment = new WrongAnswerFragment();
            wrongAnswerFragment.show(getSupportFragmentManager(), "WrongAnswer");
        }

    }

    private boolean isBefore() {
        boolean before = false;
        if (song1.getRelyr() > song2.getRelyr()) {
            before = true;
        } else if (song1.getRelyr() == song2.getRelyr()) {
            if (song1.getRelmon() > song2.getRelmon()) {
                before = true;
            } else if (song1.getRelmon() == song2.getRelmon()) {
                if (song1.getReldate() >= song2.getReldate())
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
