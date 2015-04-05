package com.github.mata1.simpledroidchart.legend;

import com.github.mata1.simpledroidchart.data.DataSet;
import com.github.mata1.simpledroidchart.palettes.ColorPalette;

/**
 * Container for chart data needed for Legend View
 */
public class Legend {

    private DataSet mDataSet;
    private ColorPalette mColorPalette;

    public Legend(DataSet dataSet, ColorPalette colorPalette) {
        mDataSet = dataSet;
        mColorPalette = colorPalette;
    }

    /**
     * Get legend data set
     * @return legend data set
     */
    public DataSet getDataSet() {
        return mDataSet;
    }

    /**
     * Get legend color palette
     * @return legend color palette
     */
    public ColorPalette getColorPalette() {
        return mColorPalette;
    }
}
