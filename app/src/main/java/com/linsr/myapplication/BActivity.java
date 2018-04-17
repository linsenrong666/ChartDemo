package com.linsr.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.linsr.myapplication.linechart.LineChartManager;
import com.linsr.myapplication.linechart.LineChartUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description
 @author Linsr
 */

public class BActivity extends AppCompatActivity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        mLineChart = (LineChart) findViewById(R.id.b_line_chart);
        LineChartManager manager = new LineChartManager(this, mLineChart);
        List<Entry> entries = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, random.nextInt(10)));
        }
        manager.addLine(entries, "昨日数据", Color.GRAY);
        entries.clear();
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, random.nextInt(10)));
        }
        manager.addLine(entries, "今日数据", Color.RED);
        manager.refresh();
    }

}
