package indi.toaok.androiddemo.module.map.marker.convert;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import indi.toaok.androiddemo.module.map.marker.GDMarkerOptions;
import indi.toaok.androiddemo.module.map.marker.bean.MarkerInfo;
import indi.toaok.androiddemo.module.map.marker.icon.PointsMarkerIconHelper;
import indi.toaok.androiddemo.module.map.marker.widget.MarkerView;

public class PointsMarkerConverter<T extends MarkerInfo> {

    public List<GDMarkerOptions> converts(List<T> values, MarkerViewBuilder<T> markerViewBuilder, boolean isToCenter) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        ArrayList<GDMarkerOptions> markerOptionsArrayList = new ArrayList<>();
        LatLng centPoint = null;
        if (isToCenter) {
            centPoint = getCentPoint(values);
        }
        for (T value : values) {
            markerOptionsArrayList.add(convert(value, markerViewBuilder, centPoint));
        }
        return markerOptionsArrayList;
    }

    public List<GDMarkerOptions> converts(List<T> values, MarkerViewBuilder<T> markerViewBuilder) {
        return converts(values, markerViewBuilder, true);
    }

    public GDMarkerOptions convert(T value, MarkerViewBuilder<T> markerViewBuilder) {
        return convert(value, markerViewBuilder, null);
    }

    private GDMarkerOptions convert(T value, MarkerViewBuilder<T> markerViewBuilder, LatLng centerPoint) {
        if (value == null || !(value instanceof MarkerInfo)) {
            return null;
        }
        GDMarkerOptions gdMarkerOptions = new GDMarkerOptions();
        MarkerView markerView = null;
        if (markerViewBuilder != null) {
            markerView = markerViewBuilder.getMarkerView(value, centerPoint);
        }
        MarkerOptions markerOptions = new MarkerOptions()
                .zIndex(0)
                .position(value.getLatLng())
                .snippet(value.getTitle())
                .setFlat(true)
                .anchor(markerView == null ? 0.5f : markerView.getHorizontalProportion(), markerView == null ? 0.5f : markerView.getVerticalalProportion())
                .icon(markerView == null ? PointsMarkerIconHelper.getPointBitmapDescriptor(null) : BitmapDescriptorFactory.fromView(markerView));
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

    public interface MarkerViewBuilder<T extends MarkerInfo> {
        MarkerView getMarkerView(T value, LatLng centerPoint);
    }
}