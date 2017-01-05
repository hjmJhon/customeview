package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by hjm on 2017/1/1 16:09.
 * 贝塞尔曲线
 */

public class BezierView extends View {

    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private PointF mStartPoint;
    private PointF mEndPoint;
    private PointF mControlPoint;
    private PointF mControlPoint2;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        //1.确定两个数据点和控制点的坐标
        mStartPoint = new PointF(mCenterX - 300, mCenterY);
        mEndPoint = new PointF(mCenterX + 300, mCenterY);
        mControlPoint = new PointF(mCenterX-100, mCenterY - 300);
        mControlPoint2 = new PointF(mCenterX+100, mCenterY - 400);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        //绘制二阶贝塞尔曲线
//        drawBezier2(canvas);
        //绘制三阶贝塞尔曲线
        drawBezier3(canvas);

    }

    /**
     * 绘制二阶贝塞尔曲线
     *
     * @param canvas
     */
    private void drawBezier2(Canvas canvas) {
        //2.绘制出控制点和数据点之间的连线
        Path path = new Path();
        //2.1绘制两个数据点和控制点
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(mStartPoint.x, mStartPoint.y, 5, mPaint);
        canvas.drawCircle(mControlPoint.x, mControlPoint.y, 5, mPaint);
        canvas.drawCircle(mEndPoint.x, mEndPoint.y, 5, mPaint);
        //2.2绘制连线
        mPaint.setStrokeWidth(5);
        path.moveTo(mStartPoint.x, mStartPoint.y);
        path.lineTo(mControlPoint.x, mControlPoint.y);
        path.lineTo(mEndPoint.x, mEndPoint.y);
        canvas.drawPath(path, mPaint);
        //2.3绘制曲线
        path.moveTo(mStartPoint.x, mStartPoint.y);
        path.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(path, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControlPoint.x = event.getX();
        mControlPoint.y = event.getY();
        invalidate();
        return true;

    }

    /**
     * 绘制三阶贝塞尔曲线
     * @param canvas
     */
    private void drawBezier3(Canvas canvas) {
        //绘制四个点
        canvas.drawCircle(mStartPoint.x,mStartPoint.y,5,mPaint);
        canvas.drawCircle(mControlPoint.x,mControlPoint.y,5,mPaint);
        canvas.drawCircle(mControlPoint2.x,mControlPoint2.y,5,mPaint);
        canvas.drawCircle(mEndPoint.x,mEndPoint.y,5,mPaint);

        //绘制点之间的连线
        Path path = new Path();
        path.moveTo(mStartPoint.x,mStartPoint.y);
        canvas.drawLine(mStartPoint.x,mStartPoint.y,mControlPoint.x,mControlPoint.y,mPaint);
        canvas.drawLine(mControlPoint.x,mControlPoint.y,mControlPoint2.x,mControlPoint2.y,mPaint);
        canvas.drawLine(mControlPoint2.x,mControlPoint2.y,mEndPoint.x,mEndPoint.y,mPaint);

        //绘制曲线
        path.moveTo(mStartPoint.x,mStartPoint.y);
        path.cubicTo(mControlPoint.x,mControlPoint.y,mControlPoint2.x,mControlPoint2.y,mEndPoint.x,mEndPoint.y);
        canvas.drawPath(path,mPaint);

    }
}
