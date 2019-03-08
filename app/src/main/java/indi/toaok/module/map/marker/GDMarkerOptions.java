package indi.toaok.module.map.marker;

import com.amap.api.maps.model.MarkerOptions;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class GDMarkerOptions<T> {
    private MarkerOptions markerOptions;

    private float rotateAngle;

    private T object;

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

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
