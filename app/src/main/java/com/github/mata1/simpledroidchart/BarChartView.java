package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View class for horizontal bar charts
 */
public class BarChartView extends ChartView {

    private static final int GAP_WIDTH = 10; // gap between two columns

    private RectF barRect;

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        barRect = new RectF();
    }

    protected void onDraw(Canvas canvas)
    {
        if (mDataSet.size() == 0)
            return;

        int i = 0; // index for bar
        int j = 0; // index for palette
        int width = (getWidth() - getPaddingLeft() - getPaddingRight()) / mDataSet.size();

        for (DataEntry entry : mDataSet) {
            mChartPaint.setColor(PASTEL_PALETTE[(j % PASTEL_PALETTE.length)]);

            float relY = entry.getyValue() / mDataSet.getMax();
            barRect.set(i * width + getPaddingLeft() + GAP_WIDTH/2,
                    getHeight() - relY * getHeight() + getPaddingTop(),
                    i * width + width + getPaddingLeft() - GAP_WIDTH/2,
                    getHeight());

            canvas.drawRect(barRect, mChartPaint);

            i++;
            j += 5;
        }
    }
}