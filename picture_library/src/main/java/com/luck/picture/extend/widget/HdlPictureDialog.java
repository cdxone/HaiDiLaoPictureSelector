package com.luck.picture.extend.widget;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.luck.picture.lib.R;

/**
 * 原型进度条
 * 这个和PictureDialog的不同指出在于扩展了样式，增加了一个提示
 */
public class HdlPictureDialog extends Dialog {
    public Context context;
    private TextView tips;
    private String mTips;//显示提示内容

    public HdlPictureDialog(Context context) {
        super(context, R.style.picture_alert_dialog);
        this.context = context;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setWindowAnimations(R.style.DialogWindowStyle);

        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extend_picture_alert_dialog);
        tips = this.findViewById(R.id.tv_tip);
        //设置提示的值
        if (mTips != null && !TextUtils.isEmpty(mTips.trim())){
            tips.setVisibility(View.VISIBLE);
            tips.setText(mTips);
        }
    }

    /**
     * 给提示设置内容
     * @param tip
     */
    public void setTips(String tip){
        mTips = tip;
    }

    /**
     * 隐藏提示
     */
    public void hideTips(){
        if (tips != null){
            tips.setVisibility(View.GONE);
        }
    }

}