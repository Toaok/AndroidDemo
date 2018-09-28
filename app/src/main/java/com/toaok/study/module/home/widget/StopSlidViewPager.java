package com.toaok.study.module.home.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止滑动的ViewPager
 *
 * @author Toaok
 * @version 1.0  2018/9/27.
 */
public class StopSlidViewPager extends ViewPager {
    public StopSlidViewPager(@NonNull Context context) {
        super(context);
    }

    public StopSlidViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
