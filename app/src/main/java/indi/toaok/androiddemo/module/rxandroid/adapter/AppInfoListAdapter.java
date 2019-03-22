package indi.toaok.androiddemo.module.rxandroid.adapter;

import android.graphics.drawable.BitmapDrawable;

import java.util.List;

import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.base.recyclerview.BaseRecyclerAdapter;
import indi.toaok.androiddemo.base.recyclerview.BaseViewHolder;
import indi.toaok.androiddemo.module.rxandroid.bean.AppInfo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Toaok
 * @version 1.0  2019/3/13.
 */
public class AppInfoListAdapter extends BaseRecyclerAdapter<AppInfo,BaseViewHolder> {


    public AppInfoListAdapter(List<AppInfo> data) {
        super(data);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.view_item_app_info;
    }

    @Override
    public void bindData(BaseViewHolder holder, int position) {
        AppInfo appInfo = mData.get(position);
        Observable.just(appInfo.getIcon())
                .map(iconPath -> BitmapDrawable.createFromPath(
                        iconPath))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> holder.getImageView(R.id.iv_app_icon).setImageDrawable(bitmap));

        holder.getTextView(R.id.tv_app_name).setText(appInfo.getName());

        holder.getTextView(R.id.tv_version).setText(appInfo.getVersion());
    }
}
