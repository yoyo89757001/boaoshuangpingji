package com.xiaojun.boaoshuangpingji.ui;

import android.app.Activity;
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
import com.xiaojun.boaoshuangpingji.utils.CustomerEngine;
import com.xiaojun.boaoshuangpingji.utils.GlideRoundTransform;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.xiaojun.boaoshuangpingji.utils.Utils;

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
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.jieshao)
    TextView jieshao;
    @BindView(R.id.a1)
    EditText a1;
    @BindView(R.id.a2)
    EditText a2;
    @BindView(R.id.a3)
    EditText a3;
    @BindView(R.id.a4)
    EditText a4;
    @BindView(R.id.a5)
    EditText a5;
    @BindView(R.id.a6)
    EditText a6;
    @BindView(R.id.a7)
    EditText a7;
    @BindView(R.id.a8)
    EditText a8;
    @BindView(R.id.a9)
    EditText a9;
    @BindView(R.id.a10)
    EditText a10;
    @BindView(R.id.a11)
    EditText a11;
    @BindView(R.id.a12)
    EditText a12;
    @BindView(R.id.baoming)
    TextView baoming;
    @BindView(R.id.baocun)
    Button baocun;
    @BindView(R.id.root_ll)
    RelativeLayout rootLl;
    @BindView(R.id.guanbi)
    Button guanbi;
    @BindView(R.id.chongpai)
    Button chongpai;
    private TextView shezhi;
    private OkHttpClient okHttpClient = null;
    private final int TIMEOUT = 1000 * 30;
    private String screen_token = null;
    private static boolean isT = true;
    private static boolean isB = false;
    private BaoCunBeanDao baoCunBeanDao = MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
    private BaoCunBean baoCunBean = null;
    private MenBean menBeans=null;

    public Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(final Message msg) {
            switch (msg.what) {
//				case 111:
//					//更新地址
//
//					break;
//				case 110:
//					if (lingdaoList.size() > 1) {
//
////						AnimatorSet animatorSet = new AnimatorSet();
////						animatorSet.playTogether(
////								ObjectAnimator.ofFloat(adapter2.getViewByPosition(recyclerView2, 1, R.id.ffflll), "scaleY", 1f, 0f),
////								ObjectAnimator.ofFloat(adapter2.getViewByPosition(recyclerView2, 1, R.id.ffflll), "scaleX", 1f, 0f)
////								//	ObjectAnimator.ofFloat(helper.itemView,"alpha",0f,1f)
////						);
////						animatorSet.setDuration(200);
////						animatorSet.setInterpolator(new AccelerateInterpolator());
////						animatorSet.addListener(new AnimatorListenerAdapter() {
////							@Override
////							public void onAnimationEnd(Animator animation) {
////								adapter2.notifyItemRemoved(1);
////								lingdaoList.remove(1);
////
////							}
////						});
////						animatorSet.start();
//
//					}
//
//
//					break;
                case 999:


                    break;


            }


            return false;
        }
    });


    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baoCunBean = baoCunBeanDao.load(123456L);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
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
        if (isT) {
            isT = false;
            menBeans=event;
            name.setVisibility(View.VISIBLE);
            jieshao.setVisibility(View.VISIBLE);
            chongpai.setVisibility(View.GONE);
            baocun.setText("签 到");
            rootLl.setVisibility(View.VISIBLE);
            if (baoCunBean != null && baoCunBean.getHoutaidizhi_ks() != null)
                Glide.with(DetecterActivity.this)
                        //	.load(R.drawable.vvv)
                        .load(baoCunBean.getHoutaidizhi_ks() + event.getPerson().getTag().getAvatar())
                        .error(R.drawable.erroy_bg)
                        //.apply(myOptions)
                        .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                        //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
                        .into(touxiang);
            name.setText(event.getPerson().getTag().getName());
            a1.setText(event.getPerson().getTag().getName());
            jieshao.setText(event.getPerson().getTag().getDepartment());
        }

        Log.e(TAG, "event---->" + event.getPerson().getTag().getAvatar());

    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(BitmapsBean event) {
        if (isB){
            isB=false;
            Glide.with(DetecterActivity.this)
                    //	.load(R.drawable.vvv)
                    .load(Bitmap2Bytes(event.getBitmap()))
                    .error(R.drawable.erroy_bg)
                    //.apply(myOptions)
                    .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                    //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
                    .into(touxiang);
        }

    }

   private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onDestroy() {
        isT = true;
        isB=false;
        EventBus.getDefault().unregister(this);//解除订阅
        super.onDestroy();


    }


    //首先登录-->获取所有主机-->创建或者删除或者更新门禁
    private void qiandao(String id) {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("exhibitionId", baoCunBean.getZhanhuiId())
                .add("subjectId", id)
                .add("name", a1.getText().toString().trim()) //姓名
                .add("sexs", a7.getText().toString().trim()) //男女
                .add("job", a8.getText().toString().trim()) //职位
                .add("phone", a3.getText().toString().trim()) //电话
                .add("email",a9.getText().toString().trim()) //邮箱
                .add("companyName",a4.getText().toString().trim()) //公司名称
                .add("companyAddress", a10.getText().toString().trim()) //公司地址
                .add("contact", a5.getText().toString().trim()) //联系人
                .add("contact_way", a11.getText().toString().trim()) //联系方式
                .add("roomNumber", a6.getText().toString().trim()) //房间号
                .add("industry", a12.getText().toString().trim()) //行业类别
                .add("province",a2.getText().toString().trim())  //省份
                .add("city", a2.getText().toString().trim())  //市级单位
                .build();

        Request.Builder requestBuilder = new Request.Builder();
       // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/addSubjectExhibition.do");
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
       // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "登陆失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d(TAG, "登录" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    int n = 1;
                    n = jsonObject.get("dtoResult").getAsInt();
                    final String ppp=jsonObject.get("dtoDesc").getAsString();
                    if (n == 0) {
                        //登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TastyToast.makeText(DetecterActivity.this,"签到成功",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();
                                rootLl.setVisibility(View.GONE);
                                isT = true;
                                //清Glide图片缓存
                                touxiang.setImageBitmap(null);
                                chongzhi();
                            }
                        });


                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TastyToast.makeText(DetecterActivity.this,ppp,TastyToast.LENGTH_SHORT,TastyToast.INFO).show();

                            }
                        });

                    }
                }catch (Exception e){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TastyToast.makeText(DetecterActivity.this,"签到异常",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();

                        }
                    });
                    Log.d("DetecterActivity", e.getMessage()+"");
                }

            }
        });

    }


    public static final int TIMEOUT2 = 1000 * 5;

    // 1:N 对比
    private void link_qiandao(final File file, final int size) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT2, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT2, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT2, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        ;
        MultipartBody mBody;
        final MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        builder.addFormDataPart("image", file.getName(), fileBody1);
        builder.addFormDataPart("screen_token", screen_token == null ? "" : screen_token);
        mBody = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
                .header("Content-Type", "application/json")
                .post(mBody)
                .url("http://192.168.2.64" + ":8866/recognize");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
                SystemClock.sleep(300);
                try {

                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功" + call.request().toString() + file.delete());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string();
                    Log.d("AllConnects", "传照片" + ss);
                    String s2 = ss.replace("\\\\u", "@!@#u").replace("\\", "")
                            .replace("tag\": \"{", "tag\":{")
                            .replace("jpg\"}\"", "jpg\"}")
                            .replace("@!@#", "\\");


                    Log.d("AllConnects", "传照片2" + s2);

                    JsonObject jsonObject = GsonUtil.parse(s2).getAsJsonObject();
                    Gson gson = new Gson();
                    MenBean menBean = gson.fromJson(jsonObject, MenBean.class);
                    if (menBean.isCan_door_open()) {

                        Message message = Message.obtain();
                        message.arg1 = 1;
                        message.obj = menBean;
                        handler.sendMessage(message);
                    }

                    Log.d("DetecterActivity", "menBean:" + menBean.isCan_door_open());

                } catch (Exception e) {
                    Log.d("WebsocketPushMsg", e.getMessage() + "");
                } finally {
                    SystemClock.sleep(300);
                    try {

                    } catch (Exception e1) {

                        e1.printStackTrace();
                    }
                }

            }
        });
    }


    /**
     * 压缩图片（质量压缩）
     *
     * @param bitmap
     */
    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            //long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        //	recycleBitmap(bitmap);
        return file;
    }


    @OnClick({R.id.baoming, R.id.baocun, R.id.guanbi,R.id.chongpai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.baoming:
                isT=false;
                baocun.setText("保 存");
                name.setVisibility(View.GONE);
                jieshao.setVisibility(View.GONE);
                chongpai.setVisibility(View.VISIBLE);
                rootLl.setVisibility(View.VISIBLE);
                isB=true;


                break;
            case R.id.baocun:
                if (baocun.getText().toString().equals("保 存")){
                    Log.d("DetecterActivity", "保存报名信息");

                }else {
                    Log.d("DetecterActivity", "保存签到信息");
                    if (menBeans!=null)
                    qiandao(menBeans.getPerson().getTag().getJob_number());

                }


                break;
            case R.id.guanbi:
                rootLl.setVisibility(View.GONE);
                isT = true;
                //清Glide图片缓存
                touxiang.setImageBitmap(null);
                chongzhi();

                break;
            case R.id.chongpai:
                isB=true;

                break;
        }
    }

    public void chongzhi(){
        a1.setText("");
        a2.setText("");
        a3.setText("");
        a4.setText("");
        a5.setText("");
        a6.setText("");
        a7.setText("");
        a8.setText("");
        a9.setText("");
        a10.setText("");
        a11.setText("");
        a12.setText("");
    }


}
