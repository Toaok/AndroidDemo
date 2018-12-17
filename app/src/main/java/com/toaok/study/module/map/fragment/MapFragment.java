package com.toaok.study.module.map.fragment;import android.graphics.Color;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.text.TextPaint;import android.text.method.LinkMovementMethod;import android.text.style.ClickableSpan;import android.view.View;import com.toaok.study.base.presenter.fragment.BaseFragment;import com.toaok.study.module.map.bean.MapBean;import com.toaok.study.module.map.databinder.MapDataBinder;import com.toaok.study.module.map.view.MapDelegate;import com.toaok.themvp.databind.DataBinder;import com.toaok.utils.core.SpanUtils;public class MapFragment extends BaseFragment<MapDelegate> implements View.OnClickListener {    private static final String TAG = "com.toaok.study.module.other.fragment.MapFragment";    public static MapFragment newInstance(String desc) {        MapFragment fragment = new MapFragment();        Bundle bundle = new Bundle();        bundle.putString(TAG, desc);        fragment.setArguments(bundle);        return fragment;    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        ClickableSpan clickableSpan = new ClickableSpan() {            @Override            public void onClick(View widget) {                viewDelegate.toast("onclick");            }            @Override            public void updateDrawState(TextPaint ds) {                ds.setColor(Color.RED);                ds.setUnderlineText(false);            }        };        viewDelegate.getTvTestString().setMovementMethod(LinkMovementMethod.getInstance());        notifyModelChanged(new MapBean(/*new SpanUtils()                .append("点击事件")                .setClickSpan(clickableSpan)                .create()                .append()*/                new SpanUtils()                        .append("url")                        .setUrl("https://www.baidu.com/")                        .create()                )        );    }    @Override    protected void bindEvenListener() {        super.bindEvenListener();    }    @SuppressWarnings("unchecked")    @Override    protected Class<MapDelegate> getDelegateClass() {        return MapDelegate.class;    }    @Override    public void onClick(View v) {        switch (v.getId()) {        }    }    @Override    protected DataBinder getDataBinder() {        return new MapDataBinder();    }}