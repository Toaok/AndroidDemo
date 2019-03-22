package indi.toaok.androiddemo.module.rxandroid.view;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.base.view.BaseDelegate;
import indi.toaok.androiddemo.common.animation.Animation;

import static android.view.View.GONE;

/**
 * @author Toaok
 * @version 1.0  2019/3/20.
 */
public class AppListDelegate extends BaseDelegate {

    //View
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view_apps)
    RecyclerView mRecyclerView;

    //Color
    @BindColor(R.color.colorAccent)
    int colorAccent;

    //Dimen
    @BindDimen(R.dimen.offset_24)
    int offsetEnd;


    boolean isSetAdapter;
    boolean isSetRefreshListener;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_app_list;
    }


    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setColorSchemeColors(colorAccent);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, offsetEnd);
        mRecyclerView.setVisibility(GONE);
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        if (listener != null) {
            isSetRefreshListener = true;
            mSwipeRefreshLayout.setOnRefreshListener(listener);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            isSetAdapter = true;
            mRecyclerView.setAdapter(adapter);
        }
    }

    public boolean isSetAdapter() {
        return isSetAdapter;
    }

    public boolean isSetRefreshingListener() {
        return isSetRefreshListener;
    }

    public void setVisibility(@Animation.Visibility int visibility) {
        mRecyclerView.setVisibility(visibility);
    }

    public void setRefreshing(boolean isRefreshing) {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
    }

}
