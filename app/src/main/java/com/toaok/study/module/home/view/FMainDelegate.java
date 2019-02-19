package com.toaok.study.module.home.view;import android.support.annotation.IntDef;import android.support.v4.view.PagerAdapter;import android.widget.TextView;import com.toaok.study.R;import com.toaok.study.base.view.BaseDelegate;import com.toaok.study.module.home.activity.MainActivity;import com.toaok.study.module.home.widget.NoScrollViewPager;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;/** * 主页面 * * @author Toaok * @version 1.0  2018/9/8. */public class FMainDelegate extends BaseDelegate {    public static final int MAP_PAGER = 0;    public static final int KLINE_PAGER = 1;    public static final int OTHER_PAGER = 2;    /**     * view pager index     * 使用注解代替Enum,限定类型     */    @IntDef({MAP_PAGER, KLINE_PAGER, OTHER_PAGER})    @Retention(RetentionPolicy.SOURCE)    public @interface VPI {    }    private TextView mMap;    private TextView mKLine;    private TextView mOther;    private NoScrollViewPager mViewPager;    @Override    public int getRootLayoutId() {        return R.layout.fragment_main;    }    @Override    public void initWidget() {        super.initWidget();        mMap = get(R.id.btn_map);        mKLine = get(R.id.btn_kline);        mOther = get(R.id.btn_other);        mViewPager = get(R.id.ssvp_home);    }    public NoScrollViewPager getViewPager() {        return mViewPager;    }    public void setViewPagerAdapter(PagerAdapter pagerAdapter) {        mViewPager.setAdapter(pagerAdapter);    }    public void setOffscreenPageLimit(int limit) {        mViewPager.setOffscreenPageLimit(limit);    }    public void setTabBackground(@VPI int pager) {        if (getActivity() != null && getActivity() instanceof MainActivity) {            switch (pager) {                case MAP_PAGER:                    mMap.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));                    mKLine.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mOther.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mViewPager.setCurrentItem(MAP_PAGER, false);                    ((MainActivity) getActivity()).setTitle(getString(R.string.home_map));                    break;                case KLINE_PAGER:                    mMap.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mKLine.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));                    mOther.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mViewPager.setCurrentItem(KLINE_PAGER, false);                    ((MainActivity) getActivity()).setTitle(getString(R.string.home_kline));                    break;                case OTHER_PAGER:                    mMap.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mKLine.setTextColor(getActivity().getResources().getColor(R.color.color_80000000));                    mOther.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));                    mViewPager.setCurrentItem(OTHER_PAGER, false);                    ((MainActivity) getActivity()).setTitle(getString(R.string.home_other));                    break;                default:                    break;            }        }    }}