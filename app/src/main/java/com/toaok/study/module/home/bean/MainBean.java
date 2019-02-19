package com.toaok.study.module.home.bean;

import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/9/30.
 */
public class MainBean implements IModel {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message=message;
    }
}
