package com.toaok.themvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.toaok.themvp.view.IDelegate;

/**
 * Presenter base class for Activity
 * Presenter层的实现基类
 *
 * @param <T> View delegate class type
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate=getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(viewDelegate.getRootView());
        initToolbar();
        viewDelegate.initWidget();
        bindEvenListener();
    }

    protected void bindEvenListener(){

    }

    /**
     * TODO 设置了Toolbar但没有显示
     */
    protected void initToolbar() {
        Toolbar toolbar=viewDelegate.getToolbar();
        if(toolbar!=null){
            this.setSupportActionBar(toolbar);
        }
    }


    /**
     * 在activity被系统回收，重新创建activity是判断viewDelegate对象是否被销毁
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(viewDelegate==null){
            try {
                viewDelegate=getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(viewDelegate.getOptionsMenuId()!=0){
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(),menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate=null;
    }

    protected abstract Class<T> getDelegateClass();
}
