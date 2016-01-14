package com.psychoapp.iliev.psychoapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivityFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    public static final List<String> answersList = new ArrayList<String>();
    public static final List<String> questionsList = new ArrayList<String>();

    @Bind(R.id.btn_answeroption_1) AppCompatButton _btn_option_1;
    @Bind(R.id.btn_answeroption_2) AppCompatButton _btn_option_2;
    @Bind(R.id.btn_answeroption_3) AppCompatButton _btn_option_3;
    @Bind(R.id.btn_answeroption_4) AppCompatButton _btn_option_4;

    @Bind(R.id.tv_question) TextView _tv_question;

    @Bind(R.id.tv_skip_option) TextView _tv_skip_option;
    @Bind(R.id.tv_previous_option) TextView _tv_previous_option;
    @Bind(R.id.tv_next_option) TextView _tv_next_option;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        final Bundle args = getArguments();
        ButterKnife.bind(this, rootView);

        // use this for custom font importing to selected UI elements
        // fonts are situated in assets/fonts
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(), "fonts/simonettaitalic.ttf");



        _btn_option_1.setTypeface(face);
        _btn_option_2.setTypeface(face);
        _btn_option_3.setTypeface(face);
        _btn_option_4.setTypeface(face);
        _tv_question.setTypeface(face);

        _tv_skip_option.setTypeface(face);
        _tv_previous_option.setTypeface(face);
        _tv_next_option.setTypeface(face);

        // 40 hardcored answers (4 x 10)
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

        // 10 hardcored questions test
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

        _tv_question.setText(questionsList.get(args.getInt(ARG_OBJECT) - 1));

        _btn_option_1.setText(answersList.get((args.getInt(ARG_OBJECT) * 4) - 4));
        _btn_option_2.setText(answersList.get((args.getInt(ARG_OBJECT) * 4) - 3));
        _btn_option_3.setText(answersList.get((args.getInt(ARG_OBJECT) * 4) - 2));
        _btn_option_4.setText(answersList.get((args.getInt(ARG_OBJECT) * 4) - 1));

        // I am using this so i can track which drawable resource is set on the onClickEvent
        _btn_option_1.setTag(R.drawable.background_with_shadow);
        _btn_option_2.setTag(R.drawable.background_with_shadow);
        _btn_option_3.setTag(R.drawable.background_with_shadow);
        _btn_option_4.setTag(R.drawable.background_with_shadow);

        View.OnClickListener changeFrag = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentId = v.getId();
                // First we check if the user haven't clicked on the LAST 10th question
                if (args.getInt(ARG_OBJECT) != 10) {
                    if (((Integer) v.getTag()).intValue() == R.drawable.background_with_shadow) {
                        v.setBackgroundResource(R.drawable.background_with_shadow_lighter);
                        v.setTag(R.drawable.background_with_shadow_lighter);

                        // reset the old selected button option wiht the default style
                        // TODO here we should change the result in our DB as well
                        if (currentId != _btn_option_1.getId() &&
                                ((Integer) _btn_option_1.getTag()).intValue() != R.drawable.background_with_shadow) {
                            _btn_option_1.setBackgroundResource(R.drawable.background_with_shadow);
                            _btn_option_1.setTag(R.drawable.background_with_shadow);
                        }
                        if (currentId != _btn_option_2.getId() &&
                                ((Integer) _btn_option_2.getTag()).intValue() != R.drawable.background_with_shadow) {
                            _btn_option_2.setBackgroundResource(R.drawable.background_with_shadow);
                            _btn_option_2.setTag(R.drawable.background_with_shadow);
                        }
                        if (currentId != _btn_option_3.getId() &&
                                ((Integer) _btn_option_3.getTag()).intValue() != R.drawable.background_with_shadow) {
                            _btn_option_3.setBackgroundResource(R.drawable.background_with_shadow);
                            _btn_option_3.setTag(R.drawable.background_with_shadow);
                        }
                        if (currentId != _btn_option_4.getId() &&
                                ((Integer) _btn_option_4.getTag()).intValue() != R.drawable.background_with_shadow) {
                            _btn_option_4.setBackgroundResource(R.drawable.background_with_shadow);
                            _btn_option_4.setTag(R.drawable.background_with_shadow);
                        }

                    }
                    else {
                        v.setBackgroundResource(R.drawable.background_with_shadow);
                        v.setTag(R.drawable.background_with_shadow);
                    }

                    // THIS is the code that actually change the current fragment with the next qustion fragment
                    // _viewPager is the container element (in the activity) in which the new fragment is loaded
                    QuizActivity activity = (QuizActivity) getActivity();
                    activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT), true);
                } else {
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    String frag = "fragment_result";
                    intent.putExtra("fragment", frag);
                    startActivity(intent);
                }
            }
        };

        View.OnClickListener skipFrag = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentId = v.getId();
                if (currentId == _tv_skip_option.getId()) {
                    if (args.getInt(ARG_OBJECT) != 10) {
                        QuizActivity activity = (QuizActivity) getActivity();
                        activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT), true);
                    } else {
                        Intent intent = new Intent(getActivity(), StartActivity.class);
                        String frag = "fragment_result";
                        intent.putExtra("fragment", frag);
                        startActivity(intent);
                    }
                } else if (currentId == _tv_next_option.getId()) {
                    if (args.getInt(ARG_OBJECT) != 10) {
                        QuizActivity activity = (QuizActivity) getActivity();
                        activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT), true);
                    } else {
                        Intent intent = new Intent(getActivity(), StartActivity.class);
                        String frag = "fragment_result";
                        intent.putExtra("fragment", frag);
                        startActivity(intent);
                    }
                } else {
                    if (args.getInt(ARG_OBJECT) != 1) {
                        QuizActivity activity = (QuizActivity) getActivity();
                        activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT) - 1, true);
                    } else {
                        Intent intent = new Intent(getActivity(), StartActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };

        _btn_option_1.setOnClickListener(changeFrag);
        _btn_option_2.setOnClickListener(changeFrag);
        _btn_option_3.setOnClickListener(changeFrag);
        _btn_option_4.setOnClickListener(changeFrag);

        _tv_skip_option.setOnClickListener(skipFrag);
        _tv_next_option.setOnClickListener(skipFrag);
        _tv_previous_option.setOnClickListener(skipFrag);

        return rootView;
    }
}
