package com.psychoapp.iliev.psychoapp;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizActivityFragment extends Fragment {

    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        Bundle args = getArguments();

        // use this for custom font importing to selected UI elements
        // fonts are situated in assets/fonts
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "fonts/simonettaitalic.ttf");

        ((Button) rootView.findViewById(R.id.btn_answeroption_1)).setTypeface(face);
        ((Button) rootView.findViewById(R.id.btn_answeroption_2)).setTypeface(face);
        ((Button) rootView.findViewById(R.id.btn_answeroption_3)).setTypeface(face);
        ((Button) rootView.findViewById(R.id.btn_answeroption_4)).setTypeface(face);
        ((TextView) rootView.findViewById(R.id.tv_question)).setTypeface(face);

        List<String> answersList = new ArrayList<String>();

        answersList.add("Apple");
        answersList.add("Banana");
        answersList.add("Orange");
        answersList.add("Watermellon");

        answersList.add("Red");
        answersList.add("Green");
        answersList.add("Orange");
        answersList.add("Blue");

        answersList.add("Those who seeks are those who finds!");
        answersList.add("Second best is as good as last option");
        answersList.add("Work is oveerrated");
        answersList.add("Casual is boring");

        answersList.add("5");
        answersList.add("25");
        answersList.add("120");
        answersList.add("0.5");

        answersList.add("Apple");
        answersList.add("Banana");
        answersList.add("Orange");
        answersList.add("Watermellon");

        answersList.add("Red");
        answersList.add("Green");
        answersList.add("Orange");
        answersList.add("Blue");

        answersList.add("Those who seeks are those who finds!");
        answersList.add("Second best is as good as last option");
        answersList.add("Work is oveerrated");
        answersList.add("Casual is boring");

        answersList.add("5");
        answersList.add("25");
        answersList.add("120");
        answersList.add("0.5");

        answersList.add("Apple");
        answersList.add("Banana");
        answersList.add("Orange");
        answersList.add("Watermellon");

        answersList.add("Red");
        answersList.add("Green");
        answersList.add("Orange");
        answersList.add("Blue");

        answersList.add("Those who seeks are those who finds!");
        answersList.add("Second best is as good as last option");
        answersList.add("Work is oveerrated");
        answersList.add("Casual is boring");

        answersList.add("5");
        answersList.add("25");
        answersList.add("120");
        answersList.add("0.5");


        List<String> questionsList = new ArrayList<String>();
        questionsList.add("Which one is your favorite!?");
        questionsList.add("Which one is your least favorite?");
        questionsList.add("Which statements is your favorite?");
        questionsList.add("If x equals 5 give answer to x! ");
        questionsList.add("Which one is your favorite!?");
        questionsList.add("Which one is your least favorite?");
        questionsList.add("Which statements is your favorite?");
        questionsList.add("If x equals 5 give answer to x! ");
        questionsList.add("Which one is your favorite!?");
        questionsList.add("Which one is your least favorite?");

        ((TextView) rootView.findViewById(R.id.tv_question)).setText(questionsList.get(args.getInt(ARG_OBJECT) - 1));

        ((Button) rootView.findViewById(R.id.btn_answeroption_1)).setText(answersList.get((args.getInt(ARG_OBJECT) * 4 )- 4));
        ((Button) rootView.findViewById(R.id.btn_answeroption_2)).setText(answersList.get((args.getInt(ARG_OBJECT) * 4 )- 3));
        ((Button) rootView.findViewById(R.id.btn_answeroption_3)).setText(answersList.get((args.getInt(ARG_OBJECT) * 4 )- 2));
        ((Button) rootView.findViewById(R.id.btn_answeroption_4)).setText(answersList.get((args.getInt(ARG_OBJECT) * 4 )- 1));

        return rootView;
    }
}
