package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hjm on 2016/12/25 12:00.
 * 绘制文字
 */

public class CanvasView3 extends View {

    String text = "ABCD";
    private Paint textPaint;
    public CanvasView3(Context context) {
        this(context,null);
    }

    public CanvasView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.BLUE);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(50);//设置绘制的文字的大小

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //文字绘制的基线是文字的左下角，所以必须指定文字绘制的位置向下偏移一定量(根据文字的大小而定)才能看出绘制出来的文字
        //1.绘制全部文字
//        canvas.drawText(text,0,50,textPaint);
//        //2.绘制字符串中的指定文字
//        canvas.drawText(text,1,3,200,100,textPaint);
//        //3.绘制字符数组
//        canvas.drawText(text.toCharArray(),0,3,200,150,textPaint);
//        //4.指定每个字符绘制的位置
//        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
//        canvas.drawPosText(text,new float[]{200,200,250,250,300,350,400,450},textPaint);
        //5.先画一个圆，再把文字绘制的圆形的周围
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(0,0,300,textPaint);
        //再将文字绘制在圆形的周围
        char[] chars = text.toCharArray();
        textPaint.setTextAlign(Paint.Align.CENTER);//设置绘制的文字居中对齐。默认左对齐
        for (int i = 0; i < text.length(); i++) {
            canvas.drawText(String.valueOf(chars[i]),0,-250,textPaint);
            int startAngle = 360/text.length();
            canvas.rotate(startAngle);
        }
    }
}
