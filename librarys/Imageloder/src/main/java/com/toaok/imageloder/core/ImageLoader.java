package com.toaok.imageloder.core;

import android.widget.ImageView;

/**
 * 使用了构建模式,封装了ImageView
 *
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public class ImageLoader {

    /**
     * 图片类型(大：2,中:1,小:0)
     */
    private int type;

    /**
     * 解析url
     */
    private String url;

    /**
     * 当图片加载失败时去加载图片
     */
    private int placeHolder;

    /**
     * ImageView 实例
     */
    private ImageView imageView;

    /**
     * wifi加载策略
     */
    private int wifiStrategy;

    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imageView = builder.imageView;
        this.wifiStrategy = builder.wifiStrategy;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getWifiStrategy() {
        return wifiStrategy;
    }

    public static class Builder {

        private int type;

        private String url;

        private int placeHolder;

        private ImageView imageView;

        private int wifiStrategy;


        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder wifiStrategy(int wifiStrategy) {
            this.wifiStrategy = wifiStrategy;
            return this;
        }

        public ImageLoader builder() {
            return new ImageLoader(this);
        }
    }

}
