package com.toaok.study.update.strategy;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.UpgradeBean;
import com.toaok.study.update.data.VersionRequestBean;
import com.toaok.study.update.util.VersionUtil;

/**
 * 当前版本和目标版本大小比较（小于才升级） 规则
 * Created by sj on 20/04/2017.
 */
public class TargetRule extends Rule {

    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
        if (ruleBean == null) {
            return false;
        }

        UpgradeBean upgradeBean = ruleBean.getTargetVersion();

        /**
         * 无法获取目标版本的数据
         */
        if (upgradeBean == null) {
            return false;
        }

        /**
         * 请求的版本小于目标版本，合法升级
         */
        try {
            if (VersionUtil.compareVersion(upgradeBean.getVersionName(), versionRequestBean.getVersionName()) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
