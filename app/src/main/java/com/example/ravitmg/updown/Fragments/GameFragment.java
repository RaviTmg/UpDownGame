package com.example.ravitmg.updown.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.ravitmg.updown.GameActivity;
import com.example.ravitmg.updown.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends DialogFragment {


    String action;
    Button start;
    TextView category;
    public GameFragment(){

    }

    public void setAction(String action){
        this.action =action;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        start = view.findViewById(R.id.btn_start);
        category = view.findViewById(R.id.tv_play_game_cat);

        category.setText("play game for " + action);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), GameActivity.class);
                intent.putExtra("CATEGORY",action);
                startActivity(intent);
            }
        });

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
