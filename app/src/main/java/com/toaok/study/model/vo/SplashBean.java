package com.toaok.study.model.vo;

import android.graphics.Bitmap;

import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashBean implements IModel{
    private Bitmap image;
    private String url;

    public SplashBean() {
    }

    public SplashBean(String url) {
        this.url = url;
    }

    public SplashBean(Bitmap image) {
        this.image = image;
    }

    public SplashBean(Bitmap image, String url) {
        this.image = image;
        this.url = url;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
