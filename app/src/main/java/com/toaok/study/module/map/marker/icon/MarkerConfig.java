package com.toaok.study.module.map.marker.icon;

import com.amap.api.maps.model.BitmapDescriptor;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class MarkerConfig {
    BitmapDescriptor bitmapDescriptor;

    private MarkerConfig(Builder builder) {
        bitmapDescriptor = builder.bitmapDescriptor;
    }

    public static final class Builder {
        private BitmapDescriptor bitmapDescriptor;

        public Builder() {
        }

        public Builder bitmapDescriptor(BitmapDescriptor val) {
            bitmapDescriptor = val;
            return this;
        }

        public MarkerConfig build() {
            return new MarkerConfig(this);
        }
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return bitmapDescriptor;
    }
}
