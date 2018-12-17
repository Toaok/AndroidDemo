package com.toaok.study.module.base.bean;

import com.toaok.themvp.model.IModel;

/**
 * toolbar 数据
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public class ToolbarBean implements IModel{

    /**
     * 标题
     */
    private String titleText;

    /*
    * 右侧文字
    * */
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
