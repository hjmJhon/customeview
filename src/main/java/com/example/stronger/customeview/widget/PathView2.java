package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by hjm on 2017/1/2 10:28.
 * Path 操作
 */

public class PathView2 extends View {

    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private RectF mRectF;

    public PathView2(Context context) {
        this(context, null);
    }

    public PathView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //rLineTo
//        testRLineTo(canvas);
        //path 填充模式之奇偶规则
//        testFillType1(canvas);
        //path之 op操作(集合操作)
//        testOp(canvas);
        //计算边界
        testBound(canvas);

    }

    /**
     * 计算边界
     *
     * @param canvas
     */
    private void testBound(Canvas canvas) {
        //先画一个圆
        Path path = new Path();
        path.addCircle(mCenterX, mCenterY, 100, Path.Direction.CW);
        //再画一个三角形
        path.moveTo(mCenterX + 100, mCenterY);
        path.rLineTo(100, 100);
        path.rLineTo(0, -200);
        path.close();
        //计算上面画的两个图形的边界
        mRectF = new RectF();
        path.computeBounds(mRectF, true);
        //画出边界矩形 把矩形边界的顶部,底部,和右边都加上10px
        mRectF.right = mRectF.right + 10;
        mRectF.top = mRectF.top - 10;
        mRectF.bottom = mRectF.bottom + 10;
        canvas.drawRect(mRectF, mPaint);
        //画出path
        canvas.drawPath(path, mPaint);
    }

    /**
     * path 之集合操作
     *
     * @param canvas
     */
    private void testOp(Canvas canvas) {
        //绘制两个不同的圆
        Path pathCircle1 = new Path();
        pathCircle1.addCircle(mCenterX - 200, mCenterY, 200, Path.Direction.CW);
        Path pathCircle2 = new Path();
        pathCircle2.addCircle(mCenterX, mCenterY, 200, Path.Direction.CW);
        canvas.drawPath(pathCircle1, mPaint);
        canvas.drawPath(pathCircle2, mPaint);

        //绘制出经过 op 操作之后的path
        Path opResult = new Path();
        opResult.op(pathCircle1, pathCircle2, Path.Op.DIFFERENCE);//path1减去path2
//        opResult.op(pathCircle1,pathCircle2, Path.Op.REVERSE_DIFFERENCE);//path2减去path1
//        opResult.op(pathCircle1,pathCircle2, Path.Op.INTERSECT);//两个path的重合部分
//        opResult.op(pathCircle1,pathCircle2, Path.Op.UNION);//合并两个path
//        opResult.op(pathCircle1,pathCircle2, Path.Op.XOR);//两个path中不重合的部分
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(opResult, mPaint);


    }

    /**
     * path 填充模式之奇偶规则
     *
     * @param canvas
     */
    private void testFillType1(Canvas canvas) {
        //设置 canvas 为填充模式
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        RectF rectF = new RectF(mCenterX - 200, mCenterY - 200, mCenterX + 200, mCenterY + 200);
        path.addRect(rectF, Path.Direction.CW);
//        path.setFillType(Path.FillType.EVEN_ODD);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(path, mPaint);

    }

    private void testRLineTo(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mCenterX, mCenterY);
        path.lineTo(mCenterX + 100, mCenterY + 100);
        path.rLineTo(100, 0);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (mRectF.contains(x, y)) {
            Log.e(TAG, "onTouchEvent:  在矩形区域内");
        } else {
            Log.e(TAG, "onTouchEvent:  不在矩形区域内");
        }
        return super.onTouchEvent(event);
    }
}
