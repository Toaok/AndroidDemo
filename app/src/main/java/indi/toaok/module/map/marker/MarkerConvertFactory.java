package indi.toaok.module.map.marker;

import android.content.Context;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.util.List;

import indi.toaok.module.map.marker.bean.MarkerInfo;
import indi.toaok.module.map.marker.bean.imp.MarkerInfoBean;
import indi.toaok.module.map.marker.convert.PointsMarkerConverter;
import indi.toaok.module.map.marker.icon.PointsMarkerIconHelper;
import indi.toaok.module.map.marker.widget.imp.PointMarkerView;

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


    public List<GDMarkerOptions> points2Converter(List<? extends MarkerInfo> values, Context context) {
        return pointsMarkerConverter.converts(values, new PointsMarkerConverter.BitmapDescriptorBuilder() {
            @Override
            public BitmapDescriptor getBitmapDescriptor(MarkerOptions markerOptions, MarkerInfo value, LatLng centerPoint) {
                BitmapDescriptor bitmapDescriptor = PointsMarkerIconHelper.getPointBitmapDescriptor(null);

                PointMarkerView pointMarkerView = new PointMarkerView(context);
                if (centerPoint == null) {
                    //下
                    pointMarkerView.setBottomtName(value.getTitle());
                    bitmapDescriptor = showBottom(markerOptions, pointMarkerView);
                } else {
                    //do something
                    if (value.getLatLng() != null) {
                        if (value.getLatLng().longitude > centerPoint.longitude) {
                            //右
                            pointMarkerView.setRightName(value.getTitle());
                            bitmapDescriptor = showRight(markerOptions, pointMarkerView);
                        } else {
                            //左
                            pointMarkerView.setLeftName(value.getTitle());
                            bitmapDescriptor = showLeft(markerOptions, pointMarkerView);
                        }
                    }
                }
                return bitmapDescriptor;
            }

            private BitmapDescriptor showTop(MarkerOptions markerOptions, PointMarkerView pointMarkerView) {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(pointMarkerView);
                markerOptions.anchor(0.5f, pointMarkerView.getVerticalalProportion());
                return bitmapDescriptor;
            }

            private BitmapDescriptor showBottom(MarkerOptions markerOptions, PointMarkerView pointMarkerView) {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(pointMarkerView);
                markerOptions.anchor(0.5f, 1 - pointMarkerView.getVerticalalProportion());
                return bitmapDescriptor;
            }


            private BitmapDescriptor showLeft(MarkerOptions markerOptions, PointMarkerView pointMarkerView) {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(pointMarkerView);
                markerOptions.anchor(1f - pointMarkerView.getHorizontalProportion(), 0.5f);
                return bitmapDescriptor;
            }

            private BitmapDescriptor showRight(MarkerOptions markerOptions, PointMarkerView pointMarkerView) {
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(pointMarkerView);
                markerOptions.anchor(pointMarkerView.getHorizontalProportion(), 0.5f);
                return bitmapDescriptor;
            }


        });
    }

    public GDMarkerOptions point2Converter(MarkerInfoBean value, Context context) {
        return pointsMarkerConverter.convert(value, new PointsMarkerConverter.BitmapDescriptorBuilder() {
            @Override
            public BitmapDescriptor getBitmapDescriptor(MarkerOptions markerOptions, MarkerInfo value, LatLng centerPoint) {
                return PointsMarkerIconHelper.getPointBitmapDescriptor(null);
            }
        });
    }


}
