package com.linsr.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.linsr.myapplication.chart.BarDataEntity;
import com.linsr.myapplication.chart.HorBarChartItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 @author Linsr
 */

public class AActivity extends AppCompatActivity {

    private LineChart mLineChart;
    private HorizontalBarChart mHorizontalBarChart;

    private List<String> mXList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        init();
        init2();
        bindData();
    }

    private void init2() {
        mHorizontalBarChart = (HorizontalBarChart) findViewById(R.id.a_bar_chart);
        //设置相关属性
        mHorizontalBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.setDrawValueAboveBar(true);
        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setMaxVisibleValueCount(60);
        mHorizontalBarChart.setPinchZoom(false);
        mHorizontalBarChart.setDrawGridBackground(false);

        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setEnabled(false);

        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setEnabled(false);
        Legend l = mHorizontalBarChart.getLegend();
        l.setEnabled(false);
        //y轴
        YAxis yl = mHorizontalBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);
        //设置数据
        setData(12, 50);
        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);
    }

    //来点随机数吧
    private void setData(int count, float range) {
        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }
        BarDataSet set1;
        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mHorizontalBarChart.setData(data);
        }
    }

    private void init() {
        mLineChart = (LineChart) findViewById(R.id.a_line_chart);
        LineChartUtils.legend(mLineChart);
        LineChartUtils.lineChart(mLineChart);
        LineChartUtils.XAxis(mLineChart);
        LineChartUtils.YAxis(mLineChart);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, (float) (((Math.random())) * i)));
        }
        List<Entry> entries1 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries1.add(new Entry(i, (float) (((Math.random())) * i)));
        }
        LineDataSet a = LineChartUtils.lineDataSet("当日分时数据", Color.RED, entries);
        LineDataSet b = LineChartUtils.lineDataSet("昨日分时数据", Color.BLUE, entries1);
        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(a);
        lineDataSets.add(b);
        LineData data = new LineData(lineDataSets);
        mLineChart.setData(data);
    }

    LinearLayout container;

    public void bindData() {
        container = (LinearLayout) findViewById(R.id.container);
        container.removeAllViews();
        BarDataEntity data = new BarDataEntity();
        data.parseData();
        if (data.getTypeList() == null) {
            return;
        }
        double maxScale = 0;
        for (int i = 0; i < data.getTypeList().size(); i++) {
            if (data.getTypeList().get(i).getTypeScale() > maxScale)
                maxScale = data.getTypeList().get(i).getTypeScale();
        }
        for (int i = 0; i < data.getTypeList().size(); i++) {
            HorBarChartItem item = new HorBarChartItem(this);
            item.setLabelName(data.getTypeList().get(i).getTypeName());
            item.setCount(data.getTypeList().get(i).getSale() + "");
            item.setMaxScale(maxScale);
            item.setPercent(data.getTypeList().get(i).getTypeScale());
            container.addView(item);
        }
    }

}
