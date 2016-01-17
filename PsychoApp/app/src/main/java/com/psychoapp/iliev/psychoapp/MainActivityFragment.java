package com.psychoapp.iliev.psychoapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // set view adapters if needed here and then return the populated view

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        Toast.makeText(getContext(), "on Activity Down", Toast.LENGTH_SHORT).show();

                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                        Toast.makeText(getContext(),
                                "IMa event da ne powqrwash",
                                Toast.LENGTH_SHORT).show();

                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });
        LinearLayout listView = (LinearLayout) view.findViewById(R.id.info_drag);


        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });



        //gestureDetector = new GestureDetector(getContext(),getListViewOnTouchListener());
    return view;
    }

}
