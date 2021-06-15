package com.fss.owspace.view.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.fss.owspace.R;
import com.fss.owspace.presenter.SplashContract;
import com.fss.owspace.view.fragment.LeftMenuFragment;
import com.fss.owspace.view.widget.VerticalViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  {

    @BindView(R.id.view_pager)
    VerticalViewPager viewPager;
    private SlidingMenu slidingMenu;
    private LeftMenuFragment leftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initMenu();
        initPage();
    }

    private void initPage() {

    }

    private void initMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setMenu(R.layout.left_menu);
        leftMenu = new LeftMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.left_menu,leftMenu).commit();
    }


    @OnClick({R.id.left_slide})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.left_slide:
                slidingMenu.showMenu();
                leftMenu.startAnim();
                break;

        }

    }

}