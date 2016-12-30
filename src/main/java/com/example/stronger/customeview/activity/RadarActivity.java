package com.example.stronger.customeview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.stronger.customeview.R;
import com.example.stronger.customeview.widget.RadarView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 自定义view之雷达图
 */
public class RadarActivity extends AppCompatActivity {

    @InjectView(R.id.radarView)
    RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        ButterKnife.inject(this);
        mRadarView.setTextColor(Color.BLACK);
    }
}
