package indi.toaok.module.map.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

import indi.toaok.R;


/**
 * @author Toaok
 * @version 1.0  2019/2/25.
 */
public class StrokeTextView extends AppCompatTextView {

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
        mStrokePaint = getPaint();//获取当前画笔
        TextPaint oldPaint = new TextPaint();
        oldPaint.set(mStrokePaint);
        int oldColor=getCurrentTextColor();
        if (isDrawStroke) {
            // 描边
            setTextColor(mStrokeColor);
            mStrokePaint.setStrokeWidth(mStrokeSize);  // 描边宽度
            mStrokePaint.setStyle(Paint.Style.FILL_AND_STROKE); //描边种类
            mStrokePaint.setFakeBoldText(true); // 外层text采用粗体
            super.onDraw(canvas);

            //恢复原先的画笔
            setTextColor(oldColor);
            mStrokePaint.set(oldPaint);
        }
        super.onDraw(canvas);
    }

}