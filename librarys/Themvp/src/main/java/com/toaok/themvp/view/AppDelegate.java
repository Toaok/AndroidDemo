package com.toaok.themvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * View delegate base class
 * 视图代理的基类
 *
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public abstract class AppDelegate implements IDelegate {

    protected final SparseArray<View> mViews = new SparseArray<>();

    protected View rootView;

    public abstract @LayoutRes
    int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void initWidget() {

    }

    public <T extends View> T bindView(@IdRes int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }


    public <T extends View> T get(@IdRes int id) {
        return bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void toast(CharSequence msg) {
        Toast.makeText(rootView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

}
