package com.toaok.study.common.cache;

import android.content.Context;

import com.toaok.study.model.local.db.dao.impl.SplashImageDao;
import com.toaok.study.model.vo.SplashImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2018/9/11.
 */
public class SplashImageCache {

    private SplashImageDao mSplashImageDao;
    private List<SplashImageBean > mData;

    public SplashImageCache(Context context) {
        mSplashImageDao = new SplashImageDao(context);
        mData=mSplashImageDao.queryAll();
    }

    public void save(List<SplashImageBean> list){
        mSplashImageDao.save(list);
    }

    public void query(int id){
        mSplashImageDao.query(id);
    }

    public void save(SplashImageBean bean){
        mSplashImageDao.insert(bean);
        if(mData==null){
            mData=new ArrayList<>();
        }
        mData.add(bean);
    }

    public void saveData(){
        if(mData==null||mData.isEmpty()){
            return;
        }
        mSplashImageDao.save(mData);
    }
    public int dataSize(){
        int c=0;
        if (mData!=null){
            c=mData.size();
        }
        return c;
    }

    public boolean isEmpty(){
        if(mData==null){
            return true;
        }else {
            return mData.isEmpty();
        }
    }



    public List<SplashImageBean> getData() {
        return mData;
    }

    public void setData(List<SplashImageBean> data) {
        mData = data;
    }
}

