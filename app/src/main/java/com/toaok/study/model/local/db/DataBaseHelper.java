package com.toaok.study.model.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Toaok
 * @version 1.0  2018/9/10.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "android_demo.db";
    private static final int VERSION = 1;


    private static DataBaseHelper sDataBaseHelper;

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (sDataBaseHelper == null) {
            Context applicationContext = context.getApplicationContext();
            sDataBaseHelper = new DataBaseHelper(applicationContext);
        }
        return sDataBaseHelper;
    }

    private DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private DataBaseHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }


    /**
     * 创建数据库表
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //启动页图片路径
        db.execSQL(SQL.CREATE_SPLASH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //使用for实现跨版本升级
        for (int i = oldVersion; i <= newVersion; i++) {
            switch (i) {
                case 1:
                    break;
                case 2:
                    updataVersion(db);
                    break;
                default:
                    break;
            }
        }

    }

    private void updataVersion(SQLiteDatabase db) {
        //更新内容，to do ....
    }
}
