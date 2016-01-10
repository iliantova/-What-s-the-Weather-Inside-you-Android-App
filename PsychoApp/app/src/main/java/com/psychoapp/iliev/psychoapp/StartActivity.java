package com.psychoapp.iliev.psychoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;

public class StartActivity extends AppCompatActivity {

    private final int BACKGROUND_IMAGES_NUM = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       toolbar.setLogo(R.drawable.logo);

        ProportionalImageView v = (ProportionalImageView) findViewById(R.id.background_image);
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
