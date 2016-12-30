package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.stronger.customeview.R;

/**
 * Created by hjm on 2016/12/24 19:46.
 * 画布操作2之绘制bitmap和文字
 */

public class CanvasView2 extends View {

    private Paint mPaint;
    private Picture mPicture;
    private Canvas mCanvas;

    public CanvasView2(Context context) {
        this(context, null);
    }

    public CanvasView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个bitmap
        drawBitmap(canvas);
    }

    private void drawBitmap(final Canvas canvas) {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap,0,0,mPaint);
        Rect src = new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight()/2);//要绘制的bitmap的内容区域
        RectF dst = new RectF(100, 100, 180, 180);//要绘制的bitmap的内容区域绘制在屏幕中的位置
        canvas.drawBitmap(bitmap,src,dst,mPaint);//这里的paint可以传null
    }


}
