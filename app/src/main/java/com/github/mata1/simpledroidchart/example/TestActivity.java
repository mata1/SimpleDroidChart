package com.github.mata1.simpledroidchart.example;

import android.app.Activity;
import android.os.Bundle;

import com.github.mata1.simpledroidchart.BarChartView;
import com.github.mata1.simpledroidchart.LineChartView;
import com.github.mata1.simpledroidchart.PieChartView;
import com.github.mata1.simpledroidchart.R;
import com.github.mata1.simpledroidchart.data.DataEntry;
import com.github.mata1.simpledroidchart.data.DataSet;
import com.github.mata1.simpledroidchart.palettes.ColorPalette;
import com.github.mata1.simpledroidchart.palettes.PaletteType;

public class TestActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DataSet data = new DataSet();
        data.add(new DataEntry("Ljubljana", 30.0F));
        data.add(new DataEntry("Ravne", 13.0F));
        data.add(new DataEntry("Maribor", 20.0F));
        data.add(new DataEntry("Celje", 9.0F));
        data.add(new DataEntry("Koper", 26.0F));

        // horizontal grid test
        /*data.clear();
        data.add(new DataEntry("Ljubljana", 0.3f));
        data.add(new DataEntry("Ravne", 0.13f));
        data.add(new DataEntry("Maribor", 0.2f));
        data.add(new DataEntry("Celje", 0.09f));
        data.add(new DataEntry("Koper", 0.26f));
        data.add(new DataEntry("Dravograd", 0.5f));
        data.add(new DataEntry("Dravograd", 0.1f));
        data.add(new DataEntry("Dravograd", 0.2f));
        data.add(new DataEntry("Dravograd", 0.3f));
        data.add(new DataEntry("Dravograd", 0.2f));*/

        LineChartView line = (LineChartView)findViewById(R.id.line);
        line.setColorPalette(ColorPalette.getPalette(PaletteType.CAKE));
        line.setData(data);

        PieChartView pie = (PieChartView)findViewById(R.id.pie);
        pie.setColorPalette(ColorPalette.getRandomPalette(0.5f, 0.95f));
        pie.setData(data);

        BarChartView bar = (BarChartView)findViewById(R.id.bar);
        bar.setColorPalette(ColorPalette.getPalette(PaletteType.GRAYSCALE));
        bar.setData(data);
    }
}
