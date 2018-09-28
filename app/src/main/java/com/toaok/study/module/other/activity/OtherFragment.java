package com.toaok.study.module.other.activity;

import android.Manifest;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.toaok.study.R;
import com.toaok.study.base.BaseFragment;
import com.toaok.study.module.home.view.MainDelegate;
import com.toaok.study.module.other.view.OtherDelegate;
import com.toaok.study.utils.AppUtil;

public class OtherFragment extends BaseFragment<MainDelegate> implements View.OnClickListener {

    public static OtherFragment getInstance() {
        OtherFragment fragment = new OtherFragment();
        return fragment;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.tv_call_phone);
    }

    @Override
    protected Class getDelegateClass() {
        return OtherDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_call_phone:
                new RxPermissions(this).request(Manifest.permission.CALL_PHONE).subscribe(granted -> {
                            if (granted) {
                                if (getActivity() != null)
                                    AppUtil.callPhone(getActivity().getApplicationContext(), "120");
                            } else {
                                if (getActivity() != null)
                                    AppUtil.openAppDetails(getActivity().getApplicationContext(), "电话");
                            }
                        }
                );
                break;
        }
    }
}
