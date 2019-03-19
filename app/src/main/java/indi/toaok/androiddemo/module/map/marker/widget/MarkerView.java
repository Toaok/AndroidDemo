package indi.toaok.androiddemo.module.map.marker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author Toaok
 * @version 1.0  2019/2/22.
 */
public abstract class MarkerView extends RelativeLayout {
    public MarkerView(Context context) {
        super(context);
    }

    public MarkerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract float getVerticalalProportion();

   public abstract float getHorizontalProportion();
}
