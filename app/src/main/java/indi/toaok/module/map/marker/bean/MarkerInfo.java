package indi.toaok.module.map.marker.bean;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */

import com.amap.api.maps.model.LatLng;

public interface MarkerInfo {

    LatLng getLatLng();
    String getTitle();
}
