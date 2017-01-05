package com.example.stronger.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hjm on 2017/1/3 15:38.
 * 不规则图形的点击事件处理
 * 如果通过测量出不规则图形的矩形边界来判断点击是否在图形区域内是不可靠的,还是得用Region来判断(将图形的path设置到region中)
 */

public class SpecialView extends View {

    /**
     * 小圆的半径
     */
    private int mSmallCircleRadius = 120;
    /**
     * 外面的大圆弧每次旋转的角度
     */
    private int mBigArcSweepAngle = 80;
    /**
     * 里面的小圆弧每次旋转的角度
     */
    private int mSmallArcSweepAngle = -80;
    /**
     * 外面圆弧所在的矩形区域
     */
    private RectF bigRectf;
    /**
     * 里面圆弧所在的矩形区域
     */
    private RectF smallRectf;
    private int mCenterX;
    private int mCenterY;
    private Paint mPaint2;
    private Paint mPaint1;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Region mSmallCircleRegion;
    private Region mRightArcRegion;
    private Region mBottomArcRegion;
    private Region mLeftArcRegion;
    private Region mTopArcRegion;

    public SpecialView(Context context) {
        this(context, null);
    }

    public SpecialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint4 = new Paint();
        mPaint5 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setAntiAlias(true);

        mPaint2.setColor(Color.BLUE);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAntiAlias(true);

        mPaint3.setColor(Color.BLUE);
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setAntiAlias(true);

        mPaint4.setColor(Color.BLUE);
        mPaint4.setStyle(Paint.Style.FILL);
        mPaint4.setAntiAlias(true);

        mPaint5.setColor(Color.BLUE);
        mPaint5.setStyle(Paint.Style.FILL);
        mPaint5.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        mSmallCircleRegion = new Region(0, 0, w, h);
        mRightArcRegion = new Region(0, 0, w, h);
        mBottomArcRegion = new Region(0, 0, w, h);
        mLeftArcRegion = new Region(0, 0, w, h);
        mTopArcRegion = new Region(0, 0, w, h);

        //外边圆弧所在的矩形区域
        bigRectf = new RectF(mCenterX - 350, mCenterY - 350, mCenterX + 350, mCenterY + 350);
        //里面圆弧所在的矩形区域
        smallRectf = new RectF(mCenterX - 200, mCenterY - 200, mCenterX + 200, mCenterY + 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画中间的小圆,并求出小圆的region
        drawSmallCircle(canvas);
        //画右边的圆弧并计算其region
        drawRightArc(canvas);
        //画底部的圆弧并计算其region
        drawBottomArc(canvas);
        //画左边的圆弧
        drawLeftArc(canvas);
        //画顶部的圆弧
        drawTopArc(canvas);
    }

    /**
     * 画中间的小圆并求出小圆的region
     *
     * @param canvas
     */
    private void drawSmallCircle(Canvas canvas) {
        Path smallCirclePath = new Path();
        smallCirclePath.addCircle(mCenterX, mCenterY, mSmallCircleRadius, Path.Direction.CW);
        //计算region
        mSmallCircleRegion.setPath(smallCirclePath, mSmallCircleRegion);
        canvas.drawPath(smallCirclePath, mPaint5);
    }

    /**
     * 画右边的圆弧并计算region
     *
     * @param canvas
     */
    private void drawRightArc(Canvas canvas) {
        Path rightArcPath = new Path();
        //最外面的圆弧
        rightArcPath.addArc(bigRectf, -40, mBigArcSweepAngle);
        //里面的圆弧
        rightArcPath.arcTo(smallRectf, 40, mSmallArcSweepAngle);
        //闭合path
        rightArcPath.close();
        //计算region
        mRightArcRegion.setPath(rightArcPath, mRightArcRegion);
        canvas.drawPath(rightArcPath, mPaint1);
    }

    /**
     * 画底部的圆弧并计算region
     *
     * @param canvas
     */
    private void drawBottomArc(Canvas canvas) {
        Path bottompath = new Path();
        //最外面的圆弧
        bottompath.addArc(bigRectf, 50, mBigArcSweepAngle);
        //里面的圆弧
        bottompath.arcTo(smallRectf, 130, mSmallArcSweepAngle);
        bottompath.close();
        mBottomArcRegion.setPath(bottompath, mBottomArcRegion);
        canvas.drawPath(bottompath, mPaint2);
    }

    /**
     * 画左边的圆弧
     *
     * @param canvas
     */
    private void drawLeftArc(Canvas canvas) {
        Path leftPath = new Path();
        leftPath.addArc(bigRectf, 140, mBigArcSweepAngle);
        leftPath.arcTo(smallRectf, 220, mSmallArcSweepAngle);
        leftPath.close();
        mLeftArcRegion.setPath(leftPath, mLeftArcRegion);
        canvas.drawPath(leftPath, mPaint3);
    }

    /**
     * 画顶部的圆弧并计算region
     *
     * @param canvas
     */
    private void drawTopArc(Canvas canvas) {
        Path topPath = new Path();
        topPath.addArc(bigRectf, 230, mBigArcSweepAngle);
        topPath.arcTo(smallRectf, 310, mSmallArcSweepAngle);
        topPath.close();
        mTopArcRegion.setPath(topPath, mTopArcRegion);
        canvas.drawPath(topPath, mPaint4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mRightArcRegion.contains(x, y)) {
                    Toast.makeText(getContext(), "右边", Toast.LENGTH_SHORT).show();
                    mPaint1.setColor(Color.GRAY);
                    postInvalidate();
                } else if (mBottomArcRegion.contains(x, y)) {
                    mPaint2.setColor(Color.GRAY);
                    postInvalidate();
                    Toast.makeText(getContext(), "底部", Toast.LENGTH_SHORT).show();
                } else if (mLeftArcRegion.contains(x, y)) {
                    mPaint3.setColor(Color.GRAY);
                    postInvalidate();
                    Toast.makeText(getContext(), "左边", Toast.LENGTH_SHORT).show();
                } else if (mTopArcRegion.contains(x, y)) {
                    mPaint4.setColor(Color.GRAY);
                    postInvalidate();
                    Toast.makeText(getContext(), "上边", Toast.LENGTH_SHORT).show();
                } else if (mSmallCircleRegion.contains(x, y)) {
                    mPaint5.setColor(Color.GRAY);
                    postInvalidate();
                    Toast.makeText(getContext(), "中间", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mPaint5.setColor(Color.BLUE);
                mPaint4.setColor(Color.BLUE);
                mPaint3.setColor(Color.BLUE);
                mPaint2.setColor(Color.BLUE);
                mPaint1.setColor(Color.BLUE);
                postInvalidate();
                break;
        }
        return true;
    }
}

