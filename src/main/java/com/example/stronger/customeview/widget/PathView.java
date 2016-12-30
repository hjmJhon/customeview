package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by hjm on 2016/12/25 18:31.
 * 自定义view之path
 */

public class PathView extends View {

    private Paint mPaint;

    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        //1.lineTo,moveTo,setLastPoint,close的操作
//        test1(canvas);
        //2.在path中添加图形
//        drawRec(canvas);
        //3.path中添加path（path合并）
//        addPath(canvas);
        //4.path中添加圆弧
//        addArc(canvas);
        //5.path的其他操作
//        otherOperation(canvas);
        //6.path中的其他操作2
        otherOperation2(canvas);


    }

    private void otherOperation2(Canvas canvas) {
        Path path = new Path();
        path.rLineTo(100,100);
        canvas.drawPath(path,mPaint);
    }

    /**
     * path的其他操作
     * @param canvas
     */
    private void otherOperation(Canvas canvas) {
        //1.判断path中是否包含内容
        Path emptyPath = new Path();
        Log.e(TAG, "path is empty: "+emptyPath.isEmpty());//true
        Path notEmptyPath = new Path();
        notEmptyPath.lineTo(100,100);
        Log.e(TAG, "notEmptyPath is empty  : "+notEmptyPath.isEmpty());//false
        //2.判断path是否是一个矩形，如果死矩形，会将矩形的信息存放在rect中
        Path rectPath = new Path();
        RectF rect = new RectF(-200, -200, 200, 200);
        rectPath.addRect(rect, Path.Direction.CW);
        Log.e(TAG, "rectPath is rect : "+rectPath.isRect(rect));
        //3.将新path覆盖ath
//        Path oldPath = new Path();
//        oldPath.addRect(rect, Path.Direction.CW);//矩形
//        Path newPath = new Path();
//        newPath.addCircle(0,0,100, Path.Direction.CW);//圆
//        oldPath.set(newPath);//用newPath 覆盖oldpath。相当于oldpath=newpath
//        canvas.drawPath(oldPath,mPaint);//画出来的是一个圆
        //4.offset
        Path offsetPath = new Path();
        offsetPath.addRect(-200,-200,200,200, Path.Direction.CW);
        Path dst = new Path();
//        dst.addCircle(0,0,100, Path.Direction.CW);
//        offsetPath.offset(200,200);//平移offsetPath
        //dst存的是平移后的path，这个参数可以不用。
        offsetPath.offset(200,0,dst);
        canvas.drawPath(offsetPath,mPaint);
        //把dst画出来
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(dst,mPaint);
    }

    /**
     * path中添加圆弧
     * @param canvas
     */
    private void addArc(Canvas canvas) {
        Path path = new Path();
        path.lineTo(-200,200);
        path.moveTo(0,0);//将下次操作的起始点移动至坐标原点
        path.lineTo(200,200);
//        path.addArc(new RectF(-200,-200,200,200),-90,270);//直接将一个圆弧添加进path中，不做其他处理
        path.arcTo(new RectF(-200,-200,200,200),-90,270,true);//作用同上

        path.arcTo(new RectF(-200,-200,200,200),-90,270);//如果圆弧的起始点和上次操作的最后一个点不同，会连接这两个点
        path.arcTo(new RectF(-200,-200,200,200),-90,270,false);//作用同上

        canvas.drawPath(path,mPaint);
    }

    /**
     * 3.合并path
     * @param canvas
     */
    private void addPath(Canvas canvas) {
        Path path = new Path();
        //将一个矩形添加进path中
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        //将一个圆添加进path中
        Path circle = new Path();
        circle.addCircle(0,0,150, Path.Direction.CW);
        //将一个小圆添加进circle中
        Path smallCircle = new Path();
        smallCircle.addCircle(0,0,100, Path.Direction.CW);
        circle.addPath(smallCircle);
        //将cirlce添加进path中
//        path.addPath(circle);
        path.addPath(circle,0,-300);//将circle向上平移300px再合并path
        canvas.drawPath(path,mPaint);
    }

    /**
     * 2.在path中添加矩形
     * @param canvas
     */
    private void drawRec(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(-200, -200, 200, 200);
        path.addRect(rectF, Path.Direction.CW);
//        path.setLastPoint(-300,300);//重置最后一个点的坐标后就不是矩形了
        canvas.drawPath(path,mPaint);
    }

    /**
     * //1.lineTo,moveTo,setLastPoint,close的操作
     * @param canvas
     */
    private void test1(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200,200);
//        path.moveTo(300,300);//移动下一个操作的起始点位置
        path.lineTo(200,0);
        path.setLastPoint(450,200);//重置当前path中最后一个点的位置
        path.close();
        canvas.drawPath(path,mPaint);
    }
}
