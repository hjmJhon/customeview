package com.example.stronger.customeview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.stronger.customeview.R;
import com.example.stronger.customeview.bean.BingInfo;
import com.example.stronger.customeview.widget.BingView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by hjm on 2016/12/18 15:01.
 * 自定义饼状图
 */

public class BingActivity extends Activity {
    @InjectView(R.id.bingView)
    BingView mBingView;
    private ArrayList<BingInfo> mBingInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing);
        ButterKnife.inject(this);

        initData();
        mBingView.setData(mBingInfos);
    }

    private void initData() {
        mBingInfos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BingInfo bingInfo = new BingInfo();
            if (i == 0) {
                bingInfo.setColor(0xffff0000);
                bingInfo.setValue(20);
            } else if (i == 1) {
                bingInfo.setColor(0xff00ff00);
                bingInfo.setValue(30);
            } else if (i == 2) {
                bingInfo.setColor(0xff0000ff);
                bingInfo.setValue(40);
            } else if (i == 3) {
                bingInfo.setColor(0xff999999);
                bingInfo.setValue(10);
            }
            mBingInfos.add(bingInfo);
        }
    }
}
