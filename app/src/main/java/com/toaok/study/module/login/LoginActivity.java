package com.toaok.study.module.login;

import android.os.Bundle;

import com.toaok.study.R;
import com.toaok.study.base.BaseActivity;
import com.toaok.themvp.databind.DataBinder;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected Class getDelegateClass() {
        return null;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
