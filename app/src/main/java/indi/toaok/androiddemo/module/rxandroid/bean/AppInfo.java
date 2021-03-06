package indi.toaok.androiddemo.module.rxandroid.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Toaok
 * @version 1.0  2019/3/12.
 */
@Data
@Accessors(prefix = "m")
public class AppInfo implements Comparable<Object> {

    private String mName;
    private String mIcon;

    private String mVersion;

    private long mLastUpdateTime;

    public AppInfo(String name, String icon, String version, long lastUpdateTime) {
        mLastUpdateTime = lastUpdateTime;
        mName = name;
        mIcon = icon;
        mVersion = version;
    }

    public AppInfo(String name, String icon, long lastUpdateTime) {
        mLastUpdateTime = lastUpdateTime;
        mName = name;
        mIcon = icon;
    }


    @Override
    public int compareTo(Object another) {
        AppInfo info = (AppInfo) another;
        return getName().compareTo(info.getName());
    }
}
