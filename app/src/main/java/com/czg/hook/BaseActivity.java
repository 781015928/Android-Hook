package com.czg.hook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by czg on 2017/7/11.
 */

public class BaseActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText(getClass().getSimpleName());
        view.setTextSize(60);
        setContentView(view);
    }
}
