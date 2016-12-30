package com.example.stronger.customeview.bean;

/**
 * Created by hjm on 2016/12/19 21:53.
 * 饼状图的数据：颜色，数值，百分比，角度
 */

public class BingInfo {

    private int color;
    private float value;
    private float percent;
    private float angle;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
