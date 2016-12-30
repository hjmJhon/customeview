package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.stronger.customeview.bean.BingInfo;

import java.util.ArrayList;

/**
 * Created by hjm on 2016/12/18 15:12.
 * 自定义饼状图
 */

public class BingView extends View {
    private Paint mPaint;
    //宽
    private int mWidth;
    //高
    private int mHeight;
    private ArrayList<BingInfo> mData;
    private float mStartAngle = 0;
    private int mRadiu;
    private RectF mRectF;

    public BingView(Context context) {
        this(context, null);
    }

    public BingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        //1.半径
        mRadiu = Math.min(mHeight, mWidth) / 2;
        //2.设置绘制的区域
        mRectF = new RectF(-mRadiu, -mRadiu, mRadiu, mRadiu);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //3.将画布的坐标远点移至当前view的中心
        canvas.translate(mRadiu, mRadiu);
        //4.当前的角度
        float currentAngle = mStartAngle;
        //5.绘制弧形
        for (int i = 0; i < mData.size(); i++) {
            BingInfo bingInfo = mData.get(i);
            mPaint.setColor(bingInfo.getColor());
            //计算每个value所占的角度
            float value = bingInfo.getValue();
            float persent = value / 100;//所有value之和是100
            bingInfo.setAngle(persent * 360);
            canvas.drawArc(mRectF, currentAngle, bingInfo.getAngle(), true, mPaint);
            currentAngle += bingInfo.getAngle();
        }
    }

    //给外界提供设置数据的方法
    public void setData(ArrayList<BingInfo> data) {
        mData = data;
        invalidate();
    }


}
