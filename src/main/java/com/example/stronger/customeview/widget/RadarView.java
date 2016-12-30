package com.example.stronger.customeview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.stronger.customeview.R;

import static android.content.ContentValues.TAG;

/**
 * 自定义雷达图
 */
public class RadarView extends View {

    private Paint mMainpaint;
    private Paint mLinePaint;
    private Paint mtextPaint;
    private int count = 6;//定义是正六边形
    private float angle = (float) (Math.PI * 2 / count);//角度
    /**
     * 各个维度的数值
     */
    private int[] values = new int[]{30, 75, 55, 65, 55, 95};
    /**
     * 文字
     */
    private String[] text = new String[]{"小明", "小黄", "小芳", "小博", "小圆", "小小"};
    /**
     * 正六边形的最大半径
     */
    private float mRadius;

    /**
     * 正六边形的中心点的坐标
     */
    private int mcenterX, mcenterY;
    /**
     * 平均半径
     */
    private float mAverageRadius;
    private int mTextColor;
    private int mTextSize;


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        mTextColor = typedArray.getColor(R.styleable.RadarView_textColor, Color.BLUE);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.RadarView_textSize, dp2px(16));
        typedArray.recycle();
        init();
    }

    private void init() {
        //正六边形的画笔
        mMainpaint = new Paint();
        mMainpaint.setAntiAlias(true);
        mMainpaint.setStyle(Paint.Style.STROKE);
        mMainpaint.setColor(Color.BLUE);

        //对角连线的画笔
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.BLACK);

        //文字的画笔
        mtextPaint = new Paint();
        mtextPaint.setColor(mTextColor);
        mtextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultWidth = dp2px(400);
        int defaultheight = dp2px(400);
        int widMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, defaultheight);
        } else if (widMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode != MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightMeasureSize);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST && widMeasureMode != MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSize, defaultheight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = Math.min(h, w) / 2 * 0.7f;
        mcenterX = w / 2;
        mcenterY = h / 2;
        mAverageRadius = mRadius / (count - 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画正六边形
        drawHexagon(canvas);
        //画对应顶点之间的连线
        drawLine(canvas);
        //画各个维度值的连线区域
        drawValues(canvas);
        //画文字
        drawText(canvas);
    }

    private void drawHexagon(Canvas canvas) {
        //内外一共5个正六边形
        Path path = new Path();
        for (int i = 1; i < count; i++) {
            //当前半径
            float currentRadius = mAverageRadius * i;
            //计算正六边形的每个顶点的坐标
            for (int j = 0; j < count; j++) {
                if (j == 0) {//第一个点的坐标
                    float x = mcenterX + currentRadius;
                    float y = mcenterY;
                    path.moveTo(x, y);
                } else {//其他点的坐标
                    float x = (float) (mcenterX + currentRadius * Math.cos(angle * j));
                    float y = (float) (mcenterY + currentRadius * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            //闭合path
            path.close();
            canvas.drawPath(path, mMainpaint);
        }
    }

    /**
     * 画对应顶点之间的连线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.moveTo(mcenterX, mcenterY);
            float x = (float) (mcenterX + mRadius * Math.cos(angle * i));
            float y = (float) (mcenterY + mRadius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mLinePaint);
        }
    }

    /**
     * 画各个维度值的连线的区域
     *
     * @param canvas
     */
    private void drawValues(Canvas canvas) {
        Path path = new Path();
        mLinePaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < values.length; i++) {
            double valuePersent = (values[i] * 1.0f) / 100;
            float valueRadius = (float) (valuePersent * mRadius);
            float valueX, valueY;
            if (i == 0) {
                valueX = mcenterX + valueRadius;
                valueY = mcenterY;
                path.moveTo(valueX, valueY);
            } else {
                valueX = (float) (mcenterX + valueRadius * Math.cos(angle * i));
                valueY = (float) (mcenterY + valueRadius * Math.sin(angle * i));
                path.lineTo(valueX, valueY);
            }
            //画小圆圈
            mLinePaint.setAlpha(200);
            canvas.drawCircle(valueX, valueY, 5, mLinePaint);
        }
        path.close();
        mLinePaint.setAlpha(100);
        canvas.drawPath(path, mLinePaint);
    }

    /**
     * 绘制文字
     * 在计算角度时还是不用Math.PI了，直接用360除以某个数吧，在这个方法中计算角度时真是蛋疼，尤其时等于180度和360度时用PI很不准确
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            float currentAngle = angle * i;
            float x = (float) (mcenterX + mRadius * Math.cos(currentAngle));
            float y = (float) (mcenterY + mRadius * Math.sin(currentAngle));
            String currentText = text[i];
            //获取文字的宽高
            float textWidth = getTextWidth(currentText);
            float textHeight = getTextHeight();
            if (currentAngle == 0) {    //0
                canvas.drawText(text[i], x + textWidth / 5, y + textHeight * 2 / 5, mtextPaint);
            } else if (currentAngle > 0 && currentAngle < Math.PI / 2) {   //0-90
                canvas.drawText(currentText, x - textWidth / 2, y + textHeight, mtextPaint);
            } else if (currentAngle > Math.PI / 2 && currentAngle < Math.PI) {   //90-180
                canvas.drawText(currentText, x - textWidth / 2, y + textHeight, mtextPaint);
            } else if (currentAngle >= Math.PI && currentAngle <= Math.PI + Math.PI / 10) {      //180
                Log.e(TAG, "drawText: " + currentText);
                canvas.drawText(currentText, x - textWidth * 4 / 3, y + textHeight * 2 / 5, mtextPaint);
            } else if (currentAngle > (Math.PI + Math.PI / 10) && currentAngle < (Math.PI + Math.PI / 2)) {  //180-270
                Log.e(TAG, "currentAngle: " + currentAngle);
                canvas.drawText(currentText, x - textWidth / 2, y - textHeight / 2, mtextPaint);
            } else if (currentAngle > (Math.PI + Math.PI / 2) && currentAngle < Math.PI * 2) {  //270-360
                canvas.drawText(currentText, x - textWidth / 2, y - textHeight / 2, mtextPaint);
            }
        }
    }

    /**
     * 获取绘制的文字的高度
     */
    private float getTextHeight() {
        Paint.FontMetrics fontMetrics = mtextPaint.getFontMetrics();
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        return Math.abs(ascent) + Math.abs(descent);
    }

    /**
     * 获取绘制的文字的宽度
     */
    private float getTextWidth(String text) {
        return mtextPaint.measureText(text);
    }

    /**
     * 设置文字的颜色.默认时蓝色
     *
     * @param color
     */
    public void setTextColor(int color) {
        mtextPaint.setColor(color);
        invalidate();
    }

    /**
     * 设置文字的大小.默认时16dp
     *
     * @param size
     */
    public void setTextSize(int size) {
        mtextPaint.setTextSize(size);
        invalidate();
    }

    /**
     * 设置显示的文字集合
     * @param texts
     */
    public void setText(String[] texts){
        this.text= texts;
        invalidate();
    }

    public int dp2px(int dpValue) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }


}