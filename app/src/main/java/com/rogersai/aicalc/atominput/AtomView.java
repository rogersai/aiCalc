package com.rogersai.aicalc.atominput;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class AtomView extends ConstraintLayout {
    int deviceWidth;

    public AtomView(Context context) {
        super(context);
    }
    public AtomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public AtomView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }
}
