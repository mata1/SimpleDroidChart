package com.github.mata1.simpledroidchart.legend;

import com.github.mata1.simpledroidchart.data.DataSet;
import com.github.mata1.simpledroidchart.palettes.ColorPalette;

/**
 * Created by matej on 04/04/15.
 */
public class Legend {

    private DataSet mDataSet;
    private ColorPalette mColorPalette;

    public Legend(DataSet dataSet, ColorPalette colorPalette) {
        mDataSet = dataSet;
        mColorPalette = colorPalette;
    }

    public DataSet getDataSet() {
        return mDataSet;
    }

    public ColorPalette getColorPalette() {
        return mColorPalette;
    }
}
