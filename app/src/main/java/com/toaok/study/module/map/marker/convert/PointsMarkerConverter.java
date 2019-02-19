package com.toaok.study.module.map.marker.convert;

import android.content.Context;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.toaok.study.module.map.bean.MarkerInfo;
import com.toaok.study.module.map.marker.GDMarkerOptions;
import com.toaok.study.module.map.widget.PointMarkerView;

import java.util.ArrayList;
import java.util.List;

public class PointsMarkerConverter<T extends MarkerInfo> implements MarkerConvert<T, GDMarkerOptions, Context> {

    @Override
    public List<GDMarkerOptions> converts(List<T> values, Context context) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        ArrayList<GDMarkerOptions> markerOptionsArrayList = new ArrayList<>();
        for (T value : values) {
            markerOptionsArrayList.add(convert(value, context));
        }
        return markerOptionsArrayList;
    }

    @Override
    public GDMarkerOptions convert(T value, Context context) {
        if (value == null) {
            return null;
        }
        GDMarkerOptions gdMarkerOptions = new GDMarkerOptions();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.zIndex(0);
        markerOptions.position(value.getLatLng());
        markerOptions.snippet(value.getTitle());
        markerOptions.setFlat(true);
        if (context != null) {
            markerOptions.icon(getBitmapDescriptor(context,null,value));
        }
        gdMarkerOptions.setMarkerOptions(markerOptions);

        return gdMarkerOptions;
    }

    public BitmapDescriptor getBitmapDescriptor(Context context, LatLonPoint centerPoint, T value) {
        PointMarkerView nearAboardPointView = new PointMarkerView(context);
        if (centerPoint == null) {
            nearAboardPointView.setBottomtName(value.getTitle());
        } else {
            //do something
            if (value.getLatLng() != null) {
                if (value.getLatLng().latitude > centerPoint.getLatitude()) {
                    nearAboardPointView.setLeftName(value.getTitle());
                } else {
                    nearAboardPointView.setRightName(value.getTitle());
                }
            }
        }
        return BitmapDescriptorFactory.fromView(nearAboardPointView);
    }
}