package com.fss.owspace.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fss.owspace.R;
import com.fss.owspace.model.entity.Event;
import com.fss.owspace.util.tools.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: .fss
 * date:   2021/5/13 11:45
 * desc:
 */
public class LeftMenuFragment extends Fragment {

    @BindView(R.id.right_slide_close)
    ImageView rightSlideClose;
    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.home_page_tv)
    TextView home_page_tv;
    @BindView(R.id.words_tv)
    TextView words_tv;
    @BindView(R.id.voice_tv)
    TextView voice_tv;
    @BindView(R.id.video_tv)
    TextView video_tv;
    @BindView(R.id.calendar_tv)
    TextView calendar_tv;

    private List<View> mViewList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_meun,container,false);
        ButterKnife.bind(this,view);
        loadView();
        return view;
    }

    private void loadView() {
        mViewList.add(home_page_tv);
        mViewList.add(words_tv);
        mViewList.add(voice_tv);
        mViewList.add(video_tv);
        mViewList.add(calendar_tv);
    }

    @OnClick({R.id.right_slide_close,R.id.search,
    R.id.home_page_tv,R.id.words_tv,R.id.voice_tv,R.id.video_tv,R.id.calendar_tv})
    public void onClick(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.right_slide_close:
                RxBus.getInstance().postEvent(new Event(1000,"closeMenu"));
                break;
            case R.id.search:
                break;
            case R.id.home_page_tv:
                RxBus.getInstance().postEvent(new Event(1000,"closeMenu"));
                break;
            case R.id.words_tv:
                break;
            case R.id.voice_tv:
                break;
            case R.id.video_tv:
                break;
            case R.id.calendar_tv:
                break;
        }

    }

    public void startAnim(){
        startIconAnim(search);
        startIconAnim(rightSlideClose);
        startColumnAnim();

    }

    private void startIconAnim(View view) {
        //???????????? ????????????
        ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, view.getWidth() / 2, view.getHeight() / 2);
        localScaleAnimation.setDuration(1000L);
        view.startAnimation(localScaleAnimation);
        float f1 = view.getWidth() / 2;
        float f2 = view.getHeight() / 2;
        localScaleAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, f1, f2);
        localScaleAnimation.setInterpolator(new BounceInterpolator());
    }

    private void startColumnAnim() {
        //???????????? ????????????
        /**
         *
             duration ????????????????????? ml
             fromYDelta ?????????????????????Y?????????????????????????????????????????????
             1.??????50???????????????View????????????Y?????????+50px???
             2.?????????50%???????????????View????????????Y?????????+???View????????????50%???
             3.?????????p 50%p?????????View?????????Y?????????+??????????????????50%???
             toYDelta ???????????????Y????????????
             fromXDelta : ???????????????X????????????
             toXDelta ??????????????????X????????????
         */
        TranslateAnimation translateAnimation = new TranslateAnimation(0F,0.0F,0.0F,0.0F);
        translateAnimation.setDuration(700L);
        for (int i =0 ;i<mViewList.size();i++){
            View view = this.mViewList.get(i);
            view.startAnimation(translateAnimation);
            translateAnimation = new TranslateAnimation(i* -35,0.0F,0.0F,0.0F);
            translateAnimation.setDuration(700L);
        }
    }
}

