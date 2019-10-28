package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dingmouren.colorpicker.*;

public class ColorPickerActivity extends AppCompatActivity {

    private Button mButton;
    private ColorPickerDialog mColorPickerDialog;
    private boolean supportAlpha; //颜色是否支持透明度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        mButton = findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColorPickerDialog = new ColorPickerDialog(ColorPickerActivity.this,getResources().getColor(R.color.colorWhite),supportAlpha,mOnColorPickerListener).show();
                supportAlpha = !supportAlpha;
            }
        });
    }

    private OnColorPickerListener mOnColorPickerListener = new OnColorPickerListener() {
        @Override
        public void onColorCancel(ColorPickerDialog dialog) {

        }

        @Override
        public void onColorChange(ColorPickerDialog dialog, int color) {
            mColorPickerDialog.setButtonTextColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(color);
            }
        }

        @Override
        public void onColorConfirm(ColorPickerDialog dialog, int color) {
            if (mButton != null){
                mButton.setBackgroundColor(color);
            }
        }
    };
}
