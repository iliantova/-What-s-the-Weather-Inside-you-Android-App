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
    @Bind(R.id.action_camera) Button _btn_camera;

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
        _btn_camera.setTypeface(face);
        _btn_login.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _btn_quiz.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _btn_camera.setShadowLayer(10, 0, 0, R.color.themeGreenDark);

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

        _btn_login.setOnClickListener(mButtonsListener);
        _btn_quiz.setOnClickListener(mButtonsListener);
        _btn_camera.setOnClickListener(mButtonsListener);

    }

    private View.OnClickListener mButtonsListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.action_login:
                    Intent newLoginIntent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(newLoginIntent);
                    break;
                case R.id.action_quiz:
                    Intent newQuizIntent = new Intent(v.getContext(), StartActivity.class);
                    startActivity(newQuizIntent);
                    break;
                case R.id.action_camera:
                    Intent newCameraIntent = new Intent(v.getContext(), CameraActivity.class);
                    startActivity(newCameraIntent);
                    break;
                default:
                    break;
            }
        }
    };
}
