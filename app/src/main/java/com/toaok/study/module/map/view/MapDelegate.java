package com.toaok.study.module.map.view;import android.widget.TextView;import com.toaok.study.R;import com.toaok.study.base.view.BaseDelegate;/** * @author Toaok * @version 1.0  2018/9/8. */public class MapDelegate extends BaseDelegate {    private TextView tvTestString;    @Override    public int getRootLayoutId() {        return R.layout.fragment_map;    }    @Override    public void initWidget() {        super.initWidget();        tvTestString=get(R.id.tv_test_string);    }    public void setTvTestString(CharSequence tvTestString) {        this.tvTestString.setText(tvTestString);    }    public TextView getTvTestString() {        return tvTestString;    }}