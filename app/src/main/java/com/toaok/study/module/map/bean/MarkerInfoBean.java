package com.toaok.study.module.map.bean;

import com.amap.api.maps.model.LatLng;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class MarkerInfoBean implements MarkerInfo{

    private String title;

    private LatLng latLng;

    @Override
    public LatLng getLatLng() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
