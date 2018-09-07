package com.toaok.study.update.strategy;

import android.text.TextUtils;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

/**
 * 版本匹配范围 规则
 * <p>
 * 1.所有版本 {@code null | "" }
 * 2.指定单个版本 {@code 1.4.3}
 * 3.小于某版本 {@code 1.4.3~}
 * 4.大于某版本 {@code ~1.4.}
 * 5.版本区间 {@code 1.4.3~1.5}
 * </p>
 * 其他为非法格式
 * Created by sj on 20/04/2017.
 */

public class VersionRangeRule extends Rule {

    static final String SPILT_CHAR = "~";

    /**
     * TODO 校验当前版本是否属于版本范围之内
     * @param versionRequestBean 请求参数
     * @param ruleBean 具体规则
     * @return
     */
    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
        if (ruleBean == null) {
            return false;
        }
        String versionRangeStr = ruleBean.getRangeVersion();

        /**
         * 所有版本 {@code null | "" }
         */
        if (TextUtils.isEmpty(versionRangeStr)) {
            return true;
        }

        int index = versionRangeStr.indexOf("~");

        /**
         * 指定单个版本 {@code 1.4.3}
         */
        if (index < 0) {

        }
        /**
         * 小于某版本 {@code 1.4.3~}
         */
        else if (index == 0) {

        }
        /**
         * 大于某版本 {@code ~1.4.}
         */
        else if (index == versionRangeStr.length() - 1) {

        }
        /**
         * 版本区间 {@code 1.4.3~1.5}
         */
        else {

        }
        return false;
    }
}
