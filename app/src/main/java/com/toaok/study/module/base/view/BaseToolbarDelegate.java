package com.toaok.study.module.base.view;import android.os.Bundle;import android.support.annotation.LayoutRes;import android.support.v7.widget.Toolbar;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import com.toaok.study.R;/** * BaseToolbarDelegate 所有在该项目中使用TheMVP框架的BaseToolbarDelegate都必须继承该抽象类 * * @author Toaok * @version 1.0  2018/9/26. */public abstract class BaseToolbarDelegate extends BaseDelegate implements View.OnClickListener {    /*     * 用于自定义Toolbar     * */    protected View toolbarView;    public abstract @LayoutRes    int getToolbarLayoutId();    @Override    public void create(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {        super.create(inflater, container, saveInstanceState);        int toolbarId = getToolbarLayoutId();        toolbarView = inflater.inflate(toolbarId, container);    }    @Override    public Toolbar getToolbar() {        return toolbarView.findViewById(R.id.toolbar);    }    private OnClickToolBarListener mToolBarListener;    public void setToolBarListener(OnClickToolBarListener toolBarListener) {        mToolBarListener = toolBarListener;        setOnClickToolbarListener(this,R.id.btn_left,R.id.text_right,R.id.btn_right);    }    protected void setOnClickToolbarListener(View.OnClickListener listener, int... ids) {        if (ids == null) {            return;        }        for (int id : ids) {            toolbarView.findViewById(id).setOnClickListener(listener);        }    }    @Override    public void onClick(View v) {        switch (v.getId()) {            case R.id.btn_left:                mToolBarListener.onClickLeftButton();                break;            case R.id.text_right:                mToolBarListener.onClickRightText();                break;            case R.id.btn_right:                mToolBarListener.onClickRightButton();                break;        }    }    public interface OnClickToolBarListener {        void onClickLeftButton();        void onClickRightButton();        void onClickRightText();    }}