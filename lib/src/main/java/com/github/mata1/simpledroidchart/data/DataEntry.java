package com.github.mata1.simpledroidchart.data;

/**
 * DataEntry class for chart data points with labels
 */
public class DataEntry implements Comparable {
    private String label = "";
    private float xValue;
    private float yValue;

    public DataEntry(float yValue) {
        this.yValue = yValue;
    }

    public DataEntry(float xValue, float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public DataEntry(String label, float yValue) {
        this.label = label == null ? "" : label;
        this.yValue = yValue;
    }

    @Override
    public int compareTo(Object another) {
        if (another instanceof DataEntry)
            return Float.compare(this.yValue, ((DataEntry)another).yValue);
        return 0;
    }

    /**
     * Get label of data entry
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get X value of data entry
     * @return X value
     */
    public float getxValue() {
        return xValue;
    }

    /**
     * Get Y value of data entry
     * @return Y value
     */
    public float getyValue() {
        return yValue;
    }

    /**
     * Get Y value in String format with relative decimal places
     * @return Y value in String format
     */
    public String getStringValue() {
        int decimals = 0;
        if (Math.abs(yValue) < 10) decimals++;
        if (Math.abs(yValue) < 1) decimals++;
        return String.format("%." + decimals + "f", yValue);
    }
}