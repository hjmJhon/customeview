package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hjm on 2017/1/4 22:54.
 */

public class ArcView extends View {

    private Paint mPaint;

    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        //addArc()
        testAddArc(canvas);
    }

    private void testAddArc(Canvas canvas) {

        Path path = new Path();
        RectF rectF = new RectF(-200,-200,200,200);
//        path.addArc(rectF,-40,90);
        path.arcTo(rectF,-40,360,true);
        canvas.drawPath(path,mPaint);

        canvas.drawRect(rectF,mPaint);
    }
}
