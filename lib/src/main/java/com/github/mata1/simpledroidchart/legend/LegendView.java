package com.github.mata1.simpledroidchart.legend;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.github.mata1.simpledroidchart.R;
import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View displaying chart legend
 */
public class LegendView extends View {

    public enum IconType {
        SQUARE, CIRCLE
    }

    private Paint mPaint;
    private Legend mLegend;
    private IconType mIconType;

    private int mTextColor;
    private int mIconGap;
    private boolean mShowValue;

    public LegendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public LegendView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    /**
     * Initialize objects
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mIconType = IconType.SQUARE;
    }

    /**
     * Initialize XML attributes
     * @param attrs xml attribute set
     */
    private void initAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LegendView);
        try {
            mTextColor = a.getColor(R.styleable.LegendView_textColor, Color.BLACK);
            mShowValue = a.getBoolean(R.styleable.LegendView_showValue, false);
            mIconGap = a.getInteger(R.styleable.LegendView_iconGap, 6);

            int iconOrdinal = a.getInt(R.styleable.LegendView_iconType, 0);
            if (iconOrdinal >= 0 && iconOrdinal < IconType.values().length)
                mIconType = IconType.values()[iconOrdinal];

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mLegend == null || mLegend.getDataSet().isEmpty())
            return;

        float height = getHeight() - getPaddingTop() - getPaddingBottom() - (mLegend.getDataSet().size() - 1) * mIconGap;
        float part = height / mLegend.getDataSet().size();
        int i = 0;

        mPaint.setTextSize(part / 2);

        mLegend.getColorPalette().reset();
        for (DataEntry entry : mLegend.getDataSet()) {
            mPaint.setColor(mLegend.getColorPalette().next());

            float y = i * (part + mIconGap);

            // draw icon
            switch (mIconType) {
                case CIRCLE:
                    float r = part/2;
                    canvas.drawCircle(
                            r + getPaddingLeft(), // x
                            y + r + getPaddingTop(), // y
                            r, // radius
                            mPaint
                    );
                    break;
                case SQUARE:
                    canvas.drawRect(
                            getPaddingLeft(), // left
                            y + getPaddingTop(), // top
                            part + getPaddingLeft(), // right
                            y + part + getPaddingTop(), // bottom
                            mPaint
                    );
                    break;
            }

            // draw text
            mPaint.setColor(mTextColor);
            String label = entry.getLabel();
            if (mShowValue)
                label += String.format(" (%s)", entry.getStringValue());
            canvas.drawText(label,
                    part + mIconGap + getPaddingLeft(), // x
                    y + part/2 + mPaint.getTextSize()/3 + getPaddingTop(), // y
                    mPaint
            );

            i++;
        }
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        if (mLegend != null && !mLegend.getDataSet().isEmpty()) {
            DataSet dataSet = mLegend.getDataSet();
            float height = getHeight() - getPaddingTop() - getPaddingBottom() - (dataSet.size() - 1) * mIconGap;
            float part = height / dataSet.size();
            float labelW = 0;

            for (DataEntry entry : dataSet)
                 labelW = mPaint.measureText(entry.getLabel()) > labelW ? mPaint.measureText(entry.getLabel()) : labelW;

            desiredWidth = getPaddingLeft() + (int)part + mIconGap + (int)labelW + getPaddingRight();
            //desiredHeight = getPaddingTop() * dataSet.size() * part
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }*/

    /**
     * Set chart data for legend
     * @param legend chart data
     */
    public void setLegend(Legend legend) {
        mLegend = legend;
        invalidate();
    }

    /**
     * Set label text color
     * @param textColor label text color
     */
    public void setTextColor(int textColor) {
        mTextColor = textColor;
        invalidate();
    }

    /**
     * Show data value beside text label
     * @param showValue whether to show value
     */
    public void setShowValue(boolean showValue) {
        mShowValue = showValue;
        invalidate();
    }

    /**
     * Set label icon type
     * @param iconType label icon type
     */
    public void setIconType(IconType iconType) {
        mIconType = iconType;
        invalidate();
    }

    /**
     * Set gap between icons
     * @param iconGap gap between icons
     */
    public void setIconGap(int iconGap) {
        mIconGap = iconGap;
        invalidate();
    }
}
