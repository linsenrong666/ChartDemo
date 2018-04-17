package com.linsr.myapplication.linechart;

import android.content.Context;
import android.graphics.Matrix;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.linsr.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 @author Linsr
 */

public class LineChartManager {

    private LineChart mLineChart;
    private Legend mLegend;
    private YAxis mLeftYAxis;
    private YAxis mRightYAxis;
    private XAxis mXAxis;
    private Context mContext;
    private List<ILineDataSet> mILineDataSets;

    public LineChartManager(Context context, LineChart lineChart) {
        mContext = context;
        mLineChart = lineChart;
        mLeftYAxis = lineChart.getAxisLeft();
        mRightYAxis = lineChart.getAxisRight();
        mXAxis = lineChart.getXAxis();
        mLegend = lineChart.getLegend();
        mILineDataSets = new ArrayList<>();

        initLineChart();
        legend();
        enableScroll(2f, 1f);
        XAxis(mContext.getResources().getColor(R.color.line));
        YAxis(mContext.getResources().getColor(R.color.line));
    }

    private void initLineChart() {
        mLineChart.setScaleEnabled(false);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDescription(null);
        mLineChart.setDragEnabled(true);
    }

    private void enableScroll(float xScale, float yScale) {
        Matrix m = new Matrix();
        m.postScale(xScale, yScale);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mLineChart.getViewPortHandler().refresh(m, mLineChart, false);//将图表动画显示之前进行缩放
    }

    private void XAxis(int color) {
        //是否启用X轴
        mXAxis.setEnabled(true);
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
//        mXAxis.setLabelCount(12, true);
        //设置X轴上每个竖线是否显示
        mXAxis.setDrawGridLines(false);
        mXAxis.setAxisLineColor(color);//设置x轴线颜色
        mXAxis.setAxisLineWidth(1f);//设置x轴线宽度
        mXAxis.setAvoidFirstLastClipping(true);
        mXAxis.setTextSize(10f);
    }

    private void YAxis(int color) {
        //=================设置左边Y轴===============
        //是否启用左边Y轴
        mLeftYAxis.setEnabled(true);
        mLeftYAxis.setAxisMinimum(0f);
        //设置横向的线为虚线
        mLeftYAxis.enableGridDashedLine(10f, 10f, 0f);
        mLeftYAxis.setGridColor(color);
        mLeftYAxis.setAxisLineColor(color);//设置y轴线颜色
        mLeftYAxis.setAxisLineWidth(1f);//设置y轴线宽度
        //禁用右边Y轴
        mRightYAxis.setEnabled(false);
    }

    private void legend() {
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        mLegend.setWordWrapEnabled(true);
        mLegend.setForm(Legend.LegendForm.CIRCLE);
    }

    public void addLine(List<Entry> entries, String label, int color) {
        List<Entry> data = new ArrayList<>();
        data.addAll(entries);
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(data, label);
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(0.7f);
        lineDataSet.setCircleRadius(2f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setValueTextSize(9f);
        //设置折线图填充
        lineDataSet.setDrawFilled(false);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry,
                                            int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "";
            }
        });
        mILineDataSets.add(lineDataSet);
    }

    public void refresh() {
        mLineChart.clear();
        LineData data = new LineData(mILineDataSets);
        mLineChart.setData(data);
    }


}
