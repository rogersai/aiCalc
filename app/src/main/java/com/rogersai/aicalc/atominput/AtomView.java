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

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int maxHeight;
//        int curLeft, curTop;
//        int curWidth, curHeight;
//
//        final int count = this.getChildCount();
//        final int boundLeft = this.getPaddingLeft();
//        final int boundTop = this.getPaddingTop();
//        final int boundRight = this.getMeasuredWidth() - this.getPaddingRight();
//        final int boundBottom = this.getMeasuredHeight() - this.getPaddingBottom();
//        final int boundWidth = boundRight - boundLeft;
//        final int boundHeight = boundBottom - boundTop;
//
//        maxHeight = 0;
//        curLeft = boundLeft;
//        curTop = boundTop;
//
//        for (int i = 0; i < count; i++){
//            View child = getChildAt(i);
//            if (child.getVisibility() == GONE) {
//                return;
//            }
//            // Tell the children that they can be at most the size of the boundary
//            child.measure(MeasureSpec.makeMeasureSpec(boundWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(boundHeight, MeasureSpec.AT_MOST));
//            curWidth = child.getMeasuredWidth();
//            curHeight = child.getMeasuredHeight();
//            if(curLeft + curWidth >= boundRight){
//                curLeft = boundLeft;
//                curTop += maxHeight;
//                maxHeight = 0;
//            }
//            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
//            if(maxHeight < curHeight) {
//                maxHeight = curHeight;
//            }
//            curLeft += curWidth;
//        }
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int count = getChildCount();
//        int maxHeight = 0;
//        int maxWidth = 0;
//        int childState = 0;
//        int mLeftWidth = 0;
//        int rowCount = 0;
//
//        for (int i = 0; i < count; i++) {
//            final View child = getChildAt(i);
//            if(child.getVisibility() == GONE) {
//                continue;
//            }
//            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            maxWidth += Math.max(maxWidth, child.getMeasuredWidth());
//            mLeftWidth += child.getMeasuredWidth();
//
////            if((mLeftWidth / deviceWidth) > rowCount){
////                maxHeight += child.getMeasuredHeight();
////                rowCount++;
////            } else {
////                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
////            }
//            childState = combineMeasuredStates(childState, child.getMeasuredState());
//        }
//
//        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
//        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
//
//        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState), resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
//    }
//
//    public void add(String itemText) {
//
//    }
}
