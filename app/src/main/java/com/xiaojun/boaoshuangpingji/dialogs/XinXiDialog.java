package com.xiaojun.boaoshuangpingji.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.xiaojun.boaoshuangpingji.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class XinXiDialog extends Dialog  {
    private Button tijiao;


    public XinXiDialog(Context context) {
        super(context, R.style.dialog_style);
        Window window =  this.getWindow();
        if ( window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = LayoutParams.WRAP_CONTENT;
                attr.width = LayoutParams.WRAP_CONTENT;
                attr.gravity = Gravity.CENTER;//设置dialog 在布局中的位置
            }
        }
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.xinxiitem, null);
        ScreenAdapterTools.getInstance().loadView(mView);
        tijiao=mView.findViewById(R.id.tijiao);

        super.setContentView(mView);
    }


    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }



    @Override
    public void setContentView(int layoutResID) {

    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */

    public void setOnQueRenListener(View.OnClickListener listener){
        tijiao.setOnClickListener(listener);
    }

//    /**
//     * 取消键监听器
//     * @param listener
//     */
//
//    public void setQuXiaoListener(View.OnClickListener listener){
//        l2.setOnClickListener(listener);
//    }


//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        switch (v.getId()){
//            case R.id.queren:
//                l1.setBackgroundResource(R.drawable.jiaohu_tc);
//                l1.setTextColor(Color.WHITE);
//                l2.setBackgroundColor(Color.TRANSPARENT);
//                l2.setTextColor(Color.parseColor("#FF1c97fe"));
//
//                break;
//            case R.id.quxiao:
//                l2.setBackgroundResource(R.drawable.jiaohu_tc);
//                l2.setTextColor(Color.WHITE);
//                l1.setBackgroundColor(Color.TRANSPARENT);
//                l1.setTextColor(Color.parseColor("#FF1c97fe"));
//                break;
//        }
//    }
}
