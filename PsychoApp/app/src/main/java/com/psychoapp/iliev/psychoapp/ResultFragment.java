package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ResultFragment extends Fragment {

    public ResultFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        Context context = getActivity();

        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/simonettaitalic.ttf");
        ((TextView) view.findViewById(R.id.result_text)).setTypeface(face);
        ((TextView) view.findViewById(R.id.result_think)).setTypeface(face);

        return view;
    }
}
