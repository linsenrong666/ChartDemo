package com.linsr.myapplication;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.github.mikephil.charting.charts.LineChart;

/**
 * Description
 @author Linsr
 */

public class MyLineChart extends LineChart {

    public MyLineChart(Context context) {
        this(context, null, 0);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Matrix m = new Matrix();
        m.postScale(2f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        getViewPortHandler().refresh(m, this, false);//将图表动画显示之前进行缩放
    }

    private int mLastX = 0;
    private int mLastY = 0;
    private float mLow = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //不允许父容器拦截该事件
                //parent为该View需要拦截滑动事件的那个父容器的引用
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //这里的逻辑可以根据需要进行修改
                //parent为该View需要拦截滑动事件的那个父容器的引用
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                Log.w("==", "high:" + getHighestVisibleX() + ",low:" + getLowestVisibleX() + ",max:" + mXAxis.getAxisMaximum());
                Log.w("==", "-------------------");
                //防止是按下也判断
                if (Math.abs(deltaX) > 8) {
                    int orientation = getOrientation(deltaX, deltaY);
                    switch (orientation) {
                        case 'r':
                            int scrollX = ((HorizontalScrollView) getParent().getParent()).getScrollX();
                            Log.i("==", "right,allow,low:" + getLowestVisibleX() + ",high:" + getHighestVisibleX() + ",scrollX:" + scrollX);
                            if (isEquals(getHighestVisibleX(), mXAxis.getAxisMaximum())
                                    && isEquals(getLowestVisibleX(), mLow)) {
                                getParent().requestDisallowInterceptTouchEvent(false);
                            }
                            if (scrollX == 0) {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            break;
                        case 'l':
                            if (isEquals(getHighestVisibleX(), mXAxis.getAxisMaximum())) {
                                mLow = getLowestVisibleX();
                                Log.e("==", "left,allow,low:" + getLowestVisibleX() + ",high:" + getHighestVisibleX());
                                getParent().requestDisallowInterceptTouchEvent(false); //允许父容器拦截该事件
                            }
                            break;
                    }
                }

                Log.d("==", "mLow:" + mLow);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }

    private boolean isEquals(float a, float b) {
        return Math.abs(a - b) <= 1;
    }

    /**
     * 根据距离差判断 滑动方向
     * @param dx X轴的距离差
     * @param dy Y轴的距离差
     * @return 滑动的方向
     */
    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            //X轴移动
            return dx > 0 ? 'r' : 'l';
        } else {
            //Y轴移动
            return dy > 0 ? 'b' : 't';
        }
    }

}
