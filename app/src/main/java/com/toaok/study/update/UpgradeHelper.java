package com.toaok.study.update;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.UpgradeBean;
import com.toaok.study.update.data.VersionRequestBean;
import com.toaok.study.update.strategy.ChannelRule;
import com.toaok.study.update.strategy.CountRule;
import com.toaok.study.update.strategy.EnbleRule;
import com.toaok.study.update.strategy.Rule;
import com.toaok.study.update.strategy.TargetRule;
import com.toaok.study.update.strategy.ValidDateRule;
import com.toaok.study.update.strategy.VersionRangeRule;

import java.util.List;

/**
 * Created by sj on 20/04/2017.
 */

public class UpgradeHelper {

    /**
     * 升级规则过滤
     * <p>
     *     所有规则从上到下校验，直至成功
     * </p>
     */
    enum RuleFilter {

        /**
         * 规则 基础
         */
        EnableRule(new EnbleRule()),
        /**
         * 规则 生效时间
         */
        ValidDateRule(new ValidDateRule()),
        /**
         * 规则 下发数量
         */
        CountRule(new CountRule()),
        /**
         * 规则 版本范围
         */
        VersionRangeRule(new VersionRangeRule()),
        /**
         * 规则 目标版本和当期版本比较
         */
        TargetRule(new TargetRule()),
        /**
         * 规则 渠道
         */
        ChannelRule(new ChannelRule());

        Rule rule;

        RuleFilter(Rule rule) {
            this.rule = rule;
        }

        public static boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
            if (ruleBean == null) {
                return false;
            }
            for (RuleFilter ruleFilter : RuleFilter.values()) {
                if (ruleFilter.rule == null) {
                    continue;
                }
                /**
                 * 检测到有效规则
                 */
                if (ruleFilter.rule.check(versionRequestBean, ruleBean)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 获取升级信息
     * @param versionRequestBean 基础请求数据
     * @param ruleBeanList 规则列表
     * @return [null -> 无升级] [非空 -> 有升级]
     */
    public static UpgradeBean getUpgrade(VersionRequestBean versionRequestBean, List<RuleBean> ruleBeanList) {
        if (versionRequestBean == null) {
            return null;
        }
        if (ruleBeanList == null || ruleBeanList.isEmpty()) {
            return null;
        }
        /**
         * 遍历所有规则，直至成功
         */
        for (RuleBean ruleBean : ruleBeanList) {
            if (ruleBean == null) {
                continue;
            }
            if (RuleFilter.check(versionRequestBean, ruleBean)) {
                return ruleBean.getTargetVersion();
            }
            /**
             * 是否支持中断，不再往下执行规则
             */
//            if(!ruleBean.isIntercept()) {
//                return null;
//            }
        }
        return null;
    }
}