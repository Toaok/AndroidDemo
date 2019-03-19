package indi.toaok.androiddemo.module.map.marker;

import android.content.Context;

import java.util.List;

import indi.toaok.androiddemo.module.map.marker.bean.imp.MarkerInfoBean;
import indi.toaok.androiddemo.module.map.marker.convert.PointsMarkerConverter;
import indi.toaok.androiddemo.module.map.marker.widget.imp.PointMarkerView;

/**
 * @author Toaok
 * @version 1.0  2019/2/13.
 */
public class MarkerConvertFactory {


    private PointsMarkerConverter<MarkerInfoBean> pointsMarkerConverter;

    private MarkerConvertFactory() {
        pointsMarkerConverter = new PointsMarkerConverter<>();
    }

    public static MarkerConvertFactory create() {
        return new MarkerConvertFactory();
    }


    public List<GDMarkerOptions> points2Converter(List<MarkerInfoBean> values, Context context) {
        return pointsMarkerConverter.converts(values, (value, centerPoint) -> {
            PointMarkerView pointMarkerView = new PointMarkerView(context);
            if (centerPoint == null) {
                //下
                pointMarkerView.setBottomtName(value.getTitle());
            } else {
                //do something
                if (value.getLatLng() != null) {
                    if (value.getLatLng().longitude > centerPoint.longitude) {
                        //右
                        pointMarkerView.setRightName(value.getTitle());
                    } else {
                        //左
                        pointMarkerView.setLeftName(value.getTitle());
                    }
                }
            }
            return pointMarkerView;
        });
    }

    public GDMarkerOptions point2Converter(MarkerInfoBean value, Context context) {
        return pointsMarkerConverter.convert(value, (value1, centerPoint) -> null);
    }


}
