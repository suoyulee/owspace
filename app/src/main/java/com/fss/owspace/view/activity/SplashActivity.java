package com.fss.owspace.view.activity;


import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;

import com.fss.owspace.R;
import com.fss.owspace.app.OwspaceApplication;
import com.fss.owspace.di.components.DaggerSplashComponent;
import com.fss.owspace.di.modules.SplashModule;
import com.fss.owspace.presenter.SplashContract;
import com.fss.owspace.presenter.SplashPresenter;
import com.fss.owspace.util.AppUtil;
import com.fss.owspace.util.FileUtil;
import com.fss.owspace.util.PreferenceUtils;
import com.fss.owspace.view.widget.FixedImageView;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * author: .fss
 * date:   2021/2/24 11:42
 * desc: 启动页
 * <p>
 * View通常来说是由Activity实现的，它会包含一个Presenter的引用，
 * View要做的就只是在每次有接口调用的时候（比如按钮点击后）调用Presenter的方法
 */

public class SplashActivity extends BaseActivity implements SplashContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.splash_img)
    FixedImageView fixedImageView;
    @Inject
    SplashPresenter presenter;

    private static final int PERMISSION_REQUETCODE = 1;

    protected String[] needPermisson = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initStatus();
    }

    /**
     * 初始化
     */
    private void initPresenter(){
        DaggerSplashComponent.builder()
                .netComponent(OwspaceApplication.get(this).getNetComponent())
                .splashModule(new SplashModule(this))
                .build().inject(this);
    }

    private void initStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View view = getWindow().getDecorView();
            /**
             * 1.View.SYSTEM_UI_FLAG_VISIBLE ：状态栏和Activity共存，Activity不全屏显示。也就是应用平常的显示画面
             * 2.View.SYSTEM_UI_FLAG_FULLSCREEN ：Activity全屏显示，且状态栏被覆盖掉
             * 3. View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ：Activity全屏显示，但是状态栏不会被覆盖掉，而是正常显示，只是Activity顶端布局会被覆盖住
             * 4.View.INVISIBLE ： Activity全屏显示，隐藏状态栏
             *    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
             *    保持整个View稳定, 常和控制System UI悬浮, 隐藏的Flags共用, 使View不会因为System UI的变化而重新layout
             */
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodePermissions();
    }


    private void delaySplash() {
        List<String> picList = FileUtil.getAllAD();
        Logger.d("~~~picList~~~" + picList.size());
        if (picList.size() > 0) {
            //随机展示一张
            Random random = new Random();
            int index = random.nextInt(picList.size());
            int imgIndex = PreferenceUtils.getPrefInt(this, "splash_img_index", 0);
            Logger.i("当前的imgIndex=" + imgIndex);
            if (index == imgIndex) {
                if (index >= picList.size()) {
                    index--;
                } else if (imgIndex == 0) {
                    if (index + 1 < picList.size()) {
                        index++;
                    }
                }
            }
            PreferenceUtils.setPrefInt(this, "splash_img_index", index);
            Logger.i("当前的picList.size=" + picList.size() + ",index = " + index);
            Logger.i("当前的picList" + picList.get(index));
            File file = new File(picList.get(index));
            try {
                InputStream fis = new FileInputStream(file);
                fixedImageView.setImageDrawable(InputStream2Drawable(fis));
                animWelcomeImage();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
        } else {
            //显示本地图片
            try {
                AssetManager assetManager = this.getAssets();
                InputStream in = assetManager.open("welcome_default.jpg");
                fixedImageView.setImageDrawable(InputStream2Drawable(in));
                animWelcomeImage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void animWelcomeImage() {
        ///平移动画:在x轴上平移
        ObjectAnimator animator = ObjectAnimator.ofFloat(fixedImageView, "translationX", -100F);
        animator.setDuration(1500L).start(); //设置持续时间
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private Drawable InputStream2Drawable(InputStream in) {
        Drawable drawable = BitmapDrawable.createFromStream(in, "splashImg");
        return drawable;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @AfterPermissionGranted(PERMISSION_REQUETCODE)
    private void requestCodePermissions() {
        if (!EasyPermissions.hasPermissions(this, needPermisson)) {
            //请求权限
            EasyPermissions.requestPermissions(this, "应用权限获取", PERMISSION_REQUETCODE, needPermisson);
        } else {
            setContentView(R.layout.activity_splash);
            ButterKnife.bind(this);
            delaySplash();
            String deviceID = AppUtil.getDeviceId(this);
            presenter.getSplash(deviceID);
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        recreate();//重建Activity使新的主题生效
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //提示
        Logger.d("onPermissionsDenied");
        showMissPermissionDialog();

    }

    private void showMissPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限。");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", (dialog, which) -> {
            finish();
        });
        builder.setPositiveButton("设置", (dialog, which) -> {
            statrAppSetting();
        });
        builder.show();
    }

    private void statrAppSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS); //应用程序列表
        Logger.d("~~~statrAppSetting~~~" + getPackageName());
        intent.setData(Uri.parse("package:" + getPackageName())); // 设置Intent的Data属性
        startActivity(intent);
    }
}