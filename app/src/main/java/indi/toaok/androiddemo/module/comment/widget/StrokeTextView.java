package indi.toaok.androiddemo.module.comment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

import androidx.appcompat.widget.AppCompatTextView;
import indi.toaok.androiddemo.R;


/**
 * 文字带边框的TextView
 * @author Toaok
 * @version 1.0  2019/2/25.
 */
public class StrokeTextView extends AppCompatTextView {

    private static final String TAG=StrokeTextView.class.getSimpleName();

    private Paint mStrokePaint;
    private float mStrokeSize;
    private int mStrokeColor;
    private boolean isDrawStroke = false; // 默认不采用描边

    public StrokeTextView(Context context) {
        this(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView, 0, 0);
        if (typedArray != null) {
            mStrokeColor = typedArray.getColor(R.styleable.StrokeTextView_strokeColor, Color.WHITE);
            mStrokeSize = typedArray.getDimension(R.styleable.StrokeTextView_strokeSize, 0.0f);
            isDrawStroke = typedArray.getBoolean(R.styleable.StrokeTextView_isDrawStroke, false);
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isDrawStroke) {
            mStrokePaint = getPaint();//获取当前画笔
            TextPaint oldPaint = new TextPaint();
            oldPaint.set(mStrokePaint);//备份原始画笔
            int oldColor=getCurrentTextColor();//备份原始颜色
            // 描边
            setTextColorUseReflection(mStrokeColor);
            mStrokePaint.setStrokeWidth(mStrokeSize);  // 描边宽度
            mStrokePaint.setStyle(Paint.Style.FILL_AND_STROKE); //描边种类
            mStrokePaint.setFakeBoldText(true); // 外层text采用粗体
            super.onDraw(canvas);
            //恢复原先的画笔
            setTextColorUseReflection(oldColor);
            mStrokePaint.set(oldPaint);
        }
        super.onDraw(canvas);
    }

    private void setTextColorUseReflection(int color) {
        Field textColorField;
        try {
            textColorField = TextView.class.getDeclaredField("mCurTextColor");
            textColorField.setAccessible(true);
            textColorField.set(this,color);
            textColorField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mStrokePaint.setColor(color);
    }

}