package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hjm on 2016/12/15 16:10.
 * 自定义view:画点,线，矩形，圆角矩形，椭圆，圆形等
 */

public class DotView extends View {
    private Paint mPaint;
    public DotView(Context context) {
        super(context);
        init();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.argb(255,255,0,0));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);//设置画笔边框的宽度
//        mPaint.setStyle(Paint.Style.STROKE);//设置画笔仅仅描边
        mPaint.setStyle(Paint.Style.FILL);//设置画笔仅仅填充
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔描边并填充
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个点
        canvas.drawPoint(200,200,mPaint);
        //画三个点
        canvas.drawPoints(new float[]{200,300,200,350,200,400},mPaint);
        //画三条线,形成一个三角形
        canvas.drawLine(300,200,500,500,mPaint);
        canvas.drawLine(300,200,300,500,mPaint);
        canvas.drawLine(300,500,500,500,mPaint);
        //画一个矩形:左上角的点和右下角的点确定一个矩形
        canvas.drawRect(300,550,800,600,mPaint);
        //画一个圆角矩形
        RectF rect = new RectF(300,610,800,650);
        canvas.drawRoundRect(rect,10,10,mPaint);
        //画一个椭圆:
        //方法一:实际上就是控制圆角的半径.当rx为宽的一半,ry为高的一半时正好是一个椭圆.在方法的内部会对rx和ry进行修正,对于大于一半的都做一半处理
        RectF rect2 = new RectF(300,660,800,860);
        canvas.drawRoundRect(rect2,500,200,mPaint);
        //方法二:利用现成的api
        RectF rect3 = new RectF(810,660,1000,960);
        canvas.drawOval(rect3,mPaint);
        //画一个圆
        canvas.drawCircle(300,960,100,mPaint);
        //画一个圆弧
        RectF rect4 = new RectF(0,1000,300,1300);
//        canvas.drawArc(rect4,0,90,false,mPaint);//不使用中心点
        canvas.drawArc(rect4,0,90,true,mPaint);//使用中心点


    }
}
