package com.toaok.study.module.base.bean;

import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public class ToolbarBean implements IModel{
    private String titleText;
    private String rightText;

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }
}
