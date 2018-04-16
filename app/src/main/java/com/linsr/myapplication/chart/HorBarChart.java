package com.linsr.myapplication.chart;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linsr.myapplication.R;

/**
 * Description
 @author Linsr
 */

public class HorBarChart extends LinearLayout {

    public HorBarChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public void bindData(final BarDataEntity data) {
        removeAllViews();

        if (data.getTypeList() == null) {
            return;
        }
        double maxScale = 0;
        for (int i = 0; i < data.getTypeList().size(); i++) {
            if (data.getTypeList().get(i).getTypeScale() > maxScale)
                maxScale = data.getTypeList().get(i).getTypeScale();
        }
        for (int i = 0; i < data.getTypeList().size(); i++) {
            final int index = i;
            HorBarChartItem item = new HorBarChartItem(getContext());
            item.setLabelName(data.getTypeList().get(i).getTypeName());
            item.setCount(data.getTypeList().get(i).getSale() + "");
            item.setMaxScale(maxScale);
            item.setPercent(data.getTypeList().get(i).getTypeScale());
            item.setBtnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "index:" + data.getTypeList().get(index).getTypeName(), Toast.LENGTH_LONG).show();
                }
            });
            addView(item);
        }
    }

    public HorBarChart(Context context) {
        this(context, null, 0);
    }

    public HorBarChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

}
