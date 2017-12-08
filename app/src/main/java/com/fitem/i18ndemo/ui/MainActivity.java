package com.fitem.i18ndemo.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fitem.i18ndemo.R;
import com.fitem.i18ndemo.base.ui.BaseActivity;
import com.fitem.i18ndemo.utils.I18NUtils;
import com.fitem.i18ndemo.widget.pop.PopUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private PopupWindow mPopupWindow;

    @OnClick(R.id.tv_select_language)
    public void toSelectLanguage() {
        showSelectPop();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    private void showSelectPop() {
        // 设置contentView
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.pop_select_language, null);
        mPopupWindow = new PopupWindow(contentView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置点击事件
        contentView.findViewById(R.id.tv_default_language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                toSetLanguage(0);
            }
        });
        contentView.findViewById(R.id.tv_english).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                toSetLanguage(1);
            }
        });

        contentView.findViewById(R.id.tv_chinese).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                toSetLanguage(2);
            }
        });

        contentView.findViewById(R.id.tv_thai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                toSetLanguage(3);
            }
        });

        // 外部是否可以点击
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        //设置动画
        mPopupWindow.setAnimationStyle(R.style.betSharePopAnim);
        PopUtils.setBackgroundAlpha(this, 0.5f);//设置屏幕透明度
        // 显示PopupWindow
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                PopUtils.setBackgroundAlpha(MainActivity.this, 1.0f);
            }
        });
    }

    private void toSetLanguage(int type) {
        I18NUtils.setLocale(this, type);
        toRestartMainActvity();
    }

    private void toRestartMainActvity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }
}