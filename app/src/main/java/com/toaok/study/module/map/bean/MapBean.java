package com.toaok.study.module.map.bean;

import com.amap.api.services.route.DrivePath;
import com.toaok.themvp.model.IModel;

/**
 * @author Toaok
 * @version 1.0  2018/11/7.
 */
public class MapBean implements IModel {

    /**
     * 规划的路线 时间最短
     */
    private DrivePath timeShortest;

    /**
     * 规划的路线 距离最短
     */
    private DrivePath distanceShortest;

    /**
     * 规划的路线 躲避拥堵
     */
    private DrivePath avoidCongestion;





    /**
     * 消息
     */
    private String message;


    public DrivePath getTimeShortest() {
        return timeShortest;
    }

    public void setTimeShortest(DrivePath timeShortest) {
        this.timeShortest = timeShortest;
    }

    public DrivePath getDistanceShortest() {
        return distanceShortest;
    }

    public void setDistanceShortest(DrivePath distanceShortest) {
        this.distanceShortest = distanceShortest;
    }

    public DrivePath getAvoidCongestion() {
        return avoidCongestion;
    }

    public void setAvoidCongestion(DrivePath avoidCongestion) {
        this.avoidCongestion = avoidCongestion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
