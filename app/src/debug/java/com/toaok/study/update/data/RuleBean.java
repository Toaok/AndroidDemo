package com.toaok.study.update.data;

import java.util.Date;

/**
 * 规则
 * Created by sj on 20/04/2017.
 */

public class RuleBean {

    /**
     * 优先级
     */
    int priority;
    /**
     * 启用状态
     */
    boolean isValid;
    /**
     * 生效时间
     */
    Date validDate;
    /**
     * 下发最大值 （无限制 指定值）
     */
    int pushMax;
    /**
     * 已下发数量
     */
    int pushed;
    /**
     * 版本范围（所有 指定范围）
     */
    String rangeVersion;
    /**
     * 将要升级到的目标版本
     */
    UpgradeBean targetVersion;
    /**
     * 渠道（所有 指定渠道）
     */
    String channel;
    /**
     * 是否传递 （默认true）
     */
    boolean isIntercept;
    /**
     * 更新策略 （强制 建议）
     */
    int upgradeStrategy;

    public int getUpgradeStrategy() {
        return upgradeStrategy;
    }

    public boolean isIntercept() {
        return isIntercept;
    }

    public String getChannel() {
        return channel;
    }

    public UpgradeBean getTargetVersion() {
        return targetVersion;
    }

    public String getRangeVersion() {
        return rangeVersion;
    }

    public int getPushed() {
        return pushed;
    }

    public int getPushMax() {
        return pushMax;
    }

    public Date getValidDate() {
        return validDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public int getPriority() {
        return priority;
    }

    private RuleBean() {}

    private RuleBean(Builder builder) {
        this.priority = builder.priority;
        this.isValid = builder.isValid;
        this.validDate = builder.validDate;
        this.pushMax = builder.pushMax;
        this.pushed = builder.pushed;
        this.rangeVersion = builder.rangeVersion;
        this.targetVersion = builder.targetVersion;
        this.channel = builder.channel;
        this.isIntercept = builder.isIntercept;
        this.upgradeStrategy = builder.upgradeStrategy;
    }

    public static class Builder {
        /**
         * 优先级
         */
        int priority;
        /**
         * 启用状态
         */
        boolean isValid;
        /**
         * 生效时间
         */
        Date validDate;
        /**
         * 下发最大值 （无限制 指定值）
         */
        int pushMax;
        /**
         * 已下发数量
         */
        int pushed;
        /**
         * 版本范围（所有 指定范围）
         */
        String rangeVersion;
        /**
         * 将要升级到的目标版本
         */
        UpgradeBean targetVersion;
        /**
         * 渠道（所有 指定渠道）
         */
        String channel;
        /**
         * 是否传递 （默认true）
         */
        boolean isIntercept;
        /**
         * 更新策略 （强制 建议）
         */
        int upgradeStrategy;

        public Builder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder setValid(boolean valid) {
            isValid = valid;
            return this;
        }

        public Builder setValidDate(Date validDate) {
            this.validDate = validDate;
            return this;
        }

        public Builder setPushMax(int pushMax) {
            this.pushMax = pushMax;
            return this;
        }

        public Builder setPushed(int pushed) {
            this.pushed = pushed;
            return this;
        }

        public Builder setRangeVersion(String rangeVersion) {
            this.rangeVersion = rangeVersion;
            return this;
        }

        public Builder setTargetVersion(UpgradeBean targetVersion) {
            this.targetVersion = targetVersion;
            return this;
        }

        public Builder setChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder setIntercept(boolean intercept) {
            isIntercept = intercept;
            return this;
        }

        public Builder setUpgradeStrategy(int upgradeStrategy) {
            this.upgradeStrategy = upgradeStrategy;
            return this;
        }

        public Builder() {
        }

        public RuleBean build() {
            /**
             * 目标版本不能为空，或版本号不能为空
             */
            if(targetVersion == null) {
                throw new NullPointerException("Target version can't be null!");
            }
            return new RuleBean(this);
        }
    }
}
