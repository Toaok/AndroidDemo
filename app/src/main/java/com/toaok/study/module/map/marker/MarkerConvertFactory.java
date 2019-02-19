package com.toaok.study.module.map.marker;

import android.content.Context;

import com.toaok.study.module.map.bean.MarkerInfoBean;
import com.toaok.study.module.map.marker.convert.PointsMarkerConverter;

import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2019/2/13.
 */
public class MarkerConvertFactory {


    private PointsMarkerConverter pointsMarkerConverter;

    private MarkerConvertFactory() {
        pointsMarkerConverter = new PointsMarkerConverter();
    }

    public static MarkerConvertFactory create() {
        return new MarkerConvertFactory();
    }


    public List<GDMarkerOptions> points2Converter(List<MarkerInfoBean> values, Context context) {
        return pointsMarkerConverter.converts(values, context);
    }

    public GDMarkerOptions point2Converter(MarkerInfoBean value, Context context) {
        return pointsMarkerConverter.convert(value, context);
    }
}
