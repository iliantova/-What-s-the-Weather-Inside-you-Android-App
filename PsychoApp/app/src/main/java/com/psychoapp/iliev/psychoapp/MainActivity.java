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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int BACKGROUND_IMAGES_NUM = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use this for custom font importing to selected UI elements
        // fonts are situated in assets/fonts
        Button loginButton = (Button) findViewById(R.id.action_login);
        Button signupButton = (Button) findViewById(R.id.action_quiz);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        loginButton.setTypeface(face);
        signupButton.setTypeface(face);

        // set random background image for each new app load
        // add quality backgrounds in drawable and refactor the BACKGROUND_IMAGES_NUM + switch/case
        final ProportionalImageView v = (ProportionalImageView) findViewById(R.id.background_image);
        Random r = new Random();
        int randomInt = r.nextInt(BACKGROUND_IMAGES_NUM);
        int res;
        switch (randomInt) {
            case 0 : res = R.drawable.c; break;
            case 1 : res = R.drawable.d; break;
            case 2 : res = R.drawable.e; break;
            case 3 : res = R.drawable.f; break;
            case 4 : res = R.drawable.g; break;
            case 5 : res = R.drawable.h; break;
            case 6 : res = R.drawable.j; break;
            case 7 : res = R.drawable.aquarell_night_400_655; break;
            default: res = R.drawable.aquarell_night_400_655; break;
        }
        v.setBackgroundResource(res);

        /*//the bottom floating menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // set onClickListeners for the buttons
        loginButton.setOnClickListener(mButtonsListener);
        signupButton.setOnClickListener(mButtonsListener);

        // fade in animation for background image (can use fadeout as well)
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        v.startAnimation(fadeIn);
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
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);

                    break;
                case R.id.action_quiz:

                    // do something when the action_quiz is clicked
                    Intent intent2 = new Intent(v.getContext(), StartActivity.class);
                    startActivity(intent2);
                    break;
                default:
                    break;
            }
        }
    };
}
