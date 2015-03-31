package com.github.mata1.simpledroidchart.palettes;

import android.graphics.Color;

/**
 * Different color palettes
 * http://www.colourlovers.com/palettes/most-loved/all-time/meta
 */
public enum PaletteType {
    PASTEL, PRIMARY, BROWNS, GRAYSCALE,
    GIANT_GOLDFISH, ADRIFT_IN_DREAMS, CAKE, OCEAN_FIVE, PROVOKING, MELLON_BALL;

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
            case BROWNS:
                return new int[] {
                        Color.rgb(199, 178, 156), // 1
                        Color.rgb(75, 68, 65), // 2
                        Color.rgb(117, 76, 36), // 3
                        Color.rgb(93, 81 ,75), // 4
                        Color.rgb(140, 98, 57), // 5
                        Color.rgb(115, 99, 87), // 6
                        Color.rgb(166, 124, 82), // 7
                        Color.rgb(153, 134, 117),  // 8
                        Color.rgb(198, 156, 110), // 9
                        Color.rgb(96, 57, 19) // 10

                };
            case GRAYSCALE:
                return new int[] {
                        Color.rgb(172, 172, 172),
                        Color.rgb(112, 112, 112),
                        Color.rgb(54, 54, 54),
                        Color.rgb(161, 161, 161),
                        Color.rgb(99, 99, 99),
                        Color.rgb(149, 149, 149),
                        Color.rgb(85, 85, 85),
                        Color.rgb(137, 137, 137),
                        Color.rgb(70, 70, 70),
                        Color.rgb(125, 125, 125)
                };
            case GIANT_GOLDFISH:
                return new int[] {
                        Color.rgb(105, 210, 231),
                        Color.rgb(167, 219, 216),
                        Color.rgb(224, 228, 204),
                        Color.rgb(243, 134, 48),
                        Color.rgb(250, 105, 0)
                };
            case ADRIFT_IN_DREAMS:
                return new int[] {
                        Color.rgb(207, 240, 158),
                        Color.rgb(168, 219, 168),
                        Color.rgb(121, 189, 154),
                        Color.rgb(59, 134, 134),
                        Color.rgb(11, 72, 107)
                };
            case CAKE:
                return new int[] {
                        Color.rgb(119, 79, 56),
                        Color.rgb(224, 142, 121),
                        Color.rgb(241, 212, 175),
                        Color.rgb(236, 229, 206),
                        Color.rgb(197, 224, 220)
                };
            case OCEAN_FIVE:
                return new int[] {
                        Color.rgb(0, 160, 176),
                        Color.rgb(106, 74, 60),
                        Color.rgb(204, 51, 63),
                        Color.rgb(235, 105, 65),
                        Color.rgb(237, 201, 81)
                };
            case PROVOKING:
                return new int[] {
                        Color.rgb(236, 208, 120),
                        Color.rgb(217, 91, 67),
                        Color.rgb(192, 41, 66),
                        Color.rgb(84, 36, 55),
                        Color.rgb(83, 119, 122)
                };
            case MELLON_BALL:
                return new int[] {
                        Color.rgb(209, 242, 165),
                        Color.rgb(239, 250, 180),
                        Color.rgb(255, 196, 140),
                        Color.rgb(255, 159, 128),
                        Color.rgb(245, 105, 145)
                };
            default:
                return new int[] { Color.BLACK };
        }
    }

}
