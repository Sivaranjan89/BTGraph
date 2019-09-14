package com.droid.uigraph;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {

    private int startWidth;
    private int deltaWidth;
    private View view;


    public ResizeAnimation(View v) {
        this.view = v;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().width = (int) (startWidth + deltaWidth * interpolatedTime);
        view.requestLayout();
    }

    public void setParams(int start, int end) {
        this.startWidth = start;
        deltaWidth = end - startWidth;
    }

    @Override
    public void setDuration(long durationMillis) {
        super.setDuration(durationMillis);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
