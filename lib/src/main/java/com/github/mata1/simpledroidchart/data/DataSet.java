package com.github.mata1.simpledroidchart.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * Get major points between data min and max
     * @return major y points between
     */
    public List<Float> getMajorPoints() {
        List<Float> points = new ArrayList<>();

        float diff = getMax() - getMin();
        float div = 10000000; // TODO optimize

        while (div > diff)
            div /= 10;

        for (float i = 0/*div*/; i <= getMax() * 10; i += div/2)
            points.add(i);

        return points;
    }
}
