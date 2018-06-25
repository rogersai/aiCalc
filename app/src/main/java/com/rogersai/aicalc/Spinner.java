package com.rogersai.aicalc;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Spinner extends ScrollView {
    private LinearLayout list;
    private Runnable scrollCheck;
    private int oldPosition;
    private static final int CHECK_DELAY = 100;

    public interface OnScrollStoppedListener{
        void onScrollStopped();
    }

    private OnScrollStoppedListener onScrollStoppedListener;

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        scrollCheck = new Runnable() {
            @Override
            public void run() {
                int newPos = getScrollY();
                if (newPos - oldPosition == 0){
                    if (onScrollStoppedListener != null) {
                        onScrollStoppedListener.onScrollStopped();
                    }
                } else {
                    oldPosition = getScrollY();
                    Spinner.this.postDelayed(scrollCheck, CHECK_DELAY);
                }
            }
        };
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void setOnScrollStoppedListener(Spinner.OnScrollStoppedListener listener) {
        onScrollStoppedListener = listener;
    }

    public void startScrollerCheck() {
        oldPosition = getScrollY();
        Spinner.this.postDelayed(scrollCheck, CHECK_DELAY);
    }
}
