package com.github.mata1.simpledroidchart.charts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mata1.simpledroidchart.R;
import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View class for line charts
 */
public class LineChartView extends ChartView {

    private boolean mShowVerGrid, mShowHorGrid;
    private boolean mShowLines, mShowPoints;

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Initialize XML attributes
     * @param attrs xml attribute set
     */
    @Override
    protected void initAttributes(AttributeSet attrs) {
        super.initAttributes(attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LineChartView);
        try {
            mShowLines = a.getBoolean(R.styleable.LineChartView_showLines, true);
            mShowPoints = a.getBoolean(R.styleable.LineChartView_showPoints, true);
            mShowVerGrid = a.getBoolean(R.styleable.LineChartView_showVerticalGrid, false);
            mShowHorGrid = a.getBoolean(R.styleable.LineChartView_showHorizontalGrid, false);
        } finally {
            a.recycle();
        }
    }

    protected void onDraw(Canvas canvas) {
        // draw horizontal grid
        if (mShowHorGrid && !mDataSet.isEmpty()) {
            for (Float point : mDataSet.getMajorPoints())
                canvas.drawLine(0, calcY(point), getWidth(), calcY(point), mGridPaint);
        }
        // draw vertical grid
        if (mShowVerGrid && !mDataSet.isEmpty()) {
            for (int i = -2; i < mDataSet.size() + 2; i++) {
                float x = calcX(i);
                canvas.drawLine(x, getHeight(), x, 0, mGridPaint);
            }
        }

        // draw chart
        mColorPalette.reset();
        for (int i = 0; i < mDataSet.size() - 1; i++) {
            DataEntry a = mDataSet.get(i);
            DataEntry b = mDataSet.get(i + 1);

            float ax = calcX(i);
            float ay = calcY(a.getyValue());
            float bx = calcX(i + 1);
            float by = calcY(b.getyValue());

            // draw lines
            if (mShowLines) {
                mChartPaint.setColor(Color.BLACK);
                canvas.drawLine(ax, ay, bx, by, mChartPaint);
            }

            // draw points
            if (mShowPoints) {
                int r = 8; // point radius TODO set relative to view size or constant

                mChartPaint.setColor(mColorPalette.next());
                canvas.drawCircle(ax, ay, r, mChartPaint);
                //canvas.drawText(a.getyValue()+"", ax + 10, ay, mChartPaint); // DEBUG

                // last point
                if (i == mDataSet.size() - 2) {
                    mChartPaint.setColor(mColorPalette.next());
                    canvas.drawCircle(bx, by, r, mChartPaint);
                }
            }

            // draw values
            if (mShowValues) {
                canvas.drawText(a.getStringValue(),
                        ax,
                        ay - mValuePaint.getTextSize(),
                        mValuePaint);

                // last point
                if (i == mDataSet.size() - 2) {
                    canvas.drawText(b.getStringValue(),
                            bx,
                            by - mValuePaint.getTextSize(),
                            mValuePaint);
                }
            }
        }
    }

    /**
     * Calculate data X position for given index
     * @param i index
     * @return calculated X position
     */
    private float calcX(int i) {
        return (getWidth() - getPaddingRight() - getPaddingLeft()) / (mDataSet.size() - 1f) * i + getPaddingLeft();
    }

    /**
     * Calculate data Y position for given value
     * @param value data value
     * @return calculated Y position
     */
    private float calcY(float value) {
        float min = mDataSet.getMin(), max = mDataSet.getMax();
        if (max - min == 0)
            return getHeight()/2;
        float relY = (value - min) / (max - min);
        return getHeight() - (relY * (getHeight() - getPaddingTop() - getPaddingBottom()) + getPaddingBottom());
    }

    /**
     * Show lines on line chart
     * @param showLines whether to show lines
     */
    public void setShowLines(boolean showLines) {
        mShowLines = showLines;
        invalidate();
    }

    /**
     * Show points on line chart
     * @param showPoints whether to show points
     */
    public void setShowPoints(boolean showPoints) {
        mShowPoints = showPoints;
        invalidate();
    }

    /**
     * Show vertical grid
     * @param showVerGrid whether to show vertical grid
     */
    public void setShowVerticalGrid(boolean showVerGrid) {
        mShowVerGrid = showVerGrid;
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
