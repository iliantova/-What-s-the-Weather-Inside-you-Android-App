package com.psychoapp.iliev.psychoapp;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.psychoapp.iliev.psychoapp.dummy.Helpers.BackGroundChanger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivity extends FragmentActivity {

    QuizQuestionsPagerAdapter mQuizQuestionsPagerAdapter;

    public int currentScore;

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.pager)
    ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

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
