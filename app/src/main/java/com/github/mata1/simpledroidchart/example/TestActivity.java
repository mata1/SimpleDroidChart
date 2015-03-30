package com.github.mata1.simpledroidchart.example;

import android.app.Activity;
import android.os.Bundle;

import com.github.mata1.simpledroidchart.BarChartView;
import com.github.mata1.simpledroidchart.LineChartView;
import com.github.mata1.simpledroidchart.PieChartView;
import com.github.mata1.simpledroidchart.R;
import com.github.mata1.simpledroidchart.data.DataEntry;
import com.github.mata1.simpledroidchart.data.DataSet;

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

        LineChartView line = (LineChartView)findViewById(R.id.line);
        //line.setShowVerticalGrid(true);
        //line.setShowHorizontalGrid(true);
        line.setData(data);

        PieChartView pie = (PieChartView)findViewById(R.id.pie);
        pie.setData(data);

        BarChartView bar = (BarChartView)findViewById(R.id.bar);
        bar.setData(data);
    }
}
