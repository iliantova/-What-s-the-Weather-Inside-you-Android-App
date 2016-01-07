package com.psychoapp.iliev.psychoapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_BACKGROUNDS = 3; // or whatever
    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = findViewById(R.id.background_image);
        int res;
        int i =  mRandom.nextInt(NUM_BACKGROUNDS);
        switch (i) {
            case 0: res = R.drawable.c; break;
            case 1: res = R.drawable.b; break;
            case 2: res = R.drawable.c; break;
            default: throw new IllegalArgumentException("oops?");
        }
        v.setBackgroundResource(res);

        // use this for custom font importing to UI elements
        Button btn1 = (Button) findViewById(R.id.action_login);
        Button btn2 = (Button) findViewById(R.id.action_quiz);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/vladimir.ttf");
        btn1.setTypeface(face);
        btn2.setTypeface(face);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            return true;
        }
        else if (id == R.id.action_quiz)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
