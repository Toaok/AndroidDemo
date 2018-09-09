package com.toaok.study.update.strategy;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

import java.util.Date;

/**
 * 规则生效时间 规则
 * Created by sj on 20/04/2017.
 */
public class ValidDateRule extends Rule {

    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
        if (ruleBean == null) {
            return false;
        }

        Date date = ruleBean.getValidDate();
        /**
         * 非法时间
         */
        if (date == null) {
            return false;
        }
        /**
         * 当前时间小于生效时间
         */
        if (new Date().getTime() < date.getTime()) {
            return false;
        }
        return true;
    }
}