package indi.toaok.module.map.databinder;

import indi.toaok.base.databinder.BaseDataBinder;
import indi.toaok.module.map.bean.MapBean;
import indi.toaok.module.map.view.MapDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/11/7.
 */
public class MapDataBinder extends BaseDataBinder<MapDelegate,MapBean> {
    @Override
    public void viewBindModel(MapDelegate viewDelegate, MapBean data) {
        super.viewBindModel(viewDelegate,data);
    }
}
