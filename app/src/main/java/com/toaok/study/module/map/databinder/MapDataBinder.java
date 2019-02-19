package com.toaok.study.module.map.databinder;

import com.toaok.study.base.databinder.BaseDataBinder;
import com.toaok.study.module.map.bean.MapBean;
import com.toaok.study.module.map.view.MapDelegate;

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
