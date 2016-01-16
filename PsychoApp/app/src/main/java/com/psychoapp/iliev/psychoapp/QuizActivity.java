package com.psychoapp.iliev.psychoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.psychoapp.iliev.psychoapp.dummy.Helpers.BackGroundChanger;
import com.psychoapp.iliev.psychoapp.dummy.Helpers.DataParser;
import com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers.HttpServerResponseTask;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivity extends FragmentActivity {

    QuizQuestionsPagerAdapter mQuizQuestionsPagerAdapter;

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.pager) ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

        final String token = "Bearer Rqj7TTXLIX7eSQelGqMc0lxdUlZOjisBtgEAdjZ1y4SE7OO5YCmEJo7vlScIWD4TeUGnuMS33oLPwEcVfPdx6Tmv5OJE6";

        HttpServerResponseTask receiveQuestionsRTask = new HttpServerResponseTask();
        receiveQuestionsRTask.execute("QUESTIONS_10_RANDOM_PARAMS", token);

        // this try/catch will get the response result with get()
        String[] result = null;
        try {
            result = receiveQuestionsRTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("QUESTIONS GET", result[0]);
        Log.e("QUESTIONS RESULT", result[1]);

//        String resultQuestions = null;
//        try {
//            resultQuestions = DataParser.getQuestionsFromJsonString(result[1], 10);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.e("Question #", resultQuestions);

        // Create an adapter that when requested, will return a fragment representing an object in the collection.
        // ViewPager and its adapters use support library fragments, so we must use getSupportFragmentManager.
        mQuizQuestionsPagerAdapter = new QuizQuestionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager, attaching the adapter.
        _viewPager.setAdapter(mQuizQuestionsPagerAdapter);
    }

    public static class QuizQuestionsPagerAdapter extends FragmentStatePagerAdapter {

        public QuizQuestionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // set here the data to be bind in fragment_quiz for each question
        @Override
         public Fragment getItem(int position) {
            Fragment fragment = new QuizActivityFragment();
            Bundle args = new Bundle();

            // here we shoulld pass the questions and answers from the server (instead of i)
            args.putInt(QuizActivityFragment.ARG_OBJECT, position + 1); // Our object is just an integer :-P
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 10-object collection.
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Question #" + (position + 1);
        }
    }

}
