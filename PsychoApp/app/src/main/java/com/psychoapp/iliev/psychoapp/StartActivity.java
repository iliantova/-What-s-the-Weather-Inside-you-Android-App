package com.psychoapp.iliev.psychoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.psychoapp.iliev.psychoapp.dummy.BackGroundChanger;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements FragmentChangeListener {

    private final int BACKGROUND_IMAGES_NUM = 8;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Bind(R.id.fragment_start_conteiner) LinearLayout _ll_frag_start;
    @Bind(R.id.toolbar) Toolbar _tb_toolbar;
    @Bind(R.id.background_image) ProportionalImageView _pim_background_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_pim_background_image);

        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragment");
        String frag = "fragment_result";
        if (fragmentName != null && fragmentName.equals(frag)) {
            ResultFragment firstFragment = new ResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_start_conteiner, firstFragment).commit();
        }

        StartActivityFragment firstFragment = new StartActivityFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_start_conteiner, firstFragment).commit();

        setSupportActionBar(_tb_toolbar);
        _tb_toolbar.setLogo(R.drawable.logo);
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

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_start_conteiner, fragment,DETAILFRAGMENT_TAG);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
