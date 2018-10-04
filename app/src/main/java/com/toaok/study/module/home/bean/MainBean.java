package com.toaok.study.module.home.bean;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.toaok.themvp.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2018/9/30.
 */
public class MainBean implements IModel {

    private FragmentManager mFragmentManager;

    private List<Fragment> mFragmentList;


    public MainBean(FragmentManager fragmentManager) {
        this(fragmentManager, null);
    }

    public MainBean(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        this.mFragmentManager = fragmentManager;
        if (fragmentList != null)
            mFragmentList = fragmentList;
        else
            mFragmentList = new ArrayList<>();
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        mFragmentList = fragmentList;
    }

    public void onDestroy() {
        mFragmentManager = null;
        if (mFragmentList != null)
            mFragmentList.clear();
        mFragmentList = null;
    }
}
