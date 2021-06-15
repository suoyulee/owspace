package com.fss.owspace.view.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * ContextWrapper类的源码，ContextWrapper中有一个attachBaseContext()方法，
     * 这个方法会将传入的一个Context参数赋值给mBase对象，之后mBase对象就有值了。
     * Application中在onCreate()方法里去初始化各种全局的变量数据是一种比较推荐的做法，
     * 但是如果你想把初始化的时间点提前到极致，可以去重写attachBaseContext()方法
     *
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        // 在这里调用Context的方法会崩溃
        super.attachBaseContext(newBase);
        // 在这里可以正常调用Context的方法
    }
}

