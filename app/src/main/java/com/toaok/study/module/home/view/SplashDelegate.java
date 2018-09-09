package com.toaok.study.module.home.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.toaok.study.R;
import com.toaok.themvp.view.AppDelegate;

/**
 * View视图层，完全移除与Presenter业务逻辑的耦合
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDelegate  extends AppDelegate{


    private ImageView mImageView;

    private FrameLayout mFrameLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mImageView=get(R.id.iv_splash);
        mFrameLayout=get(R.id.frame_layout);
    }

    public void setImageView(@DrawableRes int resId) {
        if(mImageView!=null){
            mImageView.setImageResource(resId);
        }
    }
    public void setImageView(Drawable drawable) {
        if(mImageView!=null){
            mImageView.setImageDrawable(drawable);
        }
    }
    public void setImageView(Bitmap bitmap) {
        if(mImageView!=null){
            mImageView.setImageBitmap(bitmap);
        }
    }
    public void setImageView(String url){
        if(mImageView!=null){
            Glide.with(getRootView())
                    .load(url)
                    .into(mImageView);
        }
    }

    public FrameLayout getFrameLayout() {
        return mFrameLayout;
    }
}
