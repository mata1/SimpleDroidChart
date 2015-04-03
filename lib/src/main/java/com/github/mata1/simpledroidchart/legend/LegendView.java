package com.github.mata1.simpledroidchart.legend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.github.mata1.simpledroidchart.data.DataEntry;

/**
 * Created by matej on 03/04/15.
 */
public class LegendView extends View {

    public enum IconType {
        SQUARE, CIRCLE
    }

    private Paint mPaint;
    private Legend mLegend;
    private IconType mIconType;

    private static final int PAD = 5;

    public LegendView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(18);

        mIconType = IconType.CIRCLE;
    }

    public LegendView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mLegend.getDataSet() == null || mLegend.getDataSet().isEmpty())
            return;

        float part = (getHeight() - getPaddingTop() - getPaddingBottom()) / ((float)mLegend.getDataSet().size() + 0);
        int i = 0;

        mLegend.getColorPalette().reset();
        for (DataEntry entry : mLegend.getDataSet()) {
            mPaint.setColor(mLegend.getColorPalette().next());

            // draw icon
            switch (mIconType) {
                case CIRCLE:
                    float r = part/2 - PAD;
                    canvas.drawCircle(
                            getPaddingLeft() + r + PAD, // x
                            i * part + part/2 + getPaddingTop(), // y
                            r, // radius
                            mPaint
                    );
                    break;
                case SQUARE:
                    canvas.drawRect(
                            getPaddingLeft() + PAD, // left
                            i * part + PAD + getPaddingTop(), // top
                            getPaddingLeft() + part - PAD, // right
                            i * part + part - PAD + getPaddingTop(), // bottom
                            mPaint
                    );
                    break;
            }

            // draw text
            mPaint.setColor(Color.BLACK);
            canvas.drawText(entry.getLabel(),
                    getPaddingLeft() + part + PAD, // x
                    i * part + part/2 + mPaint.getTextSize()/2 + getPaddingTop(), // y
                    mPaint
            );

            i++;
        }
    }

    public void setLegend(Legend legend) {
        mLegend = legend;
    }
}
