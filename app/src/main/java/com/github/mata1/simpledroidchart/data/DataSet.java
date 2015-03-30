package com.github.mata1.simpledroidchart.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * DataSet class for chart data. Extends ArrayList.
 */
public class DataSet extends ArrayList<DataEntry> {

    /**
     * Get maximum value from data set
     * @return maximum Y value
     */
    public float getMax() {
        return Collections.max(this).getyValue();
    }

    /**
     * Get minimum value from data set
     * @return minimum Y value
     */
    public float getMin() {
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
}
