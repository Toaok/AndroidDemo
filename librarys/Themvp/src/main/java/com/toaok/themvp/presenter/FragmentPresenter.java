package com.toaok.themvp.presenter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toaok.themvp.view.IDelegate;

/**
 * Presenter base class for fragmrnt
 * Presenter 层的实现基类
 * @param <T> View delegate class type
 *
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public abstract class FragmentPresenter<T extends IDelegate> extends Fragment {
    public T viewDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate=getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewDelegate.create(inflater,container,savedInstanceState);
        return viewDelegate.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDelegate.initWidget();
        bindEvenListener();
    }

    protected  void bindEvenListener(){

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (viewDelegate.getOptionsMenuId()!=0){
            inflater.inflate(viewDelegate.getOptionsMenuId(),menu);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(viewDelegate==null){
            try {
                viewDelegate=getDelegateClass().newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate=null;
    }

    protected abstract Class<T> getDelegateClass();
}
