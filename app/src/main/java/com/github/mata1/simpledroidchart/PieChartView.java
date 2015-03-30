package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.content.res.TypedArray;
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
        init();
        initAttributes(attrs);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    /**
     * Initialize objects
     */
    private void init() {
        arcRect = new RectF();
        mChartPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Initialize XML attributes
     * @param attrs xml attribute set
     */
    private void initAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ChartView);
        try {
            setStrokeWidth(a.getFloat(R.styleable.ChartView_strokeWidth, 60));
        } finally {
            a.recycle();
        }
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

            // draw values
            if (mShowValues) {
                String value = String.format(mDataSet.getMax() > 10 ? "%.0f" : "%.2f", entry.getyValue());
                double cos = Math.cos(Math.toRadians(angle + part/2)) * r;
                double sin = Math.sin(Math.toRadians(angle + part/2)) * r;
                canvas.drawText(value,
                        arcRect.centerX() + (float)cos,
                        arcRect.centerY() + (float)sin,
                        mValuePaint);
            }

            angle += part + GAP_WIDTH;
            j += 5;
        }

    }
}
