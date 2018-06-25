package com.xiaojun.boaoshuangpingji.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.boaoshuangpingji.MyApplication;
import com.xiaojun.boaoshuangpingji.R;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;
import com.xiaojun.boaoshuangpingji.dialogs.XiuGaiHouTaiDialog;
import com.xiaojun.boaoshuangpingji.dialogs.XiuGaiHouTaiDialog2;
import com.xiaojun.boaoshuangpingji.utils.FileUtil;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.xiaojun.boaoshuangpingji.utils.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


import static com.xiaojun.boaoshuangpingji.MyApplication.TIMEOUT;


public class SheZhiActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {
    private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12;
    private List<Button> sheZhiBeanList;
    private BaoCunBeanDao baoCunBeanDao=null;
    private BaoCunBean baoCunBean=null;
    private int dw,dh;
    public  OkHttpClient okHttpClient=null;
    private static String usbPath=null;
    private StringBuilder stringBuilder=new StringBuilder();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dw = Utils.getDisplaySize(SheZhiActivity.this).x;
        dh = Utils.getDisplaySize(SheZhiActivity.this).y;
        baoCunBeanDao= MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
        baoCunBean=baoCunBeanDao.load(123456L);
        if (baoCunBean==null){
            BaoCunBean baoBean=new BaoCunBean();
            baoBean.setId(123456L);
            baoCunBeanDao.insert(baoBean);
            baoCunBean=baoCunBeanDao.load(123456L);
        }


        setContentView(R.layout.activity_she_zhi);

        if (dw>dh){
            /**
             * 设置为横屏
             */
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

        }else {

            /**
             * 设置为竖屏
             */
            if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

        }


