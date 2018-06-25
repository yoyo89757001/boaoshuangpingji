package com.xiaojun.boaoshuangpingji.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.boaoshuangpingji.MyApplication;
import com.xiaojun.boaoshuangpingji.R;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;
import com.xiaojun.boaoshuangpingji.beans.BitmapsBean;
import com.xiaojun.boaoshuangpingji.beans.MenBean;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;
import com.xiaojun.boaoshuangpingji.dialogs.ChaXunDialog;
import com.xiaojun.boaoshuangpingji.dialogs.XinXiDialog;
import com.xiaojun.boaoshuangpingji.utils.CustomerEngine;
import com.xiaojun.boaoshuangpingji.utils.GlideRoundTransform;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.xiaojun.boaoshuangpingji.utils.Utils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by gqj3375 on 2017/4/28.
 */

public class DetecterActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();
    private ChaXunDialog chaXunDialog=null;
    private TextView shezhi;


    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        EventBus.getDefault().register(this);//订阅

        shezhi = findViewById(R.id.shezhi);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetecterActivity.this, SheZhiActivity.class));
            }
        });


        //开启副屏
        CustomerEngine.getInstance(getApplicationContext(), DetecterActivity.this);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(MenBean event) {
        //比对出来的 直接签到 隐藏重拍按钮

        XinXiDialog  xinXiDialog = new XinXiDialog(DetecterActivity.this, 0, event, null);
            xinXiDialog.setCanceledOnTouchOutside(false);
            xinXiDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    EventBus.getDefault().post("xinxi");
                    Log.d("DetecterActivity", "关闭信息弹窗");
                }
            });
            xinXiDialog.show();


    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(BitmapsBean event) {

        if (chaXunDialog==null) {
            chaXunDialog = new ChaXunDialog(DetecterActivity.this,event);
            chaXunDialog.setCanceledOnTouchOutside(false);
            chaXunDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                    EventBus.getDefault().post("msr");
                    chaXunDialog = null;

                    Log.d("DetecterActivity", "关闭陌生人弹窗");
                }
            });
            chaXunDialog.show();
        }else {
            //更新图片
            chaXunDialog.updataTuPian(event);


        }

//            Glide.with(DetecterActivity.this)
//                    //	.load(R.drawable.vvv)
//                    .load(Bitmap2Bytes(event.getBitmap()))
//                    .error(R.drawable.erroy_bg)
//                    //.apply(myOptions)
//                    .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
//                    //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
//                    .into(touxiang);


    }

    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);//解除订阅
        super.onDestroy();


    }




}
