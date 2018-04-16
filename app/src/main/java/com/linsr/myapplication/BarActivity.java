package com.linsr.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.linsr.myapplication.chart.BarDataEntity;
import com.linsr.myapplication.chart.HorBarChart;

/**
 * Description
 @author Linsr
 */

public class BarActivity extends AppCompatActivity {

    private HorBarChart mHorBarChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        mHorBarChart = (HorBarChart) findViewById(R.id.hor_bar);
        findViewById(R.id.bar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarDataEntity data = new BarDataEntity();
                data.parseData();
                mHorBarChart.bindData(data);
            }
        });
    }
}
