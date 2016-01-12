package com.psychoapp.iliev.psychoapp;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.psychoapp.iliev.psychoapp.dummy.Helpers;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivity extends FragmentActivity {

    QuizQuestionsPagerAdapter mQuizQuestionsPagerAdapter;
    private static final String RESULTFRAGMENT_TAG = "RSTAG";

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.pager) ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        Helpers.backgroundRandomizer(_background);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
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
         public Fragment getItem(int i) {
            Fragment fragment = new QuizActivityFragment();
            Bundle args = new Bundle();
            args.putInt(QuizActivityFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
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
