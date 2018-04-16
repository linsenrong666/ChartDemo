package com.linsr.myapplication.chart;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linsr.myapplication.DisplayUtils;
import com.linsr.myapplication.R;

import java.text.DecimalFormat;

/**
 * Description
 @author Linsr
 */

public class HorBarChartItem extends LinearLayout {

    private TextView mLabelName;
    private TextView mBar;
    private RelativeLayout mBarContainer;
    private ImageView mBarBtn;
    private TextView mPercent;
    private TextView mCount;
    private DecimalFormat mFormat;
    private double maxScale;
    private double percent;

    public HorBarChartItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.h_bar_item, this, true);
        mLabelName = findViewById(R.id.h_chart_label_name);
        mBarContainer = findViewById(R.id.h_chart_bar_container);
        mBar = findViewById(R.id.h_chart_bar);
        mBarBtn = findViewById(R.id.h_chart_btn);
        mPercent = findViewById(R.id.h_chart_percent);
        mCount = findViewById(R.id.h_chart_count);
        mFormat = new DecimalFormat("######0%");

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                int barContainerWidth = mBarContainer.getWidth();
                int percentTxtWidth = mPercent.getWidth();
                int countTxtWidth = mCount.getWidth();
                int btnWidth = mBarBtn.getWidth();
                int minWidth = percentTxtWidth + countTxtWidth + btnWidth + DisplayUtils.dp2px(getContext(),20);
                Log.e("===","barContainerWidth:"+barContainerWidth+",percentTxtWidth:"+percentTxtWidth
                +",countTxtWidth:"+countTxtWidth+",btnWidth:"+btnWidth+",minWidth:"+minWidth);
                final int initWidth = barContainerWidth - minWidth;
                final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mBar.getLayoutParams();
                int width = (int) (initWidth * percent / maxScale * 100 / 100);
                if (width < minWidth) {
                    width = minWidth;
                }
                lp.width = width;
                mBar.setLayoutParams(lp);
//                postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final int initWidth = mBar.getWidth();
//                        final ObjectAnimator anim = ObjectAnimator.ofFloat(mBar, "alpha", 0.0F, 1.0F).setDuration(1500);
//                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                            @Override
//                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                                float cVal = (Float) anim.getAnimatedValue();
//                                lp.width = (int) (initWidth * cVal);
//                                mBar.setLayoutParams(lp);
//                            }
//                        });
//                        anim.start();
//                    }
//                }, 0);
                return false;
            }
        });
    }

    public void setBtnClickListener(View.OnClickListener listener) {
        mBarBtn.setOnClickListener(listener);
    }

    public void setMaxScale(double maxScale) {
        this.maxScale = maxScale;
    }

    public void setLabelName(CharSequence c) {
        mLabelName.setText(c);
    }

    public void setCount(CharSequence count) {
        mCount.setText(count);
    }

    public void setPercent(double percent) {
        this.percent = percent;
        mPercent.setText(mFormat.format(percent));
    }

    public HorBarChartItem(Context context) {
        this(context, null, 0);
    }

    public HorBarChartItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
}
