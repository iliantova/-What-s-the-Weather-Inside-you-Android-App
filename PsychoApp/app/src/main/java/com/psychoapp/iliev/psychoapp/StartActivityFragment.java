package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        LinearLayoutButton button1 = new LinearLayoutButton(context,R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(StartActivityFragment.this, "RelativeLayoutButton clicked", Toast.LENGTH_LONG).show();

            }
        });

        LinearLayoutButton button2 = new LinearLayoutButton(context,R.id.button2);

        button2.setText(R.id.test_button_text2, "text");
        button2.setImageResource(R.id.test_button_image, R.drawable.logo);

        LinearLayoutButton button3 = new LinearLayoutButton(context,R.id.button3);

        button3.setText(R.id.test_button_text2, "image");
        button3.setImageResource(R.id.test_button_image, R.drawable.calendar);

        return view;
    }
}
