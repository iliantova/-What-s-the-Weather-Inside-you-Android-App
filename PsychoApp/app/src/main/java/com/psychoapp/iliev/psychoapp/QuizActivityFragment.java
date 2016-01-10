package com.psychoapp.iliev.psychoapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivityFragment extends Fragment {

    public static final String ARG_OBJECT = "Question";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        Bundle args = getArguments();

        ((TextView) rootView.findViewById(R.id.tv_question))
                .setText("Sample Question #" + Integer.toString(args.getInt(ARG_OBJECT)));

        ((Button) rootView.findViewById(R.id.btn_answeroption_1)).setText("Answer 1");
        ((Button) rootView.findViewById(R.id.btn_answeroption_2)).setText("Answer 2");
        ((Button) rootView.findViewById(R.id.btn_answeroption_3)).setText("Answer 3");
        ((Button) rootView.findViewById(R.id.btn_answeroption_4)).setText("Answer 4");

        return rootView;
    }
}
