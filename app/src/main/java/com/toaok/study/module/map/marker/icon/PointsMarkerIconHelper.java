package com.toaok.study.module.map.marker.icon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.toaok.study.R;
import com.toaok.utils.Utils;

import java.lang.ref.SoftReference;

/**
 * @author Toaok
 * @version 1.0  2019/2/13.
 */
public class PointsMarkerIconHelper {

    static SoftReference<BitmapDescriptor> pointBitmapDescriptor;
    static SoftReference<BitmapDescriptor> pointBitmapDescriptorDefault;


    /**
     * @param bitmap 自定义图标
     * @return
     */
    public static BitmapDescriptor getPointBitmapDescriptor(Bitmap bitmap) {
        //使用自定义图标
        if (pointBitmapDescriptor != null && pointBitmapDescriptor.get() != null) {
            return pointBitmapDescriptor.get();
        } else if (bitmap != null) {
            pointBitmapDescriptor = new SoftReference<>(BitmapDescriptorFactory.fromBitmap(bitmap));
            return pointBitmapDescriptor.get();
        }
        //使用默认图标
        if (pointBitmapDescriptorDefault != null && pointBitmapDescriptorDefault.get() != null) {
            return pointBitmapDescriptorDefault.get();
        } else {
            pointBitmapDescriptorDefault = new SoftReference<>(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(Utils.getApp().getApplicationContext().getResources(), R.drawable.shape_oval_solid_0076ee_size_6)));
            return pointBitmapDescriptorDefault.get();
        }
    }

    /**
     * @param bitmap 自定义图标
     * @return
     */
    public static BitmapDescriptor getPointBitmapDescriptor(View view) {
        //使用默认图标
        if (pointBitmapDescriptorDefault != null && pointBitmapDescriptorDefault.get() != null) {
            return pointBitmapDescriptorDefault.get();
        } else {
            pointBitmapDescriptorDefault = new SoftReference<>(BitmapDescriptorFactory.fromView(view));
            return pointBitmapDescriptorDefault.get();
        }
    }
}
