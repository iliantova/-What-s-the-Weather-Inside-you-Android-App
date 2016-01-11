package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartActivityFragment extends Fragment {

    public StartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Context context = getActivity();

     Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/simonettaitalic.ttf");
        ((TextView) view.findViewById(R.id.calendar_button_text)).setTypeface(face);
        ((TextView) view.findViewById(R.id.quiz_button_text)).setTypeface(face);
        ((TextView) view.findViewById(R.id.chart_button_text)).setTypeface(face);
        ((TextView) view.findViewById(R.id.last_result_button_text)).setTypeface(face);

        LinearLayout calendarButton = (LinearLayout) view.findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new StartActivityFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}
