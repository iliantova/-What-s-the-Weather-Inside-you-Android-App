package com.psychoapp.iliev.psychoapp;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LinearLayoutButton extends LinearLayout {

    public LinearLayoutButton(View view, Context context, int id)
    {
        super(context);

        // if our context is not Activity we can't get View supplied by id
        if (!(context instanceof Activity))
            return;

        // find relative layout by id
        View v = view.findViewById(id);

        // is it RelativeLayout ?
        if (!(v instanceof LinearLayout))
            return;

        //cast it to relative layout
        LinearLayout layout = (LinearLayout)v;

        // copy layout parameters
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        this.setLayoutParams(params);

        // here I am using temporary instance of Button class
        // to get standard button background and to get button text color



        // copy all child from relative layout to this button
        while (layout.getChildCount() > 0)
        {
            View vchild = layout.getChildAt(0);
            layout.removeViewAt(0);
            this.addView(vchild);

            // if child is textView set its color to standard buttong text colors
            // using temporary instance of Button class

            // just to be sure that child views can't be clicked and focused
            vchild.setClickable(false);
            vchild.setFocusable(false);
            vchild.setFocusableInTouchMode(false);
        }

        // remove all view from layout (maybe it's not necessary)
        layout.removeAllViews();

        // set that this button is clickable, focusable, ...
        this.setClickable(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(false);

        // replace relative layout in parent with this one modified to looks like button
        ViewGroup vp = (ViewGroup)layout.getParent();
        int index = vp.indexOfChild(layout);
        vp.removeView(layout);
        vp.addView(this,index);

        this.setId(id);

    }

    // method for setting texts for the text views
    public void setText(View view, int id, CharSequence text)
    {
        View v = view.findViewById(id);
        if (null != v && v instanceof TextView)
        {
            ((TextView)v).setText(text);
        }

    }
    // method for setting drawable for the images
    public void setImageDrawable(View view, int id, Drawable drawable)
    {

        View v = view.findViewById(id);
        if (null != v && v instanceof ImageView)
        {
            ((ImageView)v).setImageDrawable(drawable);
        }

    }

    // method for setting images by resource id
    public void setImageResource(View view, int id, int image_resource_id)
    {

        View v = view.findViewById(id);
        if (null != v && v instanceof ImageView)
        {
            ((ImageView)v).setImageResource(image_resource_id);
        }

    }

}