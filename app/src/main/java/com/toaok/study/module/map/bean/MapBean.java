package com.toaok.study.module.map.bean;

import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/11/7.
 */
public class MapBean implements IModel {
    private CharSequence testString;

    public MapBean(CharSequence testString) {
        this.testString = testString;
    }

    public CharSequence getTestString() {
        return testString;
    }

    public void setTestString(CharSequence testString) {
        this.testString = testString;
    }
}
