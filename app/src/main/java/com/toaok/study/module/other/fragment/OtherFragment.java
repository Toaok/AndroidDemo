package com.toaok.study.module.other.fragment;import android.Manifest;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.view.View;import com.tbruyelle.rxpermissions2.RxPermissions;import com.toaok.study.Config;import com.toaok.study.R;import com.toaok.study.base.presenter.fragment.BaseFragment;import com.toaok.study.module.other.activity.WebActivity;import com.toaok.study.module.other.view.OtherDelegate;import com.toaok.study.utils.AppUtil;import com.toaok.study.utils.PermissionUtils;import com.toaok.utils.core.AppUtils;import java.lang.ref.WeakReference;public class OtherFragment extends BaseFragment<OtherDelegate> {    private static final String TAG = "com.toaok.study.module.other.fragment.OtherFragment";    private RxPermissions mRxPermissions;    public static OtherFragment newInstance(String desc) {        OtherFragment fragment = new OtherFragment();        Bundle bundle = new Bundle();        bundle.putString(TAG, desc);        fragment.setArguments(bundle);        return fragment;    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        init();    }    private void init(){        /**         * 初始化权限请求对象         */        if (mRxPermissions == null) {            mRxPermissions = new RxPermissions(new WeakReference<>(this).get());        }    }    @Override    protected void bindEvenListener() {        super.bindEvenListener();        viewDelegate.setOnClickListener(this, R.id.tv_call_phone, R.id.tv_webview);    }    @SuppressWarnings("unchecked")    @Override    protected Class getDelegateClass() {        return OtherDelegate.class;    }    @Override    public void onClick(View v) {        super.onClick(v);        switch (v.getId()) {            case R.id.tv_call_phone:                mRxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(granted -> {                    if (granted) {                        if (isAdded() && getContext() != null)                            AppUtil.callPhone(getContext(), "12345678910");                    } else {                        if (isAdded() && getContext() != null)                            PermissionUtils.showPermissionDialog(getContext(), getString(R.string.permission_call_phone, AppUtils.getAppName(), AppUtils.getAppName()));                    }                });                break;            case R.id.tv_webview:                WebActivity.startActivity(getActivity(), Config.BAIDU, getString(R.string.title_baidu_text), true, false);                break;        }    }}