package com.toaok.study.update.strategy;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

/**
 * 基础条件 规则
 * <p>启用状态</p>
 * Created by sj on 20/04/2017.
 */

public class EnbleRule extends Rule {

    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
        if (ruleBean == null) {
            return false;
        }

        /**
         * 判断启用状态
         */
        if (!ruleBean.isValid()) {
            return false;
        }

        return true;
    }
}
