package com.toaok.study.module.map.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.MapView;
import com.toaok.study.base.presenter.fragment.BaseFragment;
import com.toaok.study.module.map.view.BaseMapDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/12/20.
 */
public abstract class BaseMapFragment<T extends BaseMapDelegate> extends BaseFragment<T> {


    private MapView mMapView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = viewDelegate.getMapView();
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }
    }
}
