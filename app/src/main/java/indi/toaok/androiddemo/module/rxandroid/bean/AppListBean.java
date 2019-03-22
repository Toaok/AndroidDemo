package indi.toaok.androiddemo.module.rxandroid.bean;

import java.util.ArrayList;
import java.util.List;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import indi.toaok.androiddemo.base.recyclerview.BaseRecyclerAdapter;
import indi.toaok.androiddemo.base.recyclerview.BaseViewHolder;
import indi.toaok.androiddemo.common.animation.Animation;
import indi.toaok.androiddemo.module.rxandroid.adapter.AppInfoListAdapter;
import indi.toaok.themvp.model.IModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Toaok
 * @version 1.0  2019/3/20.
 */

@Data
@Accessors(prefix = "m")
public class AppListBean implements IModel {

    /**
     * recyclerView 显示
     */
    @Animation.Visibility
    private int mVisibility;

    /**
     * 是否刷新
     */
    private boolean mRefreshing;

    private List<AppInfo> mAppInfos;

    private BaseRecyclerAdapter<AppInfo, BaseViewHolder> mAdapter;

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;

    private String mMesssage;

    public AppListBean() {
        this.mVisibility = Animation.GONE;
        this.mRefreshing = true;
        this.mAppInfos = new ArrayList<>();
        this.mAdapter = new AppInfoListAdapter(mAppInfos);
        this.mRefreshListener = null;
    }

    public void clearData() {
        this.mVisibility = Animation.GONE;
        this.mAdapter.clear();
    }

    @Override
    public String getMessage() {
        return mMesssage;
    }

    @Override
    public void setMessage(String message) {
        this.mMesssage = message;
    }
}
