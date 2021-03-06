package com.psychoapp.iliev.psychoapp.activities;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.psychoapp.iliev.psychoapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityFragment extends Fragment {

    @Bind(R.id.info_drag) LinearLayout _infoShowAndHide;
    @Bind(R.id.info_text) LinearLayout _infoText;
    @Bind(R.id.info_drag_text) TextView _infoShowAndHideText;
    @Bind(R.id.info_text_text) TextView _infoTextText;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        ButterKnife.bind(this, view);

        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/simonettaitalic.ttf");
        _infoTextText.setTypeface(face);
        _infoShowAndHideText.setTypeface(face);


        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                        Animation slidedown = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_faster);

                        slidedown.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        _infoText.startAnimation(slidedown);

                        _infoText.setVisibility(View.VISIBLE);
                        _infoShowAndHide.setVisibility(View.GONE);

                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        final GestureDetector gesturetap = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {

                        Animation fadeout = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out_faster);

                        fadeout.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });

                        _infoText.startAnimation(fadeout);

                        _infoText.setVisibility(View.GONE);
                        _infoShowAndHide.setVisibility(View.VISIBLE);


                        return super.onDoubleTap(e);
                    }
                });

        _infoText.setVisibility(View.GONE);


        _infoShowAndHide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
        _infoText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesturetap.onTouchEvent(event);
            }
        });

        return view;
    }
}
