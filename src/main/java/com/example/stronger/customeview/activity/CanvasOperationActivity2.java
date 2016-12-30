package com.example.stronger.customeview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.stronger.customeview.R;
import com.example.stronger.customeview.widget.CanvasView2;

/**
 * Created by hjm on 2016/12/24 19:43.
 */
public class CanvasOperationActivity2 extends AppCompatActivity {

    private CanvasView2 mCanvasView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_operation2);
        mCanvasView2 = (CanvasView2) findViewById(R.id.canvasView2);
//        mCanvasView2.draw();

    }
}
