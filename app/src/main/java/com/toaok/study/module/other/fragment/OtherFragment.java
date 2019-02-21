package com.toaok.study.module.other.fragment;import android.Manifest;import android.os.Bundle;import android.os.Environment;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.view.View;import com.tbruyelle.rxpermissions2.RxPermissions;import com.toaok.study.Config;import com.toaok.study.R;import com.toaok.study.base.presenter.fragment.BaseFragment;import com.toaok.study.module.other.activity.WebActivity;import com.toaok.study.module.other.view.OtherDelegate;import com.toaok.study.utils.AppUtil;import com.toaok.study.utils.PermissionUtils;import java.io.File;import java.lang.ref.WeakReference;public class OtherFragment extends BaseFragment<OtherDelegate> {    private static final String TAG = "com.toaok.study.module.other.fragment.OtherFragment";    private RxPermissions mRxPermissions;    public static OtherFragment newInstance(String desc) {        OtherFragment fragment = new OtherFragment();        Bundle bundle = new Bundle();        bundle.putString(TAG, desc);        fragment.setArguments(bundle);        return fragment;    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        init();    }    private void init() {        /**         * 初始化权限请求对象         */        if (mRxPermissions == null) {            mRxPermissions = new RxPermissions(new WeakReference<>(this).get());        }    }    @Override    protected void bindEvenListener() {        super.bindEvenListener();        viewDelegate.setOnClickListener(this,                R.id.tv_call_phone,                R.id.tv_install_other_apk,                R.id.tv_webview);    }    @SuppressWarnings("unchecked")    @Override    protected Class getDelegateClass() {        return OtherDelegate.class;    }    @Override    public void onClick(View v) {        super.onClick(v);        switch (v.getId()) {            //拨打电话            case R.id.tv_call_phone:                mRxPermissions                        .request(Manifest.permission.CALL_PHONE)                        .subscribe(granted -> {                            if (isAdded() && getContext() != null) {                                if (granted) {                                    AppUtil.callPhone(getContext(), "12345678910");                                } else {                                    PermissionUtils.showPermissionDialog(getContext(), R.string.permission_call_phone);                                }                            }                        });                break;            //安装APK            case R.id.tv_install_other_apk:                mRxPermissions                        .request(Manifest.permission.READ_EXTERNAL_STORAGE,                                Manifest.permission.WRITE_EXTERNAL_STORAGE)                        .subscribe(granted -> {                            if (isAdded() && getContext() != null) {                                if (granted) {                                    File file = new File(Environment.getExternalStorageDirectory(), "update.apk");                                    if (file.exists()) {                                        AppUtil.installApk(getContext(), file);                                    }else {                                        viewDelegate.toast(R.string.toast_apk_not_exist);                                    }                                } else {                                    PermissionUtils.showPermissionDialog(getContext(), R.string.permission_storage);                                }                            }                        });                break;            //加载H5网页            case R.id.tv_webview:                WebActivity.startActivity(getActivity(), Config.BAIDU, getString(R.string.title_baidu_text), true, false);                break;        }    }}