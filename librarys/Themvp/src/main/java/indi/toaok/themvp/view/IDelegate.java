package indi.toaok.themvp.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * View elegate base class
 * 视图层代理接口
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public interface IDelegate {

    void create(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState);

    int getOptionsMenuId();

    Toolbar getToolbar();

    View getRootView();

    void initWidget();
}
