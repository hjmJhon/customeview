package com.example.stronger.customeview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.stronger.customeview.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 自定义view
 */
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_dotAndLine)
    Button dotAndLine;
    @InjectView(R.id.btn_bing)
    Button btnBing;
    @InjectView(R.id.btn_canvas)
    Button mBtnCanvas;
    @InjectView(R.id.btn_canvas2)
    Button mBtnCanvas2;
    @InjectView(R.id.btn_canvas3)
    Button mBtnCanvas3;
    @InjectView(R.id.btn_path1)
    Button mBtnPath1;
    @InjectView(R.id.btn_path2)
    Button mBtnPath2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_dotAndLine)
    public void onClick() {
        startActivity(new Intent(this, DotAndLineActivity.class));
    }

    @OnClick(R.id.btn_bing)
    public void showBingActivity() {
        startActivity(new Intent(this, BingActivity.class));
    }

    @OnClick(R.id.btn_canvas)
    public void onClick2() {
        startActivity(new Intent(this, CanvasOperationActivity.class));
    }

    @OnClick(R.id.btn_canvas2)
    public void onClick3() {
        startActivity(new Intent(this, CanvasOperationActivity2.class));
    }

    @OnClick(R.id.btn_canvas3)
    public void onClick4() {
        startActivity(new Intent(this, CanvasOperationActivity3.class));
    }

    @OnClick(R.id.btn_path1)
    public void onClick5() {
        startActivity(new Intent(this, PathActivity.class));
    }

    @OnClick(R.id.btn_path2)
    public void onClick6() {
        startActivity(new Intent(this,RadarActivity.class));
    }
}
