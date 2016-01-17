package com.psychoapp.iliev.psychoapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psychoapp.iliev.psychoapp.helpers.HttpDataHelper;
import com.psychoapp.iliev.psychoapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivityFragment extends Fragment {

    public static final String ARG_OBJECT = "object";

    public static List<String> responseData = new ArrayList<String>();

    @Bind(R.id.btn_answeroption_1) AppCompatButton _btn_option_1;
    @Bind(R.id.btn_answeroption_2) AppCompatButton _btn_option_2;
    @Bind(R.id.btn_answeroption_3) AppCompatButton _btn_option_3;
    @Bind(R.id.btn_answeroption_4) AppCompatButton _btn_option_4;

    @Bind(R.id.tv_question) TextView _tv_question;

    @Bind(R.id.tv_skip_option) TextView _tv_skip_option;
    @Bind(R.id.tv_previous_option) TextView _tv_previous_option;
    @Bind(R.id.tv_next_option) TextView _tv_next_option;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        final Bundle args = getArguments();
        ButterKnife.bind(this, rootView);

        QuizActivity parent = (QuizActivity) getActivity();

        HttpDataHelper receiveQuestionsRTask = new HttpDataHelper();
        receiveQuestionsRTask.execute("QUESTIONS_10_RANDOM_PARAMS", parent.usertkn);

        try {
            responseData = receiveQuestionsRTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/simonettaitalic.ttf");
        _btn_option_1.setTypeface(face);
        _btn_option_2.setTypeface(face);
        _btn_option_3.setTypeface(face);
        _btn_option_4.setTypeface(face);
        _tv_question.setTypeface(face);
        _tv_skip_option.setTypeface(face);
        _tv_previous_option.setTypeface(face);
        _tv_next_option.setTypeface(face);

        int index = args.getInt(ARG_OBJECT) - 1;
        _tv_question.setText(responseData.get(index * 9));

        _btn_option_1.setText(responseData.get((index * 9) + 1));
        _btn_option_1.setTag(R.id.TAG_BTN_VALUE, responseData.get((index * 9) + 2));

        _btn_option_2.setText(responseData.get((index * 9) + 3));
        _btn_option_2.setTag(R.id.TAG_BTN_VALUE, responseData.get((index * 9) + 4));

        _btn_option_3.setText(responseData.get((index * 9) + 5));
        _btn_option_3.setTag(R.id.TAG_BTN_VALUE, responseData.get((index * 9) + 6));

        _btn_option_4.setText(responseData.get((index * 9) + 7));
        _btn_option_4.setTag(R.id.TAG_BTN_VALUE, responseData.get((index * 9) + 8));

        _btn_option_1.setTag(R.drawable.background_with_shadow);
        _btn_option_2.setTag(R.drawable.background_with_shadow);
        _btn_option_3.setTag(R.drawable.background_with_shadow);
        _btn_option_4.setTag(R.drawable.background_with_shadow);

        View.OnClickListener changeFrag = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentId = v.getId();

                QuizActivity parent = (QuizActivity) getActivity();
                parent.currentScore += Integer.valueOf(v.getTag(R.id.TAG_BTN_VALUE).toString());

                Log.e("Current score VALUE : ", String.valueOf(parent.currentScore));

                // First we check if the user haven't clicked on the LAST 10th question
                if (args.getInt(ARG_OBJECT) != 10) {
                    if (((Integer) v.getTag()).intValue() == R.drawable.background_with_shadow) {
                        v.setBackgroundResource(R.drawable.background_with_shadow_lighter);
                        v.setTag(R.drawable.background_with_shadow_lighter);

                        // reset the old selected button option wiht the default style
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

                    } else {
                        v.setBackgroundResource(R.drawable.background_with_shadow);
                        v.setTag(1, R.drawable.background_with_shadow);
                    }

                    QuizActivity activity = (QuizActivity) getActivity();
                    activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT), true);

                } else {
                    // this will execute when the last questions is answered
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    String frag = "fragment_result";
                    intent.putExtra("fragment", frag);

                    String finalQuizResult = String.valueOf(parent.currentScore);
                    intent.putExtra("score", finalQuizResult);

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

                        startActivity(intent);
                    }
                } else if (currentId == _tv_next_option.getId()) {
                    if (args.getInt(ARG_OBJECT) != 10) {
                        QuizActivity activity = (QuizActivity) getActivity();
                        activity._viewPager.setCurrentItem(args.getInt(ARG_OBJECT), true);
                    } else {
                        Intent intent = new Intent(getActivity(), StartActivity.class);
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
