package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.github.mata1.simpledroidchart.data.DataSet;

/**
 * Abstract class for chart view
 */
public abstract class ChartView extends View {

    protected Paint mChartPaint, mGridPaint, mValuePaint;

    protected DataSet mDataSet;

    protected boolean mShowHorGrid;

    protected boolean mShowValues;

    // pastel colors palette
    public static final int[] PASTEL_PALETTE = new int[] {
            Color.rgb(246, 150, 121),
            Color.rgb(249, 173, 129),
            Color.rgb(253, 198, 137),
            Color.rgb(255, 247, 153),
            Color.rgb(196, 223, 155),
            Color.rgb(163, 211, 156),
            Color.rgb(130, 202, 156),
            Color.rgb(122, 204, 200),
            Color.rgb(109, 207, 246),
            Color.rgb(125, 167, 217),
            Color.rgb(131, 147, 202),
            Color.rgb(135, 129, 189),
            Color.rgb(161, 134, 190),
            Color.rgb(189, 140, 191),
            Color.rgb(244, 154, 193),
            Color.rgb(245, 152, 157)
    };

    public ChartView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        init();
        initAttributes(attrs);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    /**
     * Initialize objects
     */
    private void init() {
        // init paints
        mChartPaint = new Paint();
        mChartPaint.setAntiAlias(true);

        mGridPaint = new Paint();

        mValuePaint = new Paint();
        mValuePaint.setAntiAlias(true);
        mValuePaint.setTextAlign(Paint.Align.CENTER);
        mValuePaint.setFakeBoldText(true);

        // init data
        mDataSet = new DataSet();
    }

    /**
     * Initialize XML attributes
     * @param attrs xml attribute set
     */
    private void initAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ChartView);
        try {
            mShowHorGrid = a.getBoolean(R.styleable.ChartView_showHorizontalGrid, false);
            mChartPaint.setStrokeWidth(a.getFloat(R.styleable.ChartView_strokeWidth, 2));
            mChartPaint.setColor(a.getColor(R.styleable.ChartView_chartColor, Color.BLACK));
            mGridPaint.setStrokeWidth(a.getFloat(R.styleable.ChartView_gridStrokeWidth, 1));
            mGridPaint.setColor(a.getColor(R.styleable.ChartView_gridColor, Color.LTGRAY));
            mShowValues = a.getBoolean(R.styleable.ChartView_showValues, false);
        } finally {
            a.recycle();
        }
    }

    /**
     * Set data for chart view
     * @param dataSet data for chart
     */
    public void setData(DataSet dataSet) {
        if (dataSet == null)
            return; // TODO throw exception
        mDataSet = dataSet;
        invalidate();
    }

    /**
     * Set color of chart paint
     * @param color chart color for lines, bars, ...
     */
    public void setChartColor(int color) {
        mChartPaint.setColor(color);
        invalidate();
    }

    /**
     * Set color for grid lines
     * @param color grid line color
     */
    public void setGridColor(int color) {
        mGridPaint.setColor(color);
        invalidate();
    }

    /**
     * Set Stroke width for chart
     * @param width stroke width
     */
    public void setStrokeWidth(float width) {
        mChartPaint.setStrokeWidth(width);
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

    /**
     * Show data values
     * @param showValues whether to show data values
     */
    public void setShowValues(boolean showValues) {
        mShowValues = showValues;
        invalidate();
    }

    protected abstract void onDraw(Canvas canvas);
}
