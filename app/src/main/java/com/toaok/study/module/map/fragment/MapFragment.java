package com.toaok.study.module.map.fragment;import android.Manifest;import android.app.Dialog;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import com.amap.api.maps.model.LatLng;import com.tbruyelle.rxpermissions2.RxPermissions;import com.toaok.study.R;import com.toaok.study.module.map.bean.MarkerInfoBean;import com.toaok.study.module.map.databinder.MapDataBinder;import com.toaok.study.module.map.marker.MarkerHelper;import com.toaok.study.module.map.view.MapDelegate;import com.toaok.study.utils.PermissionUtils;import com.toaok.themvp.databind.DataBinder;import java.lang.ref.WeakReference;import java.util.ArrayList;import java.util.List;public class MapFragment extends BaseMapFragment<MapDelegate> implements View.OnClickListener {    private static final String TAG = "com.toaok.study.module.map.fragment.MapFragment";    public static MapFragment newInstance(String desc) {        MapFragment fragment = new MapFragment();        Bundle bundle = new Bundle();        bundle.putString(TAG, desc);        fragment.setArguments(bundle);        return fragment;    }    private RxPermissions mRxPermissions;    private Dialog mPermissionsDialog;    @Nullable    @Override    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        return super.onCreateView(inflater, container, savedInstanceState);    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        init();        List points = new ArrayList<MarkerInfoBean>();        MarkerInfoBean markerInfoBean = new MarkerInfoBean();        markerInfoBean.setLatLng(new LatLng(31.221023, 121.395777));        markerInfoBean.setTitle("停车场(出入口)");        points.add(markerInfoBean);        MarkerInfoBean markerInfoBean1 = new MarkerInfoBean();        markerInfoBean1.setLatLng(new LatLng(31.222633, 121.394377));        markerInfoBean1.setTitle("停车场(出入口)");        points.add(markerInfoBean1);        MarkerInfoBean markerInfoBean2 = new MarkerInfoBean();        markerInfoBean2.setLatLng(new LatLng(31.222261, 121.393824));        markerInfoBean2.setTitle("天洁大厦停车场(出入口)");        points.add(markerInfoBean2);        viewDelegate.addMarkers(MarkerHelper.getPointsMaekerBuilder(points, getContext()));        viewDelegate.moveToLatLng(markerInfoBean.getLatLng());    }    private void init() {        /**         * 初始化权限请求对象         */        if (mRxPermissions == null) {            mRxPermissions = new RxPermissions(new WeakReference<>(this).get());        }        getPermmission();    }    private void getPermmission(){        if (mRxPermissions != null) {            mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION,                    Manifest.permission.ACCESS_FINE_LOCATION)                    .subscribe(granted -> {                        if (!granted && isVisible()) {                            if (mPermissionsDialog == null) {                                mPermissionsDialog = PermissionUtils.getPermissionDialog(getContext(), R.string.permission_location);                            }                            mPermissionsDialog.show();                        }                    });        }    }    @Override    protected void bindEvenListener() {        super.bindEvenListener();    }    @SuppressWarnings("unchecked")    @Override    protected Class<MapDelegate> getDelegateClass() {        return MapDelegate.class;    }    @Override    public void onClick(View v) {        switch (v.getId()) {        }    }    @Override    protected DataBinder getDataBinder() {        return new MapDataBinder();    }    @Override    public void onDestroy() {        super.onDestroy();        if (mPermissionsDialog != null) {            mPermissionsDialog.dismiss();        }    }}