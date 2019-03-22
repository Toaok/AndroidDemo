package indi.toaok.androiddemo.common.animation;

import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * @author Toaok
 * @version 1.0  2019/3/20.
 */
public class Animation {
    public static final int PAGER_MAP = 0;
    public static final int PAGER_KLINE = 1;
    public static final int PAGER_OTHER = 2;

    public static final int GONE = View.GONE;
    public static final int INVISIBLE = View.INVISIBLE;
    public static final int VISIBLE = View.VISIBLE;

    /**
     * @author Toaok
     * @version 1.0  2019/3/20.
     */
    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }


    /**
     * view pager index
     * 使用注解代替Enum,限定类型
     */
    @IntDef({PAGER_MAP, PAGER_KLINE, PAGER_OTHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VPI {
    }
}
