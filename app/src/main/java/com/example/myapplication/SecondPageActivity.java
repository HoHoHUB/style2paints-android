package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;

public class SecondPageActivity extends AppCompatActivity {

    private Button mBtnColorPicker;
    private Button mBtnEraser,mBtnLocater,mBtnTips;
    private ColorPickerDialog mColorPickerDialog = null;
    private boolean supportAlpha = true;//颜色是否支持透明度
    int ColorSave;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        mBtnEraser = findViewById(R.id.rb_eraser);
        mBtnLocater = findViewById(R.id.rb_locator);
        mBtnTips = findViewById(R.id.rb_tips);
        mBtnColorPicker = findViewById(R.id.btn_change_color);
        setListeners();
    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnEraser.setOnClickListener(onClick);
        mBtnLocater.setOnClickListener(onClick);
        mBtnTips.setOnClickListener(onClick);
        mBtnColorPicker.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.rb_eraser:
                    mBtnEraser.setEnabled(false);
                    mBtnLocater.setEnabled(true);
                    mBtnTips.setEnabled(true);
                    break;
                case R.id.rb_locator:
                    mBtnEraser.setEnabled(true);
                    mBtnLocater.setEnabled(false);
                    mBtnTips.setEnabled(true);
                    break;
                case R.id.rb_tips:
                    mBtnEraser.setEnabled(true);
                    mBtnLocater.setEnabled(true);
                    mBtnTips.setEnabled(false);
                    break;
                case R.id.btn_change_color:
                    if(flag == 0) {
                        mColorPickerDialog = new ColorPickerDialog(SecondPageActivity.this,getResources().getColor(R.color.colorWhite,null),supportAlpha,mOnColorPickerListener).show();
                    }else{
                        ColorSave = (Integer) MysharedPreferences.getColor(SecondPageActivity.this);
                        mColorPickerDialog = new ColorPickerDialog(SecondPageActivity.this,ColorSave,supportAlpha,mOnColorPickerListener).show();
                    }
                    break;
            }
        }
    }

    private OnColorPickerListener mOnColorPickerListener = new OnColorPickerListener() {
        @Override
        public void onColorCancel(ColorPickerDialog dialog) {

        }

        @Override
        public void onColorChange(ColorPickerDialog dialog, int color) {
//            mColorPickerDialog.setButtonTextColor(color);
            Boolean bool = MysharedPreferences.setColor(color,SecondPageActivity.this);
            if(bool){
                flag = 1;
            }else{
                flag = 0;
            }
            //设置bar的颜色
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
////                getWindow().setStatusBarColor(color);
//            }
        }

        @Override
        public void onColorConfirm(ColorPickerDialog dialog, int color) {
            if (mBtnColorPicker != null){
                mBtnColorPicker.setBackgroundColor(color);
            }
        }
    };

    public static class MysharedPreferences{

        public static SharedPreferences share(Context context){
            SharedPreferences sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
            return sharedPreferences;
        }

        public static Object getColor(Context context){
            return share(context).getInt("color",0);
        }

        public static boolean setColor(int color,Context context){
            SharedPreferences.Editor e = share(context).edit();
            e.putInt("color",color);
            Boolean bool = e.commit();
            return bool;
        }
    }
}
