package com.psychoapp.iliev.psychoapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.psychoapp.iliev.psychoapp.helpers.BackGroundChanger;
import com.psychoapp.iliev.psychoapp.customs.ProportionalImageView;
import com.psychoapp.iliev.psychoapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivity extends FragmentActivity {

    public static final String USER_PREFERENCES = "userPreferences";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";

    QuizQuestionsPagerAdapter mQuizQuestionsPagerAdapter;

    public int currentScore;
    public String usertkn;

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.pager) ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
//        mediaPlayer.start();

        SharedPreferences prefs = this.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        usertkn = prefs.getString(TOKEN, null);

        mQuizQuestionsPagerAdapter = new QuizQuestionsPagerAdapter(getSupportFragmentManager());

        _viewPager.setAdapter(mQuizQuestionsPagerAdapter);
    }

    public static class QuizQuestionsPagerAdapter extends FragmentStatePagerAdapter {

        public QuizQuestionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
         public Fragment getItem(int position) {
            Fragment fragment = new QuizActivityFragment();
            Bundle args = new Bundle();

            args.putInt(QuizActivityFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            // to load ten question fragments
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Question #" + (position + 1);
        }
    }

}
