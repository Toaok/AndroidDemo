package indi.toaok.module.map.marker;

import com.amap.api.maps.model.MarkerOptions;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class GDMarkerOptions {
    private MarkerOptions markerOptions;

    private float rotateAngle;
    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public float getRotateAngle() {
        return rotateAngle;
    }

    public void setRotateAngle(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }
}
