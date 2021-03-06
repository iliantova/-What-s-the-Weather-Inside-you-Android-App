package com.psychoapp.iliev.psychoapp.helpers;

import android.view.View;

import com.psychoapp.iliev.psychoapp.R;

import java.util.Random;

public class BackGroundChanger {

    private static final int BACKGROUND_IMAGES_NUM = 8;

    public static void backgroundRandomizer(View view) {
        Random r = new Random();
        int randomInt = r.nextInt(BACKGROUND_IMAGES_NUM);
        int res;
        switch (randomInt) {
            case 0:
                res = R.drawable.c;
                break;
            case 1:
                res = R.drawable.d;
                break;
            case 2:
                res = R.drawable.e;
                break;
            case 3:
                res = R.drawable.f;
                break;
            case 4:
                res = R.drawable.g;
                break;
            case 5:
                res = R.drawable.h;
                break;
            case 6:
                res = R.drawable.j;
                break;
            case 7:
                res = R.drawable.aquarell_night_400_655;
                break;
            default:
                res = R.drawable.aquarell_night_400_655;
                break;
        }
        view.setBackgroundResource(res);

    }
}
