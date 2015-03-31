package com.github.mata1.simpledroidchart.palettes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Color palette array
 */
public class ColorPalette extends ArrayList<Integer> {
    private int index = 0;

    public int next() {
        return get(index++ % size());
    }

    public void reset() {
        index = 0;
    }

    public static ColorPalette getPalette(PaletteType paletteType) {
        ColorPalette palette = new ColorPalette();

        for (int i : paletteType.getColors())
            palette.add(i);

        return palette;
    }
}
