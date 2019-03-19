package indi.toaok.androiddemo.module.other.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import indi.toaok.androiddemo.base.presenter.fragment.BaseFragment;
import indi.toaok.androiddemo.module.other.view.WebViewDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/10/16.
 */
public class WebViewFragment extends BaseFragment<WebViewDelegate> {

    private static final String TAG = "indi.toaok.androiddemo.module.other.fragment.WebViewFragment";

    private static final String EXTRA_URL = "EXTRA_URL";

//    protected ShareBottomView shareBottomView;


    public static WebViewFragment newInstance(String url) {
        return newInstance("", url);
    }

    public static WebViewFragment newInstance(String desc, String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(TAG, desc);
        args.putString(EXTRA_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
    }

    private void initWebView(){
        Bundle args=getArguments();
        if(args!=null){
            if (!TextUtils.isEmpty(args.getString(EXTRA_URL))) {
                viewDelegate.initWebView(args.getString(EXTRA_URL));
            }
        }
    }

    @Override
    protected Class<WebViewDelegate> getDelegateClass() {
        return WebViewDelegate.class;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewDelegate.getWvContent().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewDelegate.getWvContent().onResume();
    }

    public void goBack() {
        viewDelegate.goBack();
    }
}
