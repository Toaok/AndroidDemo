package com.toaok.study.update.data;

import android.text.TextUtils;

/**
 * Created by sj on 20/04/2017.
 */

public class VersionRequestBean {

    /**
     * 版本名
     */
    String versionName;

    /**
     * 渠道
     */
    String channel;

    public String getChannel() {
        return channel;
    }

    public String getVersionName() {
        return versionName;
    }

    private VersionRequestBean() {}

    private VersionRequestBean(Builder builder) {
        this.versionName = builder.versionName;
        this.channel = builder.channel;
    }

    public static class Builder {

        /**
         * 版本名
         */
        String versionName;

        /**
         * 渠道
         */
        String channel;

        public Builder setChannle(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder setVersionName(String versionName) {
            this.versionName = versionName;
            return this;
        }

        public Builder() {
        }

        public VersionRequestBean build() {
            /**
             * 目标版本不能为空
             */
            if(TextUtils.isEmpty(versionName)) {
                throw new NullPointerException("Target version can't be null!");
            }
            return new VersionRequestBean(this);
        }
    }
}
