package com.toaok.study.model.local.db.dao.impl;import android.content.Context;import android.database.sqlite.SQLiteDatabase;import com.toaok.study.model.local.db.DataBaseHelper;import com.toaok.study.model.local.db.dao.ExtendDao;/** * @author Toaok * @version 1.0  2018/9/11. */public abstract class BaseDaoImpl<T> implements ExtendDao<T> {    protected DataBaseHelper mHelper;    protected SQLiteDatabase db;    protected BaseDaoImpl(Context context) {        mHelper = DataBaseHelper.getInstance(context.getApplicationContext());    }    @Override    public void close() {        if (db != null) {            db.close();        }    }    protected  SQLiteDatabase getReadableDatabase(){        return mHelper.getReadableDatabase();    }    protected  SQLiteDatabase getWritableDatabase(){        return mHelper.getWritableDatabase();    }}