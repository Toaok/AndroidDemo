package com.toaok.study.module.map.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toaok.study.R;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class PointMarkerView extends RelativeLayout {

    private static final String TAG ="PointMarkerView";
    
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvTop;
    private TextView tvBottom;

    private ImageView ivPoint;

    private View rootView;

    public PointMarkerView(Context context) {
        this(context, null);
    }

    public PointMarkerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointMarkerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w    Current width of this view.
     * @param h    Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG,"----------------onSizeChanged----------------:width:"+(rootView==null?0:rootView.getWidth())+"/"+"height:"+(rootView==null?0:rootView.getHeight()));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG,"----------------onAttachedToWindow----------------:width:"+(rootView==null?0:rootView.getWidth())+"/"+"height:"+(rootView==null?0:rootView.getHeight()));
        resetMarkerWidthFromTextView();
        resetMarkerHeightFromTextView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.i(TAG,"----------------onWindowFocusChanged----------------:width:"+(rootView==null?0:rootView.getWidth())+"/"+"height:"+(rootView==null?0:rootView.getHeight()));
        resetMarkerWidthFromTextView();
        resetMarkerHeightFromTextView();
    }

    protected void init(Context context) {
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = mLayoutInflater.inflate(R.layout.view_point_marker, this, true);

        tvLeft = rootView.findViewById(R.id.tv_left);
        tvRight = rootView.findViewById(R.id.tv_right);
        tvTop = rootView.findViewById(R.id.tv_top);
        tvBottom = rootView.findViewById(R.id.tv_bottom);
        ivPoint = rootView.findViewById(R.id.iv_point);
    }

    public void setLeftName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvLeft.setVisibility(VISIBLE);
            tvLeft.setText(name);
        }
    }

    public void setRightName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(name);
        }
    }


    public void setBottomtName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvBottom.setVisibility(VISIBLE);
            tvBottom.setText(name);
        }
    }

    public void setTopName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvTop.setVisibility(VISIBLE);
            tvTop.setText(name);
        }
    }


    private void resetMarkerWidthFromTextView() {
        this.resetMarkerWidthFromTextView(tvRight.getWidth() + tvLeft.getWidth());
    }

    private void resetMarkerWidthFromTextView(int textViewWidth) {
        Log.i(TAG, "textViewWidth:"+textViewWidth );
        rootView.setMinimumWidth(textViewWidth * 2);
        LayoutParams lp = (LayoutParams) ivPoint.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        ivPoint.setLayoutParams(lp);
    }

    private void resetMarkerHeightFromTextView() {
        this.resetMarkerHeightFromTextView(tvTop.getHeight() + tvBottom.getHeight());
    }

    private void resetMarkerHeightFromTextView(int textViewHeight) {
        Log.i(TAG, "textViewHeight:"+textViewHeight);
        rootView.setMinimumWidth(textViewHeight * 2);
        LayoutParams lp = (LayoutParams) ivPoint.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        ivPoint.setLayoutParams(lp);
    }
}
