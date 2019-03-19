package indi.toaok.androiddemo.module.map.marker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class MarkerBuilder {

    List<GDMarkerOptions> datas;
    MarkerHelper.MarkerType type;

    private MarkerBuilder(Builder builder) {
        datas = builder.datas;
        type = builder.type;
    }


    public static final class Builder {
        private List<GDMarkerOptions> datas;
        private MarkerHelper.MarkerType type;

        public Builder() {
        }

        public Builder datas(List<GDMarkerOptions> val) {
            datas = val;
            return this;
        }

        public Builder data(GDMarkerOptions val) {
            if(datas==null){
                datas=new ArrayList<>();
                datas.add(val);
            }
            return this;
        }

        public Builder type(MarkerHelper.MarkerType val) {
            type = val;
            return this;
        }

        public MarkerBuilder build() {
            return new MarkerBuilder(this);
        }
    }

    public List<GDMarkerOptions> getDatas() {
        return datas;
    }

    public MarkerHelper.MarkerType getType() {
        return type;
    }
}
