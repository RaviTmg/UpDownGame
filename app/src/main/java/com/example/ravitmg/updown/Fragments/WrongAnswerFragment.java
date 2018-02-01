package com.example.ravitmg.updown.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.ravitmg.updown.MainActivity;
import com.example.ravitmg.updown.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WrongAnswerFragment extends DialogFragment {


    public WrongAnswerFragment() {
        // Required empty public constructor
    }

    Button restart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_wrong_answer, container, false);
        restart = view.findViewById(R.id.btn_restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }
        });
        return view;
    }

}
