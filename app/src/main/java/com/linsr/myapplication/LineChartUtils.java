package com.linsr.myapplication;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 @author Linsr
 */

public class LineChartUtils {

    public static void lineChart(LineChart lineChart) {
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
//        lineChart.setTouchEnabled(false);
        lineChart.setDescription(null);
    }

    public static void XAxis(int color,LineChart lineChart) {
        XAxis xAxis = lineChart.getXAxis();
        //是否启用X轴
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        xAxis.setLabelCount(12, true);
        //设置X轴上每个竖线是否显示
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(color);//设置x轴线颜色
        xAxis.setAxisLineWidth(1f);//设置x轴线宽度
    }

    public static void YAxis(int color,LineChart lineChart) {
        //=================设置左边Y轴===============
        YAxis axisLeft = lineChart.getAxisLeft();
        //是否启用左边Y轴
        axisLeft.setEnabled(true);
        axisLeft.setAxisMinimum(0f);
        //设置横向的线为虚线
        axisLeft.enableGridDashedLine(10f, 10f, 0f);
        axisLeft.setGridColor(color);
        axisLeft.setAxisLineColor(color);//设置y轴线颜色
        axisLeft.setAxisLineWidth(1f);//设置y轴线宽度
        //禁用右边Y轴
        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setEnabled(false);
    }

    public static void legend(LineChart lineChart) {
        Legend legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setWordWrapEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
    }

    public static LineDataSet lineDataSet(String label, int color, List<Entry> entries) {
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, label);
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
        return lineDataSet;
    }

//    /**
//     * 初始化曲线 每一个LineDataSet代表一条线
//     *
//     * @param lineDataSet
//     * @param color
//     * @param mode        折线图是否填充
//     */
//    private static void initLineDataSet(LineDataSet lineDataSet, int color, boolean mode) {
//        lineDataSet.setColor(color);
//        lineDataSet.setCircleColor(color);
//        lineDataSet.setLineWidth(0.7f);
//        lineDataSet.setCircleRadius(2f);
//
//        //设置曲线值的圆点是实心还是空心
//        lineDataSet.setDrawCircleHole(true);
//        lineDataSet.setValueTextSize(9f);
//        //设置折线图填充
//        lineDataSet.setDrawFilled(mode);
//        lineDataSet.setFormLineWidth(1f);
//        lineDataSet.setFormSize(15.f);
//        //线模式为圆滑曲线（默认折线）
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//    }

}
