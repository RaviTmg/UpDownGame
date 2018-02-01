package com.example.ravitmg.updown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ravitmg.updown.Fragments.GameFragment;

public class MainActivity extends AppCompatActivity {
    GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameFragment = new GameFragment();
        findViewById(R.id.rel_category1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameFragment.setAction("Songs");
                gameFragment.show(getSupportFragmentManager(), "GameFragment");
                //startActivity(new Intent(MainActivity.this,GameActivity.class));
            }
        });

        findViewById(R.id.rel_category2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameFragment.setAction("Artists");
                gameFragment.show(getSupportFragmentManager(), "GameFragment");
            }
        });

    }


}
