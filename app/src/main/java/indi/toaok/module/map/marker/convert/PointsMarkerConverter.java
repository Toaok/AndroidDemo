package indi.toaok.module.map.marker.convert;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import indi.toaok.module.map.marker.GDMarkerOptions;
import indi.toaok.module.map.marker.bean.MarkerInfo;

public class PointsMarkerConverter<T extends MarkerInfo> {

    public List<GDMarkerOptions> converts(List<T> values, BitmapDescriptorBuilder bitmapDescriptorBuilder, boolean isToCenter) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        ArrayList<GDMarkerOptions> markerOptionsArrayList = new ArrayList<>();
        LatLng centPoint=null;
        if(isToCenter) {
            centPoint= getCentPoint(values);
        }
        for (T value : values) {
            markerOptionsArrayList.add(convert(value,bitmapDescriptorBuilder, centPoint));
        }
        return markerOptionsArrayList;
    }

    public List<GDMarkerOptions> converts(List<T> values, BitmapDescriptorBuilder bitmapDescriptorBuilder) {
        return converts(values, bitmapDescriptorBuilder, true);
    }

    public GDMarkerOptions convert(T value, BitmapDescriptorBuilder bitmapDescriptorBuilder) {
        return convert(value, bitmapDescriptorBuilder, null);
    }

    private GDMarkerOptions convert(T value, BitmapDescriptorBuilder bitmapDescriptorBuilder, LatLng centerPoint) {
        if (value == null) {
            return null;
        }
        GDMarkerOptions gdMarkerOptions = new GDMarkerOptions();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.zIndex(0);
        markerOptions.position(value.getLatLng());
        markerOptions.snippet(value.getTitle());
        markerOptions.icon(bitmapDescriptorBuilder.getBitmapDescriptor(markerOptions,value,centerPoint));
        gdMarkerOptions.setMarkerOptions(markerOptions);
        return gdMarkerOptions;
    }

    private LatLng getCentPoint(List<T> values) {
        double x = 0, y = 0;

        for (T value : values) {
            x += value.getLatLng().latitude;
            y += value.getLatLng().longitude;
        }
        return new LatLng(x / values.size(), y / values.size());
    }

    public interface BitmapDescriptorBuilder<T extends MarkerInfo> {
        BitmapDescriptor getBitmapDescriptor(MarkerOptions markerOptions,T value,LatLng centerPoint);
    }
}