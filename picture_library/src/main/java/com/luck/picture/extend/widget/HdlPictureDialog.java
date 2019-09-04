package com.luck.picture.extend.widget;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
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
    private OnKeyDownListener mOnKeyDownListener;

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

    /**
     * 按下返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (isShowing()) {
            // 当按下返回键的时候,将返回的这个事件传递出去
            if (mOnKeyDownListener != null){
                mOnKeyDownListener.onKeyDown();
            }
            dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置返回键的监听
     * @param listener
     */
    public void setOnKeyDownListener(OnKeyDownListener listener) {
        mOnKeyDownListener = listener;
    }

    /**
     * 返回键的监听
     */
    public interface OnKeyDownListener{
        void onKeyDown();
    }
}