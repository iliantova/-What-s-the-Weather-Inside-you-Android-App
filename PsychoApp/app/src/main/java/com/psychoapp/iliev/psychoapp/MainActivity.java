package com.psychoapp.iliev.psychoapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int BACKGROUNDS_IMAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use this for custom font importing to UI elements
        Button btn1 = (Button) findViewById(R.id.action_login);
        Button btn2 = (Button) findViewById(R.id.action_quiz);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        btn1.setTypeface(face);
        btn2.setTypeface(face);

        // set random background image for each new load
        View v = (View) findViewById(R.id.background_image);
        Random r = new Random();
        int randomInt = r.nextInt(BACKGROUNDS_IMAGES);
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
