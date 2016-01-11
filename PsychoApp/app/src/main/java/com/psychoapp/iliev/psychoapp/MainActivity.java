package com.psychoapp.iliev.psychoapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.psychoapp.iliev.psychoapp.dummy.Helpers;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.action_login) Button _btn_login;
    @Bind(R.id.action_quiz) Button _btn_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Helpers.backgroundRandomizer(_background);

        // use this for custom font importing to selected UI elements
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        _btn_login.setTypeface(face);
        _btn_quiz.setTypeface(face);
        _btn_login.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _btn_quiz.setShadowLayer(10, 0, 0, R.color.themeGreenDark);

        /*//the bottom floating menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // fade in animation for background image (can use fadeout as well)
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        _background.startAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        // set onClickListeners for the buttons
        _btn_login.setOnClickListener(mButtonsListener);
        _btn_quiz.setOnClickListener(mButtonsListener);

    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mButtonsListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            // Yes we will handle click here but which button clicked??? We don't know

            // So we will make
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.action_login:

                    // do something when the action_login is clicked
                    Intent newLoginIntent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(newLoginIntent);

                    break;
                case R.id.action_quiz:

                    // do something when the action_quiz is clicked
                    Intent newQuizIntent = new Intent(v.getContext(), QuizActivity.class);
                    startActivity(newQuizIntent);
                    break;
                default:
                    break;
            }
        }
    };
}
