package com.toaok.study.model.local.db;/** * @author Toaok * @version 1.0  2018/9/10. */public class SQL {    /**     * 表t_splash_image     */    public static final String SPLASH_TABLE = "t_splash_image";    public static final String SPLASH_IMAGE_ID = "id";    public static final String SPLASH_IMAGE_URL = "url";    //创建表t_splash_image    public static final String CREATE_SPLASH_TABLE = "CREATE TABLE " + SPLASH_TABLE + " ( \n" +            "    " + SPLASH_IMAGE_ID + "  INTEGER         PRIMARY KEY\n" +            "                        NOT NULL\n" +            "                        UNIQUE,\n" +            "    " + SPLASH_IMAGE_URL + " VARCHAR( 255 )  NOT NULL \n" +            ");";}