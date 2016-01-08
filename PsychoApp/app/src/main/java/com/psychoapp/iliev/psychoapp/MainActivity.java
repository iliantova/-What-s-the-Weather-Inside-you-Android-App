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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int BACKGROUND_IMAGES_NUM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // use this for custom font importing to selected UI elements
        // fonts are situated in assets/fonts
        Button btn1 = (Button) findViewById(R.id.action_login);
        Button btn2 = (Button) findViewById(R.id.action_quiz);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        btn1.setTypeface(face);
        btn2.setTypeface(face);


        // set random background image for each new app load
        // add quality backgrounds in drawable and refactor the BACKGROUND_IMAGES_NUM + switch/case
        ProportionalImageView v = (ProportionalImageView) findViewById(R.id.background_image);
        Random r = new Random();
        int randomInt = r.nextInt(BACKGROUND_IMAGES_NUM);
        int res;
        switch (randomInt) {
            case 0 : res = R.drawable.b; break;
            case 1 : res = R.drawable.c; break;
            case 2 : res = R.drawable.aquarell_night_400_645; break;
            default: res = R.drawable.aquarell_night_400_645; break;
        }
        v.setBackgroundResource(res);

        //the bottom floating menu
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
