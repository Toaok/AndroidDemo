package com.toaok.study.update.strategy;

import android.text.TextUtils;

import com.toaok.study.update.data.RuleBean;
import com.toaok.study.update.data.VersionRequestBean;

/**
 * 渠道 规则
 * <p>
 * 1.所有渠道 {@code null | "" }
 * 2.指定单个渠道 {@code xiaomi}
 * </p>
 * Created by sj on 20/04/2017.
 */

public class ChannelRule extends Rule {
   
    @Override
    public boolean check(VersionRequestBean versionRequestBean, RuleBean ruleBean) {
        if (ruleBean == null) {
            return false;
        }
        String channel = ruleBean.getChannel();

        /**
         * 1.所有渠道 {@code null | "" }
         */
        if(TextUtils.isEmpty(channel)) {
            return true;
        }

        /**
         * 2.指定单个渠道 {@code xiaomi}
         */
        if(versionRequestBean != null && channel.equals(versionRequestBean.getChannel())) {
            return true;
        }
        return false;
    }
}