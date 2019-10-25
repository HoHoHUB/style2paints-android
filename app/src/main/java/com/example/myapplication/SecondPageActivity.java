package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

public class SecondPageActivity extends AppCompatActivity {

    private Button mBtnTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        mBtnTips = findViewById(R.id.btn_tips);
        Drawable drawable = getResources().getDrawable(R.drawable.vector2);
        drawable.setBounds(0,0,50,0);
        mBtnTips.setCompoundDrawables(drawable,null,null,null);
    }
}
