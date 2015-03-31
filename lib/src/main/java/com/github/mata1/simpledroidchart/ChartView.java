package com.github.mata1.simpledroidchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.github.mata1.simpledroidchart.data.DataSet;
import com.github.mata1.simpledroidchart.palettes.ColorPalette;
import com.github.mata1.simpledroidchart.palettes.PaletteType;

/**
 * Abstract class for chart view
 */
public abstract class ChartView extends View {

    protected Paint mChartPaint, mGridPaint, mValuePaint;

    protected DataSet mDataSet;

    protected ColorPalette mColorPalette;

    protected boolean mShowHorGrid;

    protected boolean mShowValues;


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

        // init color palette
        mColorPalette = ColorPalette.getPalette(PaletteType.PASTEL);
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

            int paletteOrdinal = a.getInt(R.styleable.ChartView_colorPalette, 0);
            if (paletteOrdinal >= 0 && paletteOrdinal < PaletteType.values().length)
                mColorPalette = ColorPalette.getPalette(PaletteType.values()[paletteOrdinal]);

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

    /**
     * Set chart color palette
     * @param colorPalette new color palette
     */
    public void setColorPalette(ColorPalette colorPalette) {
        mColorPalette = colorPalette;
        invalidate();
    }

    protected abstract void onDraw(Canvas canvas);
}
