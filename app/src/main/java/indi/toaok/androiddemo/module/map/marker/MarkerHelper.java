package indi.toaok.androiddemo.module.map.marker;

import android.content.Context;

import java.util.List;

import indi.toaok.androiddemo.module.map.marker.bean.imp.MarkerInfoBean;

/**
 * @author Toaok
 * @version 1.0  2019/2/14.
 */
public class MarkerHelper {

    public enum MarkerType {

        /**
         * 默认类型
         */
        DEFAULT,
        /**
         * 汽车类型
         */
        CAR,
        /**
         * 起点和终点
         */
        START,
        /**
         * 起点和终点
         */
        END,
        /***
         * 上车点
         */
        ABOARD_POINT,
        /**
         * 我的位置
         */
        ME;


        /**
         * 图层
         *
         * @return
         */
        public int zIndex() {
            switch (this) {
                case DEFAULT:
                    return -1;
                case CAR:
                    return 0;
                case START:
                    return 1;
                case END:
                    return 2;
                case ABOARD_POINT:
                    return 3;
                case ME:
                    return 4;
                default:
                    break;
            }
            return 0;
        }
    }
    /**
     * 获取marker集合
     *
     * @param points 点集合
     * @param context 上下文
     * @return
     */
    public static MarkerBuilder getPointsMaekerBuilder(List<MarkerInfoBean> points, Context  context) {
        return new MarkerBuilder.Builder()
                .datas(MarkerConvertFactory.create().points2Converter(points, context))
                .type(MarkerType.ABOARD_POINT)
                .build();
    }

    /**
     * 获取marker
     *
     * @param point         点
     * @param context       上下文
     * @return
     */
    public static MarkerBuilder getPointMaekerBuilder(MarkerInfoBean point, Context context) {
        return new MarkerBuilder.Builder()
                .data(MarkerConvertFactory.create().point2Converter(point, context))
                .type(MarkerType.ABOARD_POINT)
                .build();
    }

}
