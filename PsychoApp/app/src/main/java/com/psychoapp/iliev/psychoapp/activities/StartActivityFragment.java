package com.psychoapp.iliev.psychoapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.psychoapp.iliev.psychoapp.common.PostDataTask;
import com.psychoapp.iliev.psychoapp.interfaces.FragmentChangeListener;
import com.psychoapp.iliev.psychoapp.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartActivityFragment extends Fragment {

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


        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/simonettaitalic.ttf");
        ((TextView) _calendarButtonText).setTypeface(face);
        ((TextView) _quizButtonText).setTypeface(face);
        ((TextView) _chartButtonText).setTypeface(face);
        ((TextView) _lastResultButtonText).setTypeface(face);

        View.OnClickListener skipFrag = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curentId = v.getId();
                if (curentId == _calendarButton.getId()) {

                } else if (curentId == _quizButton.getId()) {
                    Intent intent = new Intent(getActivity(), QuizActivity.class);
                    startActivity(intent);

                } else if (curentId == _chartButton.getId()) {

                    try {
                        PostDataTask.main();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (curentId == _lastResultButton.getId()) {

                    showOtherFragment();
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


