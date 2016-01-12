package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartActivityFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    @Bind(R.id.calendar_button) LinearLayout _calendarButton;
    @Bind(R.id.quiz_button) LinearLayout _quizButton;
    @Bind(R.id.chart_button) LinearLayout _chartButton;
    @Bind(R.id.last_result_button) LinearLayout _lastResultButton;

    @Bind(R.id.calendar_button_text) TextView _calendarButtonText;
    @Bind(R.id.quiz_button_text) TextView _quizButtonText;
    @Bind(R.id.chart_button_text) TextView _chartButtonText;
    @Bind(R.id.last_result_button_text) TextView _lastResultButtonText;

    public StartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Context context = getActivity();

        ButterKnife.bind(this, view);
        Toast.makeText(getContext(),
                "rabotq",
                Toast.LENGTH_SHORT).show();

        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/simonettaitalic.ttf");
        ((TextView) _calendarButtonText).setTypeface(face);
        ((TextView) _quizButtonText).setTypeface(face);
        ((TextView) _chartButtonText).setTypeface(face);
        ((TextView) _lastResultButtonText).setTypeface(face);

//        LinearLayout calendarButton = (LinearLayout) view.findViewById(R.id.calendar_button);
//        calendarButton.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                Fragment fragment = new StartActivityFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
        View.OnClickListener skipFrag = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curentId = v.getId();
                if (curentId == _quizButton.getId()) {
                    Toast.makeText(getContext(),
                            "This is a message displayed in a Toast",
                            Toast.LENGTH_SHORT).show();
                    showOtherFragment();
                }
                else if (curentId == _calendarButton.getId()){
                    Toast.makeText(getContext(),
                            "_quizButton",
                            Toast.LENGTH_SHORT).show();
                    showOtherFragment();
                }
                else if (curentId == _chartButton.getId()){
                    Toast.makeText(getContext(),
                            "_chartButton",
                            Toast.LENGTH_SHORT).show();
                }
                else if (curentId == _lastResultButton.getId()){
                    Toast.makeText(getContext(),
                            "_lastResultButton",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        _calendarButton.setOnClickListener(skipFrag);
        _quizButton.setOnClickListener(skipFrag);
        _chartButton.setOnClickListener(skipFrag);
        _lastResultButton.setOnClickListener(skipFrag);

        return view;
    }
    public void showOtherFragment() {
        Fragment fr = new ResultFragment();
        FragmentChangeListener fc = (FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }
    }


