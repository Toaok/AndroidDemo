package com.toaok.study.update.strategy;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

/**
 * Created by sj on 20/04/2017.
 */

public abstract class Rule {

    /**
     * 校验规则
     * @param versionRequestBean 请求参数
     * @param ruleBean 具体规则
     * @return
     */
    public abstract boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean);
}