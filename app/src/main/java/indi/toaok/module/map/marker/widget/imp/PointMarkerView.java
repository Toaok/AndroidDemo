package indi.toaok.module.map.marker.widget.imp;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import indi.toaok.R;

import indi.toaok.module.map.marker.widget.MarkerView;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class PointMarkerView extends RelativeLayout implements MarkerView{

    private static final String TAG = "PointMarkerView";

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
            resetPointLayoutGravity(RelativeLayout.CENTER_VERTICAL);
        }
    }

    public void setRightName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(name);
            resetPointLayoutGravity(RelativeLayout.CENTER_VERTICAL);
        }
    }


    public void setBottomtName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvBottom.setVisibility(VISIBLE);
            tvBottom.setText(name);
            resetPointLayoutGravity(RelativeLayout.CENTER_HORIZONTAL);
        }

    }

    public void setTopName(String name) {
        if (!TextUtils.isEmpty(name)) {
            tvTop.setVisibility(VISIBLE);
            tvTop.setText(name);
            resetPointLayoutGravity(RelativeLayout.CENTER_HORIZONTAL);
        }

    }


    private void resetPointLayoutGravity(int layoutGravity) {
        LayoutParams lp = (LayoutParams) ivPoint.getLayoutParams();
        lp.addRule(layoutGravity);
        ivPoint.setLayoutParams(lp);
        ivPoint.setVisibility(VISIBLE);
    }

    @Override
    public float getVerticalalProportion() {
        float vCenter = ivPoint.getHeight() / 2.0f;
        float proportion=vCenter / rootView.getHeight();
        Log.d(TAG, "vCenter:" + vCenter);
        Log.d(TAG, "proportion:" + proportion);
        return proportion;

    }

    @Override
    public float getHorizontalProportion() {
        float hCenter = ivPoint.getWidth() / 2.0f;
        float proportion=hCenter / rootView.getWidth();
        Log.d(TAG, "hCenter:" + hCenter);
        Log.d(TAG, "proportion:" + proportion);
        return proportion;
    }
}
