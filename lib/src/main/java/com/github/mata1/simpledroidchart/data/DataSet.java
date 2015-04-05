package com.github.mata1.simpledroidchart.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DataSet class for chart data. Extends ArrayList.
 */
public class DataSet extends ArrayList<DataEntry> {

    private float mMin, mMax;
    private boolean mMinSet, mMaxSet;

    /**
     * Get maximum value from data set TODO check if empty
     * @return maximum Y value
     */
    public float getMax() {
        if (mMaxSet || isEmpty())
            return mMax;
        return Collections.max(this).getyValue();
    }

    /**
     * Get minimum value from data set TODO check if empty
     * @return minimum Y value
     */
    public float getMin() {
        if (mMinSet || isEmpty())
            return mMin;
        return Collections.min(this).getyValue();
    }

    /**
     * Get the sum of data set values
     * @return Y value sum
     */
    public float getSum() {
        float sum = 0;

        for (DataEntry entry : this)
            sum += entry.getyValue();

        return sum;
    }

    /**
     * Get major points between data min and max
     * @return major y points between
     */
    public List<Float> getMajorPoints() {
        List<Float> points = new ArrayList<>();

        float diff = Math.abs(getMax() - getMin());
        if (diff == 0)
            diff = getMax();

        int log = (int)Math.ceil(Math.log10(diff));
        float div = (float)Math.pow(10, log - 1);

        float min = (float)Math.ceil(getMin()/div - 1) * div;
        float max = (float)Math.floor(getMax()/div + 1) * div;

        for (float i = Math.min(0, min); i <= max; i += div/2)
            points.add(i);

        return points;
    }

    public void setMin(float min) {
        mMin = min;
        mMinSet = true;
    }

    public void resetMin() {
        mMinSet = false;
    }

    public void setMax(float max) {
        mMax = max;
        mMaxSet = true;
    }

    public void resetMax() {
        mMaxSet = false;
    }
}
