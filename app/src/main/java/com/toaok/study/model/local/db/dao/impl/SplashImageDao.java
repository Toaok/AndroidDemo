package com.toaok.study.model.local.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.toaok.study.model.local.db.SQL;
import com.toaok.study.model.vo.SplashImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public class SplashImageDao extends BaseDaoImpl<SplashImageBean> {

    private static final String TABLE = SQL.SPLASH_TABLE;
    private final String IMAGE_ID = SQL.SPLASH_IMAGE_ID;
    private final String IMAGE_URL = SQL.SPLASH_IMAGE_URL;

    public SplashImageDao(Context context) {
        super(context);
    }

    @Override
    public List<SplashImageBean> queryAll() {
        db = getReadableDatabase();
        List<SplashImageBean> splashImageBeanList = new ArrayList<>();
        Cursor cursor = db.query(TABLE, null, null, null, null, null, null);

        int idIndex = cursor.getColumnIndex(IMAGE_ID);
        int urlIndex = cursor.getColumnIndex(IMAGE_URL);
        while (cursor.moveToNext()) {
            SplashImageBean splashImageBean = new SplashImageBean();
            splashImageBean.setId(cursor.getInt(idIndex));
            splashImageBean.setUrl(cursor.getString(urlIndex));
            splashImageBeanList.add(splashImageBean);
        }
        cursor.close();
        close();
        return splashImageBeanList;
    }


    @Override
    public void save(List<SplashImageBean> list) {
        try {
            getWritableDatabase().beginTransaction();
            preCompileSave(list);
            getWritableDatabase().setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getWritableDatabase().endTransaction();
        }
        close();
    }

    private void preCompileSave(List<SplashImageBean> list) {
        String sql = "insert into " + TABLE + " values (?,?);";
        SQLiteStatement sqLiteStatement = getReadableDatabase().compileStatement(sql);
        for (SplashImageBean bean : list) {
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindLong(1, bean.getId());
            sqLiteStatement.bindString(2, bean.getUrl());
            sqLiteStatement.executeInsert();
        }
    }

    @Override
    public long insert(SplashImageBean splashImageBean) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_URL, splashImageBean.getUrl());
        long result = db.insert(TABLE, null, values);
        close();
        return result;
    }

    @Override
    public long delete(SplashImageBean splashImageBean) {
        db = getReadableDatabase();
        long result=db.delete(TABLE, IMAGE_ID + "=?", new String[]{splashImageBean.getId() + ""});
        close();
        return result;
    }

    @Override
    public long update(SplashImageBean splashImageBean) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_URL, splashImageBean.getUrl());
        long result=db.update(TABLE, values, IMAGE_ID + "=?", new String[]{splashImageBean.getId() + ""});
        close();
        return result;
    }

    @Override
    public SplashImageBean query(int id) {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE +
                        " WHERE " + IMAGE_ID + "=?",
                new String[]{});
        SplashImageBean splashImageBean = new SplashImageBean();
        int idIndex = cursor.getColumnIndex(IMAGE_ID);
        int urlIndex = cursor.getColumnIndex(IMAGE_URL);
        if (cursor.moveToNext()) {
            splashImageBean.setId(cursor.getInt(idIndex));
            splashImageBean.setUrl(cursor.getString(urlIndex));
        }
        cursor.close();
        close();
        return splashImageBean;
    }
}
