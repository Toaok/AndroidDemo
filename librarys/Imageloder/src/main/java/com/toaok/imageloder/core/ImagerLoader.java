package com.toaok.imageloder.core;

import android.content.Context;

import com.toaok.imageloder.core.glide.GlideImageLoaderStrategy;

/**
 * 单例模式
 * 使用该类来加载图片
 *
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public class ImagerLoader {
    /**
     * 小
     */
    public static final int PIC_SMALL = 0;
    /**
     * 中
     */
    public static final int PIC_MEDIUM = 1;
    /**
     * 大
     */
    public static final int PIC_LARGE = 2;

    /**
     * 正常加载
     */
    public static final int LOAD_STRATEGY_NORMAL = 0;

    /**
     * wifi下加载
     */
    public static final int LOAD_STRATEGY_WIFI = 1;


    /**
     * wifi标记
     */
    public static boolean sWifiFlag = false;

    /**
     * 图片加载策略
     */
    private static BaseImageLoaderStrategy mStrategy;


    public ImagerLoader() {
        //默认采用glide加载图片的策略
        mStrategy = new GlideImageLoaderStrategy();
    }

    public void loadImage(Context context, ImageLoader imageLoader) {
        mStrategy.loadImage(context, imageLoader);
    }

    public void setLoadImageStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy = strategy;
    }
}
