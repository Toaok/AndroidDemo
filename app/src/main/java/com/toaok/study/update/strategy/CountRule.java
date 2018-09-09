package com.toaok.study.update.strategy;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

/**
 * 下发数量 规则
 * Created by sj on 20/04/2017.
 */

public class CountRule extends Rule {

    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {

        /**
         * TODO 当前版本先不做
         */
        return true;
    }
}
