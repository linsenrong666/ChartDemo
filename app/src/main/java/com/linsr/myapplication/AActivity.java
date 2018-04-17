package com.linsr.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

    private MyLineChart mLineChart;
    private LineChart mLineChart2;
    private HorizontalBarChart mHorizontalBarChart;

    private List<Entry> entries;
    private List<Entry> entries1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        entries = new ArrayList<>();
        entries1 = new ArrayList<>();
        init();
        init2();
        init3();
        findViewById(R.id.aa_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AActivity.this, BarActivity.class));
            }
        });
        findViewById(R.id.aa_line_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("==", "==");
                entries.clear();
                entries1.clear();
                //设置数据
                for (int i = 0; i < 100; i++) {
                    entries.add(new Entry(i, (float) (((Math.random())) * i)));
                }
                for (int i = 0; i < 100; i++) {
                    entries1.add(new Entry(i, (float) (((Math.random())) * i)));
                }
                //通知数据已经改变
                mLineData.notifyDataChanged();
                mLineChart.notifyDataSetChanged();
                mLineChart.invalidate();
            }
        });
    }

    private void init3() {
        mLineChart2 = (LineChart) findViewById(R.id.a_line_chart_2);
        Matrix m = new Matrix();
        m.postScale(2f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mLineChart2.getViewPortHandler().refresh(m, mLineChart2, false);//将图表动画显示之前进行缩放
        LineChartUtils.legend(mLineChart2);
        LineChartUtils.lineChart(mLineChart2);
        LineChartUtils.XAxis(getResources().getColor(R.color.line), mLineChart2);
        LineChartUtils.YAxis(getResources().getColor(R.color.line), mLineChart2);
        LineDataSet a = LineChartUtils.lineDataSet("当日分时数据", getResources().getColor(R.color.main_red), entries);
        LineDataSet b = LineChartUtils.lineDataSet("昨日分时数据", Color.GRAY, entries1);
        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(a);
        lineDataSets.add(b);

        LineData  data = new LineData(lineDataSets);
        mLineChart2.setData(data);
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
        mLineChart = (MyLineChart) findViewById(R.id.a_line_chart);
        LineChartUtils.legend(mLineChart);
        LineChartUtils.lineChart(mLineChart);
        LineChartUtils.XAxis(getResources().getColor(R.color.line), mLineChart);
        LineChartUtils.YAxis(getResources().getColor(R.color.line), mLineChart);
        //设置数据
        for (int i = 0; i < 100; i++) {
            entries.add(new Entry(i + 1, (float) (((Math.random()) * 10))));
        }
        for (int i = 0; i < 100; i++) {
            entries1.add(new Entry(i + 1, (float) (((Math.random() * 10)))));
        }
        LineDataSet a = LineChartUtils.lineDataSet("当日分时数据", getResources().getColor(R.color.main_red), entries);
        LineDataSet b = LineChartUtils.lineDataSet("昨日分时数据", Color.GRAY, entries1);
        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(a);
        lineDataSets.add(b);

        mLineData = new LineData(lineDataSets);
        mLineChart.setData(mLineData);

        ViewGroup.LayoutParams para = mLineChart.getLayoutParams();
        para.width = DisplayUtils.getScreenWidthPixels(this);//修改宽度
        mLineChart.setLayoutParams(para);
    }

    private LineData mLineData;
}
