package com.github.mata1.simpledroidchart.charts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.github.mata1.simpledroidchart.R;
import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View class for horizontal bar charts
 */
public class BarChartView extends ChartView {

    private RectF mBarRect;

    private int mGapWidth = 10; // gap between two columns

    private boolean mShowHorGrid;

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Initialize objects
     */
    @Override
    protected void init() {
        super.init();

        mBarRect = new RectF();
    }

    /**
     * Initialize XML attributes
     * @param attrs xml attribute set
     */
    @Override
    protected void initAttributes(AttributeSet attrs) {
        super.initAttributes(attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.BarChartView);
        try {
            mGapWidth = a.getInt(R.styleable.BarChartView_gapWidth, 10);
            mShowHorGrid = a.getBoolean(R.styleable.LineChartView_showHorizontalGrid, false);
        } finally {
            a.recycle();
        }
    }

    protected void onDraw(Canvas canvas)
    {
        if (mDataSet.size() == 0)
            return;

        // draw horizontal grid
        if (mShowHorGrid) {
            for (Float point : mDataSet.getMajorPoints())
                canvas.drawLine(0, calcY(point), getWidth(), calcY(point), mGridPaint);
        }

        int i = 0; // index for bar
        float width = (getWidth() - getPaddingLeft() - getPaddingRight()) / (float)mDataSet.size(); // bar width

        mColorPalette.reset();
        for (DataEntry entry : mDataSet) {
            mChartPaint.setColor(mColorPalette.next());

            float x = i * width + getPaddingLeft();
            mBarRect.set(x + mGapWidth / 2f,
                    calcY(entry.getyValue()),
                    x + width - mGapWidth / 2f,
                    getHeight());

            canvas.drawRect(mBarRect, mChartPaint);

            // draw values
            if (mShowValues) {
                canvas.drawText(entry.getStringValue(),
                        x + width/2,
                        calcY(entry.getyValue()) + mValuePaint.getTextSize()*1.3f,
                        mValuePaint);
            }

            i++;
        }
    }

    /**
     * Calculate data Y position for given value
     * @param value data value
     * @return calculated Y position
     */
    private float calcY(float value) {
        float relY = value / mDataSet.getMax();
        return getHeight() - relY * (getHeight() - getPaddingTop());
    }

    /**
     * Set gap between bars
     * @param gapWidth gap width
     */
    public void setGapWidth(int gapWidth) {
        mGapWidth = gapWidth;
        invalidate();
    }

    /**
     * Show horizontal grid lines
     * @param showHorGrid whether to show horizontal grid
     */
    public void setShowHorizontalGrid(boolean showHorGrid) {
        mShowHorGrid = showHorGrid;
        invalidate();
    }
}
