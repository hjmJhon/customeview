package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.stronger.customeview.R;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

/**
 * Created by hjm on 2017/1/3 10:40.
 * 矩阵简单使用
 */

public class MatrixView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Matrix mMatrix;

    public MatrixView(Context context) {
        this(context, null);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);

        //
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.spalsh_new, options);
        //构建一个Matrix
        mMatrix = new Matrix();
        //原始坐标
        float[] src = new float[]{0, 0,//左上
                mBitmap.getWidth(), 0,//右上
                mBitmap.getWidth(), mBitmap.getHeight(),//右下
                0, mBitmap.getHeight()};//左下

        //变换后的坐标
        float[] dst = new float[]{0, 0,//左上
                mBitmap.getWidth(), 100,//右上.右上角y坐标向下100px
                mBitmap.getWidth(), mBitmap.getHeight()-100,//右下.右下角y坐标向上100px
                0, mBitmap.getHeight()};//左下

        mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mapPoints方法:计算一组点基于当前Matrix变换后的位置
        testMapPoints1(canvas);
        //变换图片
        changebitmap(canvas);
    }

    private void changebitmap(Canvas canvas) {

        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    private void testMapPoints1(Canvas canvas) {
        //构造三个原始点
        float[] src = new float[]{0, 0, 80, 100, 200, 200};
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        Log.e(TAG, "before: " + Arrays.toString(src));
        matrix.mapPoints(src);
        Log.e(TAG, "after: " + Arrays.toString(src));
    }
}
