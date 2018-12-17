package com.toaok.study.common.cache;import android.content.Context;import com.toaok.study.model.local.db.dao.impl.SplashImageDao;import com.toaok.study.model.vo.SplashImageBean;import java.util.ArrayList;import java.util.List;/** * @author Toaok * @version 1.0  2018/9/11. */public class SplashImageCache {    private SplashImageDao mSplashImageDao;    private List<SplashImageBean> mData;    private SplashImageBean mSplashImageBean;    public SplashImageCache(Context context) {        mSplashImageDao = new SplashImageDao(context);        mSplashImageBean = mSplashImageDao.randomQueryOne();    }    /**     * 随机查询     */    public void randomQuery(){        mSplashImageBean=mSplashImageDao.randomQueryOne();    }    public void saveData() {        if (mData == null || mData.isEmpty()) {            return;        }        mSplashImageDao.save(mData);    }    public boolean isEmpty() {        if (mSplashImageBean == null) {            return true;        } else {            return false;        }    }    public SplashImageBean getSplashImageBean() {        return mSplashImageBean;    }    public void setData(List<SplashImageBean> data) {        mData = data;        saveData();    }    public void clear() {        if (mSplashImageDao != null) {            mSplashImageDao.close();            mSplashImageDao = null;        }    }}