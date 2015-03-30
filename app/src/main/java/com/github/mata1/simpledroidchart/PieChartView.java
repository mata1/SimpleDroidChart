package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * View class for pie charts
 */
public class PieChartView extends ChartView {

    private static final int GAP_WIDTH = 5; // gap between two values

    private RectF arcRect;

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        arcRect = new RectF();
        mChartPaint.setStyle(Paint.Style.STROKE);
        setStrokeWidth(60); // TODO set relative or constant
    }

    protected void onDraw(Canvas canvas) {
        int r = Math.min(getHeight(), getWidth()) / 2 - (int)mChartPaint.getStrokeWidth() / 2 - getPaddingTop(); // pie radius
        arcRect.set(getWidth() / 2 - r,
                getHeight() / 2 - r,
                getWidth() / 2 + r,
                getHeight() / 2 + r);

        float angle = 0;
        int j = 0; // index for palette
        for (DataEntry entry : mDataSet) {
            float part = entry.getyValue() / mDataSet.getSum() * (360 - mDataSet.size() * GAP_WIDTH);
            mChartPaint.setColor(PASTEL_PALETTE[(j % PASTEL_PALETTE.length)]);
            canvas.drawArc(arcRect, angle, part, false, mChartPaint);

            angle += part + GAP_WIDTH;
            j += 5;
        }

    }
}
