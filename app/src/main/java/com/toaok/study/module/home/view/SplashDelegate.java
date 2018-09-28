package com.toaok.study.module.home.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.toaok.imageloder.core.ImageLoader;
import com.toaok.imageloder.core.ImagerLoader;
import com.toaok.study.R;
import com.toaok.study.base.BaseDelegate;

/**
 * View视图层，完全移除与Presenter业务逻辑的耦合
 *
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDelegate extends BaseDelegate {


    private ImageView mImageView;

    private FrameLayout mFrameLayout;

    private TextView mCountDownTimer;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mImageView = get(R.id.iv_splash);
        mFrameLayout = get(R.id.frame_layout);
        mCountDownTimer=get(R.id.tv_count_down_timer);
    }

    public TextView getCountDownTimer() {
        return mCountDownTimer;
    }

    public void setImageView(@DrawableRes int resId) {
        if (mImageView != null) {
            mImageView.setImageResource(resId);
        }
    }

    public void setImageView(Drawable drawable) {
        if (mImageView != null) {
            mImageView.setImageDrawable(drawable);
        }
    }

    public void setImageView(Bitmap bitmap) {
        if (mImageView != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

    public void setImageView(String url) {
        if (mImageView != null) {
            new ImagerLoader().loadImage(getActivity(),
                    new ImageLoader.Builder()
                            .url(url)
                            .imageView(mImageView)
                            .builder());
        }
    }

    public void setCountDownTimer(CharSequence countDownTimer) {
        mCountDownTimer.setText(countDownTimer);
    }

    public FrameLayout getFrameLayout() {
        return mFrameLayout;
    }
}