        bt1= (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt1.setOnFocusChangeListener(this);
        bt2= (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        bt2.setOnFocusChangeListener(this);
        bt3= (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(this);
        bt3.setOnFocusChangeListener(this);
        bt4= (Button) findViewById(R.id.bt4);
        bt4.setOnClickListener(this);
        bt4.setOnFocusChangeListener(this);
        bt5= (Button) findViewById(R.id.bt5);
        bt5.setOnClickListener(this);
        bt5.setOnFocusChangeListener(this);
        bt6= (Button) findViewById(R.id.bt6);
        bt6.setOnClickListener(this);
        bt6.setOnFocusChangeListener(this);
        bt7= (Button) findViewById(R.id.bt7);
        bt7.setOnClickListener(this);
        bt7.setOnFocusChangeListener(this);
        bt8= (Button) findViewById(R.id.bt8);
        bt8.setOnClickListener(this);
        bt8.setOnFocusChangeListener(this);
        bt9= (Button) findViewById(R.id.bt9);
        bt9.setOnClickListener(this);
        bt9.setOnFocusChangeListener(this);
        bt10= (Button) findViewById(R.id.bt10);
        bt10.setOnClickListener(this);
        bt10.setOnFocusChangeListener(this);
        bt11= (Button) findViewById(R.id.bt11);
        bt11.setOnClickListener(this);
        bt11.setOnFocusChangeListener(this);
        bt12= (Button) findViewById(R.id.bt12);
        bt12.setOnClickListener(this);
        bt12.setOnFocusChangeListener(this);
        bt1.requestFocus();

        sheZhiBeanList = new ArrayList<>();
        sheZhiBeanList.add(bt1);
        sheZhiBeanList.add(bt2);
        sheZhiBeanList.add(bt3);
        sheZhiBeanList.add(bt4);
        sheZhiBeanList.add(bt5);
        sheZhiBeanList.add(bt6);
        sheZhiBeanList.add(bt7);
        sheZhiBeanList.add(bt8);
        sheZhiBeanList.add(bt9);
        sheZhiBeanList.add(bt10);
        sheZhiBeanList.add(bt11);
        sheZhiBeanList.add(bt12);


}

    @Override
    protected void onPause() {
        //开启Activity
        //  Log.d("SheZhiActivity", "baoCunBean.getMoban():" + baoCunBean.getMoban());
        switch (baoCunBean.getMoban()){
            case 1:


                break;

            case 2:

                break;
            case 3:

                break;
            case 4:


                break;

        }

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("SheZhiActivity", "停止");

        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                ChongsZHI();
                bt1.requestFocus();
                bt1.setTextColor(Color.WHITE);
                bt1.setBackgroundResource(R.drawable.zidonghuoqu1);
                  AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt1,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt1,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet.setDuration(300);
                    animatorSet.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet.start();
                break;
            case R.id.bt2:
                ChongsZHI();
                bt2.requestFocus();
                bt2.setTextColor(Color.WHITE);
                bt2.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt2,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt2,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet2.setDuration(300);
                animatorSet2.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {


                    }
                });
                animatorSet2.start();
                break;
            case R.id.bt3:
                ChongsZHI();
                bt3.requestFocus();
                bt3.setTextColor(Color.WHITE);
                bt3.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt3,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt3,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet3.setDuration(300);
                animatorSet3.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        final XiuGaiXinXiDialog dialog=new XiuGaiXinXiDialog(SheZhiActivity.this);
//                        if (baoCunBean.getTuisongDiZhi()==null){
//                            dialog.setContents("设置推送地址","http://192.168.2.50");
//                        }else {
//                            dialog.setContents("设置推送地址",baoCunBean.getTuisongDiZhi());
//                        }
//                        dialog.setOnQueRenListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                baoCunBean.setTuisongDiZhi(dialog.getContents());
//                                baoCunBeanDao.update(baoCunBean);
//                                baoCunBean=baoCunBeanDao.load(123456L);
//                                dialog.dismiss();
//                            }
//                        });
//                        dialog.setQuXiaoListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                dialog.dismiss();
//                            }
//                        });
//                        dialog.show();

                    }
                });
                animatorSet3.start();

                break;
            case R.id.bt4:
                ChongsZHI();
                bt4.requestFocus();
                bt4.setTextColor(Color.WHITE);
                bt4.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet4 = new AnimatorSet();
                animatorSet4.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt4,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt4,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet4.setDuration(300);
                animatorSet4.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {



                    }
                });
                animatorSet4.start();
                break;
            case R.id.bt5:
                ChongsZHI();
                bt5.requestFocus();
                bt5.setTextColor(Color.WHITE);
                bt5.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet5 = new AnimatorSet();
                animatorSet5.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt5,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt5,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet5.setDuration(300);
                animatorSet5.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (baoCunBean.getIsShowMoshengren()){ //false为 竖屏
                            baoCunBean.setIsShowMoshengren(false);
                            baoCunBeanDao.update(baoCunBean);
                            baoCunBean=baoCunBeanDao.load(123456L);
                            bt5.setText("已设置为不弹");
                            TastyToast.makeText(SheZhiActivity.this,"已设置为不弹",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();

                        }else {
                            baoCunBean.setIsShowMoshengren(true);
                            baoCunBeanDao.update(baoCunBean);
                            baoCunBean=baoCunBeanDao.load(123456L);
                            bt5.setText("已设置为弹出");
                            TastyToast.makeText(SheZhiActivity.this,"已设置为弹出",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();
                        }


                    }
                });
                animatorSet5.start();
                break;
            case R.id.bt6:
                ChongsZHI();
                bt6.requestFocus();
                bt6.setTextColor(Color.WHITE);
                bt6.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet6 = new AnimatorSet();
                animatorSet6.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt6,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt6,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet6.setDuration(300);
                animatorSet6.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                       //语音设置


                    }
                });
                animatorSet6.start();

                break;

            case R.id.bt7:
                ChongsZHI();
                bt7.requestFocus();
                bt7.setTextColor(Color.WHITE);
                bt7.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet7 = new AnimatorSet();
                animatorSet7.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt7,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt7,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet7.setDuration(300);
                animatorSet7.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗

                        bt7.setEnabled(true);
                    }
                });
                animatorSet7.start();
                bt7.setEnabled(false);

                break;

            case R.id.bt8:
                ChongsZHI();
                bt8.requestFocus();
                bt8.setTextColor(Color.WHITE);
                bt8.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet8 = new AnimatorSet();
                animatorSet8.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt8,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt8,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet8.setDuration(300);
                animatorSet8.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗
                        final XiuGaiHouTaiDialog2 dialog2=new XiuGaiHouTaiDialog2(SheZhiActivity.this);
                        if (baoCunBean.getHoutaiDiZhi()==null ){
                            dialog2.setContents("http://192.168.2.161:8080","");
                        }else {
                            dialog2.setContents(baoCunBean.getHoutaiDiZhi(),baoCunBean.getZhanhuiId());
                        }
                        dialog2.setOnQueRenListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baoCunBean.setHoutaiDiZhi(dialog2.getContents());
                              //  baoCunBean.setGuanggaojiMing(dialog2.getGuangGaoJiMing());
                                baoCunBean.setZhanhuiId(dialog2.getZhangHuId());
                                baoCunBeanDao.update(baoCunBean);
                                baoCunBean=baoCunBeanDao.load(123456L);
                                try {

                                    getOkHttpClient(baoCunBean.getZhanhuiId());

                                }catch (Exception e){
                                    Log.d("SheZhiActivity", e.getMessage());
                                }

                                dialog2.dismiss();

                            }
                        });
                        dialog2.setQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                            }
                        });
                        dialog2.show();


                        bt8.setEnabled(true);
                    }
                });
                animatorSet8.start();
                bt8.setEnabled(false);

                break;
            case R.id.bt9:

                ChongsZHI();
                bt9.requestFocus();
                bt9.setTextColor(Color.WHITE);
                bt9.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet9 = new AnimatorSet();
                animatorSet9.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt9,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt9,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet9.setDuration(300);
                animatorSet9.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗
                        final XiuGaiHouTaiDialog dialog=new XiuGaiHouTaiDialog(SheZhiActivity.this);
                        if (baoCunBean.getHoutaidizhi_ks()==null || baoCunBean.getZhanghao_ks()==null){
                            dialog.setContents("http://192.168.2.161","","");
                        }else {
                            dialog.setContents(baoCunBean.getHoutaidizhi_ks(),baoCunBean.getMima_ks(),baoCunBean.getZhanghao_ks());
                        }
                        dialog.setOnQueRenListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                baoCunBean.setHoutaidizhi_ks(dialog.getContents());
                                baoCunBean.setMima_ks(dialog.getGuangGaoJiMing());
                                baoCunBean.setZhanghao_ks(dialog.getZhangHuId());
                                baoCunBeanDao.update(baoCunBean);
                                baoCunBean=baoCunBeanDao.load(123456L);
                                try {
                                    getOkHttpClient2(dialog.getZhangHuId(),dialog.getGuangGaoJiMing(),dialog.getContents());

                                }catch (Exception e){
                                    Log.d("SheZhiActivity", e.getMessage());
                                }

                                dialog.dismiss();

                            }
                        });
                        dialog.setQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                        bt9.setEnabled(true);
                    }
                });
                animatorSet9.start();
                bt9.setEnabled(false);

                break;
            case R.id.bt10:

                ChongsZHI();
                bt10.requestFocus();
                bt10.setTextColor(Color.WHITE);
                bt10.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet10 = new AnimatorSet();
                animatorSet10.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt10,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt10,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet10.setDuration(300);
                animatorSet10.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗


                        bt10.setEnabled(true);
                    }
                });
                animatorSet10.start();
                bt10.setEnabled(false);
                break;
            case R.id.bt11:
                ChongsZHI();
                bt11.requestFocus();
                bt11.setTextColor(Color.WHITE);
                bt11.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet11 = new AnimatorSet();
                animatorSet11.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt11,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt11,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet11.setDuration(300);
                animatorSet11.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗



                        bt11.setEnabled(true);
                    }
                });
                animatorSet11.start();
                bt11.setEnabled(false);

                break;
            case R.id.bt12:
                bt12.setEnabled(false);
                ChongsZHI();
                bt12.requestFocus();
                bt12.setTextColor(Color.WHITE);
                bt12.setBackgroundResource(R.drawable.zidonghuoqu1);
                AnimatorSet animatorSet12 = new AnimatorSet();
                animatorSet12.playTogether(
                        //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                        ObjectAnimator.ofFloat(bt12,"scaleX",1.0f,1.2f,1.0f),
                        ObjectAnimator.ofFloat(bt12,"scaleY",1.0f,1.2f,1.0f)
                );
                //animatorSet.setInterpolator(new DescelerateInterpolator());
                animatorSet12.setDuration(300);
                animatorSet12.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //弹窗


                    }
                });
                animatorSet12.start();


                break;

        }

    }


    private void  ChongsZHI(){
        if (sheZhiBeanList!=null){
        for (int i=0;i<sheZhiBeanList.size();i++){
            sheZhiBeanList.get(i).setBackgroundResource(R.drawable.zidonghuoqu2);
            sheZhiBeanList.get(i).setTextColor(Color.parseColor("#1b37d6"));
          }
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.bt1:
              //  Log.d("SheZhiActivity", "hasFocus:1" + hasFocus);
                if (hasFocus){
                    ChongsZHI();
                    bt1.setTextColor(Color.WHITE);
                    bt1.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt1,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt1,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
                break;
            case R.id.bt2:
             //   Log.d("SheZhiActivity", "hasFocus:2" + hasFocus);
                if (hasFocus){
                    ChongsZHI();
                    bt2.setTextColor(Color.WHITE);
                    bt2.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt2,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt2,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
                break;
            case R.id.bt3:
                if (hasFocus){
                    ChongsZHI();
                    bt3.setTextColor(Color.WHITE);
                    bt3.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt3,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt3,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
               // Log.d("SheZhiActivity", "hasFocus:3" + hasFocus);
                break;
            case R.id.bt4:
                if (hasFocus){
                    ChongsZHI();
                    bt4.setTextColor(Color.WHITE);
                    bt4.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt4,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt4,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
               // Log.d("SheZhiActivity", "hasFocus:4" + hasFocus);
                break;
            case R.id.bt5:
                if (hasFocus){
                    ChongsZHI();
                    bt5.setTextColor(Color.WHITE);
                    bt5.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt5,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt5,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
              //  Log.d("SheZhiActivity", "hasFocus:5" + hasFocus);
                break;
            case R.id.bt6:
                if (hasFocus){
                    ChongsZHI();
                    bt6.setTextColor(Color.WHITE);
                    bt6.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt6,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt6,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }

                break;
            case R.id.bt7:
                if (hasFocus){
                    ChongsZHI();
                    bt7.setTextColor(Color.WHITE);
                    bt7.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt7,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt7,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
              //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
            case R.id.bt8:
                if (hasFocus){
                    ChongsZHI();
                    bt8.setTextColor(Color.WHITE);
                    bt8.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt8,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt8,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
                //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
            case R.id.bt9:
                if (hasFocus){
                    ChongsZHI();
                    bt9.setTextColor(Color.WHITE);
                    bt9.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet9 = new AnimatorSet();
                    animatorSet9.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt9,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt9,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet9.setDuration(300);
                    animatorSet9.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet9.start();
                }
                //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
            case R.id.bt10:
                if (hasFocus){
                    ChongsZHI();
                    bt10.setTextColor(Color.WHITE);
                    bt10.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet10 = new AnimatorSet();
                    animatorSet10.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt10,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt10,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet10.setDuration(300);
                    animatorSet10.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet10.start();
                }
                //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
            case R.id.bt11:
                if (hasFocus){
                    ChongsZHI();
                    bt11.setTextColor(Color.WHITE);
                    bt11.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt11,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt11,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
                //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
            case R.id.bt12:
                if (hasFocus){
                    ChongsZHI();
                    bt12.setTextColor(Color.WHITE);
                    bt12.setBackgroundResource(R.drawable.zidonghuoqu1);
                    AnimatorSet animatorSet6 = new AnimatorSet();
                    animatorSet6.playTogether(
                            //	ObjectAnimator.ofFloat(manager.getChildAt(1),"translationY",-1000,0),
                            ObjectAnimator.ofFloat(bt12,"scaleX",1.0f,1.2f,1.0f),
                            ObjectAnimator.ofFloat(bt12,"scaleY",1.0f,1.2f,1.0f)
                    );
                    //animatorSet.setInterpolator(new DescelerateInterpolator());
                    animatorSet6.setDuration(300);
                    animatorSet6.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }
                    });
                    animatorSet6.start();
                }
                //  Log.d("SheZhiActivity", "hasFocus7:" + hasFocus);
                break;
        }
    }



  //  往SD卡写入文件的方法
    public void savaFileToSD(String filename, String filecontent) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;

            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream output = new FileOutputStream(filename,true);
            output.write(filecontent.getBytes());
            //将String字符串以字节流的形式写入到输出流中
            output.close();
            //关闭输出流
        }
    }

    //后台信息
    private void getOkHttpClient(String id){
        Log.d("SheZhiActivity", "jddddddddddddddddd");

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .build();

        Request.Builder requestBuilder = new Request.Builder();
        //requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type","application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi()+"/qryExhibitionById.do");
        final Request request = requestBuilder.build();

        Call mcall= okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TastyToast.makeText(SheZhiActivity.this,"查询失败",TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    String s=response.body().string();
                    Log.d("shezhiactivity", "查询"+s);
                    JsonObject jsonObject= GsonUtil.parse(s).getAsJsonObject();

                    //登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
                    int  jo=jsonObject.get("companyId").getAsInt();
                    if (jo!=0){
                        baoCunBean.setMoban(jo);
                        baoCunBean.setZhanhuiBianMa(jsonObject.get("streamingNo").getAsString());
                        baoCunBeanDao.update(baoCunBean);
                    }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TastyToast.makeText(SheZhiActivity.this,"获取客户id成功",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
                            }
                        });

                }catch (final Exception e){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TastyToast.makeText(SheZhiActivity.this,"查询失败"+e.getMessage(),TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                        }
                    });
                }
            }

        });

    }


    //首先登录-->获取所有主机-->创建或者删除或者更新门禁
    private void getOkHttpClient2(String userName,String pwd,String url){
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("username", userName)
                .add("password", pwd)
                .add("pad_id", Utils.getSerialNumber(this)==null?Utils.getIMSI():Utils.getSerialNumber(SheZhiActivity.this))
                .add("device_type", "2")
                .build();

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type","application/json");
        requestBuilder.post(body);
        requestBuilder.url(url+"/pad/login");
        final Request request = requestBuilder.build();

        Call mcall= okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TastyToast.makeText(SheZhiActivity.this,"登陆失败",TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                String s=response.body().string();
                Log.d("shezhiactivity", "登录"+s);
                JsonObject jsonObject= GsonUtil.parse(s).getAsJsonObject();
                int n=1;
                n=jsonObject.get("code").getAsInt();
                if (n==0){
                    //登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
                    JsonObject jo=jsonObject.get("data").getAsJsonObject();
                    baoCunBean.setScreen_token(jo.get("screen_token").getAsString());
                    baoCunBeanDao.update(baoCunBean);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TastyToast.makeText(SheZhiActivity.this,"获取Screen_token成功",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TastyToast.makeText(SheZhiActivity.this,"获取Screen_token失败",TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                        }
                    });
                }
                }catch (final Exception e){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TastyToast.makeText(SheZhiActivity.this,"登陆失败"+e.getMessage(),TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                        }
                    });
                }
            }

        });

    }


    @Override
    protected void onResume() {
        super.onResume();


    }




//    public static class  UsbBroadCastReceiver extends BroadcastReceiver {
//
//        public UsbBroadCastReceiver() {
//        }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            if (intent.getAction()!=null && intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")){
//                usbPath = intent.getData().getPath();
//                List<String> sss=  FileUtil.getMountPathList();
//                int size=sss.size();
//                for (int i=0;i<size;i++){
//
//                    if (sss.get(i).contains(usbPath)){
//                        usbPath=sss.get(i);
//                    }
//
//                }
//
//                Log.d("UsbBroadCastReceiver", usbPath);
//            }
//
//
//        }
//    }


}
