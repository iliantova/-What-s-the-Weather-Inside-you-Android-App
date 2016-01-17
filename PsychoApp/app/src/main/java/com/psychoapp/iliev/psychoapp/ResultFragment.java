package com.psychoapp.iliev.psychoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultFragment extends Fragment {

    @Bind(R.id.result_text) TextView _tv_result_text;
    @Bind(R.id.result_think) TextView _tv_result_think;

    public ResultFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        Context context = getActivity();

        ButterKnife.bind(this, view);

        String finalScore = getActivity().getIntent().getExtras().getString("score");
        Log.e("FINAL SCORE", finalScore);

        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/simonettaitalic.ttf");
        _tv_result_text.setTypeface(face);
        _tv_result_think.setTypeface(face);

        return view;
    }
}
