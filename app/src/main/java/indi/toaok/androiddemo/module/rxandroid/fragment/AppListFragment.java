package indi.toaok.androiddemo.module.rxandroid.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import indi.toaok.androiddemo.base.presenter.fragment.BaseFragment;
import indi.toaok.androiddemo.common.animation.Animation;
import indi.toaok.androiddemo.module.rxandroid.bean.AppInfo;
import indi.toaok.androiddemo.module.rxandroid.bean.AppInfoRich;
import indi.toaok.androiddemo.module.rxandroid.bean.AppListBean;
import indi.toaok.androiddemo.module.rxandroid.databinder.AppListDataBinder;
import indi.toaok.androiddemo.module.rxandroid.view.AppListDelegate;
import indi.toaok.androiddemo.utils.FilePathUtil;
import indi.toaok.themvp.databind.DataBinder;
import indi.toaok.utils.core.ImageUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Toaok
 * @version 1.0  2019/3/19.
 */
public class AppListFragment extends BaseFragment<AppListDelegate> {

    private AppListBean mAppListBean;

    public static AppListFragment newInstance() {
        AppListFragment fragment = new AppListFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mAppListBean = new AppListBean();
        mAppListBean.setRefreshListener(() -> refreshAppList());
        refreshAppList();
    }

    private Observable<AppInfo> getApps() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(getContext().getPackageManager().queryIntentActivities(mainIntent, 0))
                .map(resolveInfo -> {
                    AppInfoRich appInfoRich = new AppInfoRich(getContext(), resolveInfo);
                    Bitmap icon = ImageUtil.drawable2Bitmap(appInfoRich.getIcon());
                    String name = appInfoRich.getName();
                    //保存PNG格式图片时要注意加上后缀,不然不会以png方式读取
                    String iconPath = FilePathUtil.getCacheImagePath() + name + ".png";
                    ImageUtil.save(icon, iconPath, Bitmap.CompressFormat.PNG);
                    return new AppInfo(name, iconPath, appInfoRich.getVersion(), appInfoRich.getLastUpdateTime());
                });
    }

    @SuppressLint("CheckResult")
    private void refreshAppList() {
        getApps().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(appInfo -> {//next
                    mAppListBean.setVisibility(Animation.VISIBLE);
                    mAppListBean.getAdapter().append(appInfo);
                    notifyModelChanged(mAppListBean);
                }, throwable -> {//异常
                    mAppListBean.setMessage("Something went wrong!");
                    mAppListBean.setRefreshing(false);
                    notifyModelChanged(mAppListBean);
                }, () -> {//完成
                    mAppListBean.setMessage("There is the list!");
                    mAppListBean.setRefreshing(false);
                    notifyModelChanged(mAppListBean);
                }, disposable -> {//订阅时清除数据
                    mAppListBean.clearData();
                    mAppListBean.setMessage("list has subscribe!");
                    notifyModelChanged(mAppListBean);
                });
    }

    @Override
    protected Class<AppListDelegate> getDelegateClass() {
        return AppListDelegate.class;
    }

    @Override
    protected DataBinder getDataBinder() {
        return new AppListDataBinder();
    }
}
