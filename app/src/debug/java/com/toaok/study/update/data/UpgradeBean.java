package com.toaok.study.update.data;

import android.text.TextUtils;

/**
 * 规则中的 目标版本 {@link RuleBean#targetVersion}
 * Created by sj on 20/04/2017.
 */

public class UpgradeBean {

    /**
     * 版本名
     */
    String versionName;
    /**
     * 下载地址
     */
    String url;
    /**
     * 描述信息
     */
    String desc;

    public String getVersionName() {
        return versionName;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    private UpgradeBean() {}

    private UpgradeBean(Builder builder) {
        this.versionName = builder.versionName;
        this.url = builder.url;
        this.desc = builder.desc;
    }

    public static class Builder {

        /**
         * 版本名
         */
        String versionName;
        /**
         * 下载地址
         */
        String url;
        /**
         * 描述信息
         */
        String desc;

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setVersionName(String versionName) {
            this.versionName = versionName;
            return this;
        }

        public Builder() {
        }

        public UpgradeBean build() {
            /**
             * 目标版本不能为空
             */
            if(TextUtils.isEmpty(versionName)) {
                throw new NullPointerException("Target version can't be null!");
            }
            return new UpgradeBean(this);
        }
    }
}
