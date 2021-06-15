package com.fss.owspace.view.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.security.acl.LastOwnerException;

/**
 * author: .fss
 * date:   2021/2/23 16:09
 * desc: 自适应imageview 高度适配屏幕大小
 */
public class FixedImageView extends ImageView {
    private int mScreenHeight;
    private int mScreenWidth;

    public FixedImageView(Context context) {
        this(context, null);
    }

    public FixedImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mScreenWidth = getScreenWidthHeight(context)[0];
        this.mScreenHeight = getScreenWidthHeight(context)[1];
    }

    /**
     * 获取屏幕宽高
     *
     * @return
     */
    private static int[] getScreenWidthHeight(Context context) {
        int[] arrayOfInt = new int[2];
        if (context == null)
            return arrayOfInt;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();;
        arrayOfInt[0] = displayMetrics.widthPixels;
        arrayOfInt[1] = displayMetrics.heightPixels;
        Logger.d(" displayMetrics.widthPixels:" +arrayOfInt[0]);
        Logger.d(" displayMetrics.heightPixels:" +arrayOfInt[1]);
        Logger.d("  displayMetrics.density:" + displayMetrics.density); //屏幕密度 像素比例 在Android里面的意思是表示 DP 和 PX 之间的比例
        Logger.d("  displayMetrics.densityDpi:" + displayMetrics.densityDpi); // 屏幕密度 每寸像素
        float widthDP =  arrayOfInt[0] / 2;
        float heightDP=  arrayOfInt[1] / 2;
        float smallestWidthDP;
        if(widthDP < heightDP) {
            smallestWidthDP = widthDP;
        }else {
            smallestWidthDP = heightDP;
        }

        return arrayOfInt;
    }


    //onMeasure方法的作用就是计算出自定义View的宽度和高度。
    //这个计算的过程参照父布局给出的大小，以及自己特点算出结果
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取父容器的宽度
        int i = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(i,this.mScreenHeight);
    }
}
