package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hjm on 2016/12/20 14:35.
 * 画布操作
 */

public class CanvasView extends View {

    private Paint mPaint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(0xff990000);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //位移画布。注意：位移每次都是基于当前位置的移动，而不是每次都基于屏幕坐标原点的移动。（位移相当于将坐标原点移动了）
//        traslateCanvas(canvas);
        //缩放画布。默认的缩放中心是坐标原点(坐标原点可以通过canvas来改变)
//        scaleCanvas(canvas);
        //旋转画布
//        rotateCanvas(canvas);
        //画布错切
        skewCanvas(canvas);

    }

    /**
     * 画布错切
     * @param canvas
     */
    private void skewCanvas(Canvas canvas) {
        //先将画布移至当前view的中心
        canvas.translate(getWidth()/2,getHeight()/2);
        //以画布的原点位矩形的左上角画矩形
        RectF rectF = new RectF(0,0,200,200);
        canvas.drawRect(rectF,mPaint);
        //在x轴的方向上错切45度
//        canvas.skew(1,0);
//        canvas.drawRect(rectF,mPaint);
        //在y轴方向上错切45度
        canvas.skew(0,-2);
        canvas.drawRect(rectF,mPaint);

    }

    /**
     * 旋转画布
     *
     * @param canvas
     */
    private void rotateCanvas(Canvas canvas) {
        //将坐标原点移动到画布的中心
        canvas.translate(getWidth() / 2,getHeight() / 2);
        //画一个矩形
//        RectF rectF = new RectF(0, -200, 200, 0);
//        canvas.drawRect(rectF,mPaint);
        //将画布旋转45度，默认的旋转中心点是坐标原点
//        canvas.rotate(45);
//        canvas.drawRect(rectF,mPaint);
        //将画布旋转45度，设置旋转的中心点
//        canvas.rotate(45,100,-100);
//        canvas.drawRect(rectF,mPaint);
        //绘制一个类似表盘的圆盘
        drawWatch(canvas);
    }

    private void drawWatch(Canvas canvas) {
        //先绘制两个圆
        canvas.drawCircle(0,0,250,mPaint);
        canvas.drawCircle(0,0,220,mPaint);
        //绘制两个圆之间的连线，就是用旋转画布来实现的
        for (int i = 0; i < 360; i+=20) {
            canvas.drawLine(0,-250,0,-220,mPaint);
            canvas.rotate(20);
        }

    }


    /**
     * 缩放画布
     *
     * @param canvas
     */
    private void scaleCanvas(Canvas canvas) {
        //移动画布到当前view的中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //0.画一个矩形
//        RectF rectF = new RectF(0, -400, 400, 0);
//        canvas.drawRect(rectF,mPaint);
        //1.缩放之后再画一个矩形。默认的缩放中心点是坐标原点
//        canvas.scale(0.5f,0.5f);
//        canvas.drawRect(rectF,mPaint);
        //2.将缩放中心点向右偏移200px
//        canvas.scale(0.5f,0.5f,200,0);
//        canvas.drawRect(rectF,mPaint);
        //3.缩放系数是负数，这时回以缩放中心点进行翻转
//        canvas.scale(0.5f,-0.5f);
//        canvas.drawRect(rectF,mPaint);
        //4.将缩放中心点向右偏移200px,且向上偏移200px
//        canvas.scale(0.5f,-0.5f,200,-200);
//        canvas.drawRect(rectF,mPaint);
        //5.实现一个同心圆效果的矩形
//        RectF rectF1 = new RectF(-400, -400, 400, 400);
//        for (int i = 0; i < 10; i++) {
//            canvas.drawRect(rectF1,mPaint);
//            canvas.scale(0.9f,0.9f);
//        }
        //6.实现一个同心圆效果
//        int radiu = 100;
//        for (int i = 0; i < 5; i++) {
//            canvas.drawCircle(0,0,radiu,mPaint);
//            canvas.scale(0.7f,0.7f);
//        }


    }

    /**
     * 位移画布
     *
     * @param canvas
     */
    private void traslateCanvas(Canvas canvas) {
        //画一个圆形
        canvas.drawCircle(50, 50, 50, mPaint);
        //将画布位移100像素后再继续画一个圆形
        canvas.translate(150, 150);
        canvas.drawCircle(0, 0, 50, mPaint);
    }
}
