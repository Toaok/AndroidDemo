package com.toaok.imageloder.core.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.toaok.imageloder.core.BaseImageLoaderStrategy;
import com.toaok.imageloder.core.ImageLoader;
import com.toaok.utilcode.util.NetworkUtils;

/**
 * 使用glide来加载图片
 *
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    private RequestOptions mOptions;

    public GlideImageLoaderStrategy() {

    }

    @Override
    public void loadImage(Context context, ImageLoader imageLoader) {

        if (NetworkUtils.isAvailableByPing()) {
            //网络可用
            loadNormal(context, imageLoader);
        } else {
            //网络不可用
            loadNormal(context, imageLoader);
        }


    }


    /**
     * load image with Glide
     */
    private void loadNormal(Context context, ImageLoader imageLoader) {
        mOptions = new RequestOptions()
                .centerCrop()
                .placeholder(imageLoader.getPlaceHolder());
        Glide.with(context).load(imageLoader.getUrl()).apply(mOptions).into(imageLoader.getImageView());
    }
}
