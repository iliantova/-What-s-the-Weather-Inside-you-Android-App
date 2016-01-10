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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends FragmentActivity {

    ViewPager mViewPager;
    QuizQuestionsPagerAdapter mQuizQuestionsPagerAdapter;

    private static final int BACKGROUND_IMAGES_NUM = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // set random background image for each new app load
        // add quality backgrounds in drawable and refactor the BACKGROUND_IMAGES_NUM + switch/case
//        final ProportionalImageView v = (ProportionalImageView) findViewById(R.id.background_image);
//        Random r = new Random();
//        int randomInt = r.nextInt(BACKGROUND_IMAGES_NUM);
//        int res;
//        switch (randomInt) {
//            case 0 : res = R.drawable.c; break;
//            case 1 : res = R.drawable.d; break;
//            case 2 : res = R.drawable.e; break;
//            case 3 : res = R.drawable.f; break;
//            case 4 : res = R.drawable.g; break;
//            case 5 : res = R.drawable.h; break;
//            case 6 : res = R.drawable.j; break;
//            case 7 : res = R.drawable.aquarell_night_400_655; break;
//            default: res = R.drawable.aquarell_night_400_655; break;
//        }
//        v.setBackgroundResource(res);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        //
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mQuizQuestionsPagerAdapter = new QuizQuestionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mQuizQuestionsPagerAdapter);

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
            return "Qustion #" + (position + 1);
        }
    }
}
