package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by hjm on 2017/1/2 14:41.
 * path操作之pathMeasure
 */

public class PathView3 extends View {

    private int mMCenterX;
    private int mMCenterY;
    private Paint mPaint;

    public PathView3(Context context) {
        this(context, null);
    }

    public PathView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMCenterX = w / 2;
        mMCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //截取path中的一部分
        getSegmentPath(canvas);
        //nextContour
        testNextContour(canvas);
    }

    /**
     *
     * @param canvas
     */
    private void testNextContour(Canvas canvas) {
        //在path中添加两条闭合的曲线
        Path path = new Path();
        path.addRect(new RectF(mMCenterX-200,mMCenterY-200,mMCenterX+200,mMCenterY+200), Path.Direction.CW);
        path.addCircle(mMCenterX,mMCenterY,100, Path.Direction.CW);
        //将path于PathMeasure关联
        PathMeasure pathMeasure = new PathMeasure(path, true);
        //分别获取path中两个不同曲线的length
        float length1 = pathMeasure.getLength();
        if (pathMeasure.nextContour()) {
            float length2 = pathMeasure.getLength();
            Log.e(TAG, "length1 :"+length1+"; length2: "+length2);
        }
    }

    /**
     * 截取path中的一部分
     *
     * @param canvas
     */
    private void getSegmentPath(Canvas canvas) {
        Path path = new Path();
        path.addRect(new RectF(mMCenterX - 200, mMCenterY - 200, mMCenterX + 200, mMCenterY + 200), Path.Direction.CW);
        path.close();
        Path dst = new Path();
        PathMeasure pathMeasure = new PathMeasure(path, true);
        //startD 开始点距离起始点的距离  stopD结束点距离起始点的距离   dst将截取的path添加到这里
        pathMeasure.getSegment(0, 400, dst, true);//最后一个参数设置为true,截取出来的path片段会保持原样
//        pathMeasure.getSegment(0, 400, dst, false);//最后一个参数设置为false,截取出来的path片段的起点会移到dst的最后一个点
        canvas.drawPath(dst, mPaint);

    }
}
