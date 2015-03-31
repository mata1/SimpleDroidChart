package com.github.mata1.simpledroidchart.palettes;

import android.graphics.Color;

/**
 * Created by matej on 31/03/15.
 */
public enum PaletteType {
    PASTEL, PRIMARY;

    public int[] getColors() {
        switch (this) {
            case PASTEL:
                return new int[] {
                        Color.rgb(246, 150, 121), // 1
                        Color.rgb(163, 211, 156), // 2
                        Color.rgb(131, 147, 202), // 3
                        Color.rgb(245, 152, 157), // 4
                        Color.rgb(196, 223, 155), // 5
                        Color.rgb(125, 167, 217), // 6
                        Color.rgb(244, 154, 193), // 7
                        Color.rgb(255, 247, 153), // 8
                        Color.rgb(109, 207, 246), // 9
                        Color.rgb(189, 140, 191), // 10
                        Color.rgb(253, 198, 137), // 11
                        Color.rgb(122, 204, 200), // 12
                        Color.rgb(161, 134, 190), // 13
                        Color.rgb(249, 173, 129), // 14
                        Color.rgb(130, 202, 156), // 15
                        Color.rgb(135, 129, 189), // 16
                };
            case PRIMARY:
                return new int[] {
                        Color.RED,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.CYAN,
                        Color.BLUE,
                        Color.MAGENTA
                };
            default:
                return new int[] { Color.BLACK };
        }
    }

}
