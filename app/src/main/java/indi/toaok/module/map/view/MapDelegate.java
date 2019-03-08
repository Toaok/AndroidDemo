package indi.toaok.module.map.view;

import com.amap.api.maps.AMap;

import indi.toaok.R;
import indi.toaok.module.comment.widget.LoadingDialog;

/**
 * 路线规划和点的平滑移动
 *
 * @author Toaok
 * @version 1.0  2018/12/20.
 */
public class MapDelegate extends BaseMapDelegate implements AMap.OnMapLoadedListener {

    private LoadingDialog.DialogDismissListener mDialogDismissListener;

    @Override
    protected void init() {
        super.init();
        mDialogDismissListener=LoadingDialog.createLoadingDialog(getActivity(),null,getString(R.string.loading_wait_a_moment));
        aMap.setOnMapLoadedListener(this);
    }

    @Override
    public void onMapLoaded() {
        mDialogDismissListener.onDismiss();
    }
}
