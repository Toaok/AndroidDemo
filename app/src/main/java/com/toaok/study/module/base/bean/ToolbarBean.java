package com.toaok.study.module.base.bean;

import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public class ToolbarBean implements IModel{
    private String leftText;
    private String centerText;
    private String rightText;


    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getCenterText() {
        return centerText;
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }
}
