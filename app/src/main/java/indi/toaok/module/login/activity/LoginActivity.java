package indi.toaok.module.login.activity;import android.os.Bundle;import indi.toaok.R;import indi.toaok.base.presenter.activity.BaseActivity;import indi.toaok.themvp.databind.DataBinder;import indi.toaok.base.presenter.activity.BaseActivity;public class LoginActivity extends BaseActivity {    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);    }    @Override    protected Class getDelegateClass() {        return null;    }    @Override    public DataBinder getDataBinder() {        return null;    }}