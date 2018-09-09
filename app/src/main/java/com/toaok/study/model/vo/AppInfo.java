package com.toaok.study.model.vo;

import com.blankj.utilcode.util.AppUtils;
import com.toaok.study.BuildConfig;
import com.toaok.themvp.model.IModel;

/**
 * APP 基本信息
 *
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class AppInfo implements IModel {


    private AppInfo() {   }

    private static class AppInfoInstance{
        private static AppInfo sAppInfo=new AppInfo();
    }

    public static AppInfo getInstance(){
        AppInfoInstance.sAppInfo.applicationId= BuildConfig.APPLICATION_ID;
        AppInfoInstance.sAppInfo.buildType=BuildConfig.BUILD_TYPE;
        AppInfoInstance.sAppInfo.environment=BuildConfig.API_HOST;
        AppInfoInstance.sAppInfo.channel=BuildConfig.FLAVOR;
        AppInfoInstance.sAppInfo.versionName= AppUtils.getAppVersionName();
        AppInfoInstance.sAppInfo.versionCode= AppUtils.getAppVersionCode();
        return AppInfoInstance.sAppInfo;
    }

    /**
     * applicationId
     */
    private String applicationId;

    /*
     * 开发环境
     * */
    private String environment;

    /**
     * 打包方式
     */
    private String buildType;


    /*
     *打包渠道
     * */
    private String channel;

    /*
     * versionName
     * 版本名
     * */
    private String versionName;


    /**
     * versionCode
     * 版本号
     */
    private int versionCode;


    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
