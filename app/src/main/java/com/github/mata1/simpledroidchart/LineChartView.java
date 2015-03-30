package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View class for line charts
 */
public class LineChartView extends ChartView {

    private boolean mShowVerGrid;

    private boolean mShowLines = true;
    private boolean mShowPoints = true;

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        // draw horizontal grid
        if (mShowHorGrid) {
            float nLines = getHeight() / 50;
            float dist = getHeight() / nLines;
            for (int i = 0; i <= nLines; i++)
                canvas.drawLine(0, i * dist, getWidth(), i * dist, mGridPaint);
        }
        // draw vertical grid
        if (mShowVerGrid) {
            for (int i = 0; i < mDataSet.size(); i++) {
                float x = calcX(i);
                canvas.drawLine(x, getHeight(), x, 0, mGridPaint);
            }
        }

        // draw chart
        int j = 0; // index for palette
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

                mChartPaint.setColor(PASTEL_PALETTE[j % PASTEL_PALETTE.length]);
                j += 5;
                canvas.drawCircle(ax, ay, r, mChartPaint);
                //canvas.drawText(a.getyValue()+"", fx + 10, ay, mChartPaint); // DEBUG

                // last point
                if (i == mDataSet.size() - 2) {
                    mChartPaint.setColor(PASTEL_PALETTE[j % PASTEL_PALETTE.length]);
                    canvas.drawCircle(bx, by, r, mChartPaint);
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
        return (getWidth() - getPaddingRight() - getPaddingLeft()) / (mDataSet.size() - 1) * i + getPaddingLeft();
    }

    /**
     * Calculate data Y position for given value
     * @param value data value
     * @return calculated Y position
     */
    private float calcY(float value) {
        float relY = (value - mDataSet.getMin()) / (mDataSet.getMax() - mDataSet.getMin());
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
}
