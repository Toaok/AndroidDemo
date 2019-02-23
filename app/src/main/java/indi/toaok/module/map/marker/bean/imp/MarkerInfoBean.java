package indi.toaok.module.map.marker.bean.imp;

import com.amap.api.maps.model.LatLng;

import indi.toaok.module.map.marker.bean.MarkerInfo;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class MarkerInfoBean implements MarkerInfo {

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
