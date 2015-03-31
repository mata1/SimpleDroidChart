package com.github.mata1.simpledroidchart.palettes;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Color palette array
 */
public class ColorPalette extends ArrayList<Integer> {
    private int index = 0;

    /**
     * Get next color from palette
     * @return next color
     */
    public int next() {
        return get(index++ % size());
    }

    /**
     * Get current color from palette
     * @return current color
     */
    public int getCurrent() {
        return get(index % size());
    }

    /**
     * Reset color index to zero
     */
    public void reset() {
        index = 0;
    }

    /**
     * Get color palette from predefined enums
     * @param paletteType color palette enum
     * @return predefined color palette
     */
    public static ColorPalette getPalette(PaletteType paletteType) {
        ColorPalette palette = new ColorPalette();

        for (int i : paletteType.getColors())
            palette.add(i);

        return palette;
    }

    /**
     * Generate random color palette. HSV format
     * http://martin.ankerl.com/2009/12/09/how-to-create-random-colors-programmatically/
     * @param saturation palette saturation
     * @param value palette value
     * @return random color palette
     */
    public static ColorPalette getRandomPalette(float saturation, float value) {
        int nColors = 20;
        ColorPalette palette = new ColorPalette();

        Random r = new Random();
        float goldenRatio = 0.6180339887499895f;
        float hue = r.nextFloat();
        for (int i = 0; i < nColors; i++) {
            hue += goldenRatio;
            hue %= 1;
            palette.add(Color.HSVToColor(new float[]{hue * 360, 0.5f, 0.95f}));
        }

        return palette;
    }
}
