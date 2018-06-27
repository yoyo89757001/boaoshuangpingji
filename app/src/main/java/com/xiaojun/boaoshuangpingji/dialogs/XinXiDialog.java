package com.xiaojun.boaoshuangpingji.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.badoo.mobile.util.WeakHandler;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.boaoshuangpingji.MyApplication;
import com.xiaojun.boaoshuangpingji.R;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;
import com.xiaojun.boaoshuangpingji.beans.BaoCunId;
import com.xiaojun.boaoshuangpingji.beans.BaoCunIdDao;
import com.xiaojun.boaoshuangpingji.beans.BitmapsBean;
import com.xiaojun.boaoshuangpingji.beans.ChaXunBeans;
import com.xiaojun.boaoshuangpingji.beans.MenBean;
import com.xiaojun.boaoshuangpingji.beans.NameBean;
import com.xiaojun.boaoshuangpingji.beans.ZhanhuiBean;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;
import com.xiaojun.boaoshuangpingji.utils.DateUtils;
import com.xiaojun.boaoshuangpingji.utils.GlideRoundTransform;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
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
 * @author Tom.Cai
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 */
public class XinXiDialog extends Dialog {
    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.xingbie)
    EditText xingbie;
    @BindView(R.id.renyuanleixing)
    EditText renyuanleixing;
    @BindView(R.id.shoujihaoma)
    EditText shoujihaoma;
    @BindView(R.id.zhiweizhiwu)
    EditText zhiweizhiwu;
    @BindView(R.id.gongsimingcheng)
    EditText gongsimingcheng;
    @BindView(R.id.gongsidizhi)
    EditText gongsidizhi;
    @BindView(R.id.lianxiren)
    EditText lianxiren;
    @BindView(R.id.lianxifangshi)
    EditText lianxifangshi;
    @BindView(R.id.fangjianleixing)
    EditText fangjianleixing;
    @BindView(R.id.fangjianhao)
    EditText fangjianhao;
    @BindView(R.id.zuoweihao)
    EditText zuoweihao;
    @BindView(R.id.canzhuohao)
    EditText canzhuohao;
    @BindView(R.id.hangyeleixing)
    EditText hangyeleixing;
    @BindView(R.id.tishi)
    TextView tishi;
    @BindView(R.id.l1)
    LinearLayout l1;
    @BindView(R.id.l2)
    LinearLayout l2;
    @BindView(R.id.l3)
    LinearLayout l3;
    private ListView listView;
    private Button tijiao, chongpai,shangyibu,tijiao2;
    private BaoCunBean baoCunBean = null;
    private BaoCunBeanDao baoCunBeanDao = MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
    private BaoCunIdDao baoCunIdDao=MyApplication.myApplication.getDaoSession().getBaoCunIdDao();
    private final int TIMEOUT = 1000 * 60;
    private Context context;
    private OkHttpClient okHttpClient;
    private WeakHandler mHandler; // We still need at least one hard reference to WeakHandler
    private int type = 0;
    private MenBean menBean = null;
    private NameBean.ObjectsBean objectsBean = null;
    private String zhaopianPath = null;
    private ZhanHuiAdapter zhanHuiAdapter;
    private List<ZhanhuiBean.ObjectsBean> zhanhuiLists=new ArrayList<>();
    private CheckBox checkBox22;

    public XinXiDialog(Context context, int type, MenBean event, NameBean.ObjectsBean objectsBean) {
        super(context, R.style.dialog_style);
        this.type = type;
        this.context = context;
        menBean = event;
        this.objectsBean = objectsBean;
        Window window = this.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = LayoutParams.WRAP_CONTENT;
                attr.width = LayoutParams.WRAP_CONTENT;
                attr.gravity = Gravity.CENTER;//设置dialog 在布局中的位置
            }
        }
        baoCunBean = baoCunBeanDao.load(123456L);
        mHandler = new WeakHandler();
        setCustomDialog();
    }


    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.xinxiitem, null);
        ScreenAdapterTools.getInstance().loadView(mView);

        Window window = this.getWindow();
        window.setContentView(mView);
        ButterKnife.bind(this, mView);

        listView=mView.findViewById(R.id.listview);
        checkBox22=mView.findViewById(R.id.checkbox22);
        checkBox22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // Log.d("XinXiDialog", "isChecked:" + isChecked);
                if (zhanhuiLists.size()>0){
                    for (ZhanhuiBean.ObjectsBean bean :zhanhuiLists){
                        bean.setCheck(isChecked);
                    }
                    zhanHuiAdapter.notifyDataSetChanged();

                }

            }
        });
        zhanHuiAdapter=new ZhanHuiAdapter(context,zhanhuiLists);
        listView.setAdapter(zhanHuiAdapter);
        tijiao = mView.findViewById(R.id.tijiao);
        tijiao2 = mView.findViewById(R.id.tijiao2);
        tijiao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (type) {
                    case 0: {
                        if (menBean.getPerson().getTag().getJob_number() != null && !menBean.getPerson().getTag().getJob_number().equals(""))
                            qiandao(menBean.getPerson().getTag().getJob_number());

                    }
                    break;
                    case 1: {

                        if (!name.getText().toString().trim().equals("")
                                && zhaopianPath != null
                                && !renyuanleixing.getText().toString().trim().equals("")) {

                               xiugai();

                        } else {
                            TastyToast.makeText(context, "请先填写完整信息和照片", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                        }
                    }
                    break;

                    case 2: {
                        Log.d("XinXiDialog", zhaopianPath+"照片地址");
                        if (!name.getText().toString().trim().equals("")
                                && zhaopianPath != null
                                && !renyuanleixing.getText().toString().trim().equals("")) {
                              baoming();

                        } else {
                            TastyToast.makeText(context, "请先填写完整信息和照片", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                        }
                    }
                    break;

                }


            }
        });
        shangyibu = mView.findViewById(R.id.shangyibu);
        shangyibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                shangyibu.setVisibility(View.GONE);
                tijiao.setVisibility(View.VISIBLE);
                tijiao2.setVisibility(View.GONE);

            }
        });
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交 2 是报名  0 是识别出来的   1 是查询出来的
                switch (type) {
                    case 0: {
                        if (menBean.getPerson().getTag().getJob_number() != null && !menBean.getPerson().getTag().getJob_number().equals(""))
                            qiandao(menBean.getPerson().getTag().getJob_number());

                    }
                    break;
                    case 1: {

                        if (!name.getText().toString().trim().equals("")
                                && zhaopianPath != null
                                && !renyuanleixing.getText().toString().trim().equals("")) {
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            tijiao.setVisibility(View.GONE);
                            tijiao2.setVisibility(View.VISIBLE);
                            l3.setVisibility(View.VISIBLE);
                            shangyibu.setVisibility(View.VISIBLE);

                         //   xiugai();

                        } else {
                            TastyToast.makeText(context, "请先填写完整信息和照片", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                        }
                    }
                    break;

                    case 2: {
                        Log.d("XinXiDialog", zhaopianPath+"照片地址");
                        if (!name.getText().toString().trim().equals("")
                                && zhaopianPath != null
                                && !renyuanleixing.getText().toString().trim().equals("")) {
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.VISIBLE);
                            tijiao.setVisibility(View.GONE);
                            tijiao2.setVisibility(View.VISIBLE);
                            shangyibu.setVisibility(View.VISIBLE);

                          //  baoming();
                        } else {
                            TastyToast.makeText(context, "请先填写完整信息和照片", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                        }
                    }
                    break;

                }

            }
        });
        chongpai = mView.findViewById(R.id.chongpai);
        chongpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重拍//发送重拍广播
                EventBus.getDefault().post("msr2");
            }
        });

        if (type == 1 || type == 2) {
            chongpai.setVisibility(View.VISIBLE);
        } else {
            chongpai.setVisibility(View.GONE);
        }
        switch (type) {
            case 0:
                //识别
                chongpai.setVisibility(View.GONE);
                tijiao2.setVisibility(View.VISIBLE);
                tijiao.setVisibility(View.GONE);

                if (menBean.getPerson().getTag().getJob_number() != null && !menBean.getPerson().getTag().getJob_number().equals(""))
                    chaxunOne(menBean.getPerson().getTag().getJob_number());
                if (menBean != null && menBean.getPerson() != null && menBean.getPerson().getTag().getAvatar() != null)
                    Glide.with(context)
                            //	.load(R.drawable.vvv)
                            .load(baoCunBean.getHoutaidizhi_ks() + menBean.getPerson().getTag().getAvatar())
                            .error(R.drawable.erroy_bg)
                            //.apply(myOptions)
                            .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                            //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
                            .into(touxiang);

                break;
            case 1://查询出来
                //查询分会议
                chaxunFHY();
                chongpai.setVisibility(View.VISIBLE);
                if (objectsBean != null) {
                    Glide.with(context)
                            //	.load(R.drawable.vvv)
                            .load(baoCunBean.getHoutaiDiZhi() + "/upload/compare/" + objectsBean.getPhoto_ids())
                            .error(R.drawable.erroy_bg)
                            //.apply(myOptions)
                            .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                            //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
                            .into(touxiang);
                    if (objectsBean.getPhoto_ids() != null && !objectsBean.getPhoto_ids().equals(""))
                        zhaopianPath = objectsBean.getPhoto_ids();
                    name.setText(objectsBean.getName());
                    xingbie.setText(objectsBean.getSexs());
                    renyuanleixing.setText(objectsBean.getDepartment());
                    shoujihaoma.setText(objectsBean.getPhone());
                    zhiweizhiwu.setText(objectsBean.getTitle());
                    gongsimingcheng.setText(objectsBean.getCome_from());
                    gongsidizhi.setText(objectsBean.getInterviewee());
                    lianxiren.setText(objectsBean.getContact());
                    lianxifangshi.setText(objectsBean.getContactWay());
                    fangjianleixing.setText(objectsBean.getRoomType());
                    fangjianhao.setText(objectsBean.getRoomNumber());
                    zuoweihao.setText(objectsBean.getLocation());
                    hangyeleixing.setText(objectsBean.getIdentity());

                }

                break;
            case 2:
                //报名
                //查询分会议
                chaxunFHY();
                chongpai.setVisibility(View.VISIBLE);

                break;

        }
        super.setContentView(mView);
    }

    public void updataTuPian(BitmapsBean event) {
        //更新图片 并且去旷视验证质量 质量通过 上传到瑞瞳后台
        getOkHttpClient2(compressImage(event.getBitmap()));
        Glide.with(context)
                //	.load(R.drawable.vvv)
                .load(bitmapTobyte(event.getBitmap()))
                .error(R.drawable.erroy_bg)
                //.apply(myOptions)
                .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                //.transform(new GlideCircleTransform(MyApplication.getAppContext(),2, Color.parseColor("#ffffffff")))
                .into(touxiang);
    }


    private byte[] bitmapTobyte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();

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
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            //long length = baos.toByteArray().length;
        }
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date date = new Date(System.currentTimeMillis());
        // String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
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


    //首先登录-->获取所有主机-->创建或者删除或者更新门禁
    private void getOkHttpClient2(final File file) {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("username", baoCunBean.getZhanghao_ks())
                .add("password", baoCunBean.getMima_ks())
                .build();

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaidizhi_ks() + "/auth/login");
        final Request request = requestBuilder.build();

        Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        tishi.setText("验证图片质量出错");
                    }
                }, 0);
                Log.d("ffffffff", "登陆失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    String s = response.body().string();
                    //Log.d(TAG, "123   "+s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    int n = 1;
                    n = jsonObject.get("code").getAsInt();
                    if (n == 0) {
                        //登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
                        MyApplication.okHttpClient = okHttpClient;
                        link_P1(file);

                    } else {
                        mHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                tishi.setText("验证图片质量失败");
                            }
                        }, 0);
                    }
                } catch (Exception e) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            tishi.setText("验证图片异常");
                        }
                    }, 0);
                    Log.d("XinXiDialog", e.getMessage() + "");
                }

            }
        });
    }


    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.width = LayoutParams.MATCH_PARENT;
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
     *
     * @param listener
     */

    public void setOnQueRenListener(View.OnClickListener listener) {
        tijiao.setOnClickListener(listener);
    }

    //展会 Adapter
    public class ZhanHuiAdapter extends BaseAdapter {

        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
        private List<ZhanhuiBean.ObjectsBean> fuWuQiBeanList;


        /*构造函数*/
        private ZhanHuiAdapter(Context context, List<ZhanhuiBean.ObjectsBean> fuWuQiBeanList) {
            this.mInflater = LayoutInflater.from(context);
            this.fuWuQiBeanList=fuWuQiBeanList;

        }

        @Override
        public int getCount() {

            return fuWuQiBeanList.size();//返回数组的长度
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        /*书中详细解释该方法*/
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            //观察convertView随ListView滚动情况

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.chaxunitem666,null);
                ScreenAdapterTools.getInstance().loadView(convertView);
                ScreenAdapterTools.getInstance().loadView(convertView);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                holder.dianhua = (TextView) convertView.findViewById(R.id.dianhua);
                holder.gongsi = (TextView) convertView.findViewById(R.id.gongsi);
                holder.llbg = (LinearLayout) convertView.findViewById(R.id.ll_bg);
                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   // Log.d("ZhanHuiAdapter", "isChecked:" + isChecked+"  "+position);
                    zhanhuiLists.get(position).setCheck(isChecked);

                }
            });
            holder.dianhua.setText(fuWuQiBeanList.get(position).getSubConferenceName());
            holder.gongsi.setText(DateUtils.times(fuWuQiBeanList.get(position).getStartTime())+" 至 "+DateUtils.times(fuWuQiBeanList.get(position).getEndTime()));
            if (position%2==0){
                holder.llbg.setBackgroundColor(Color.WHITE);
            }else {
                holder.llbg.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
            if (fuWuQiBeanList.get(position).isCheck()){
                holder.checkBox.setChecked(true);
            }else {
                holder.checkBox.setChecked(false);
            }

            return convertView;
        }
        /*存放控件*/
        private class ViewHolder{
            private CheckBox checkBox;
            private LinearLayout llbg;
            private TextView dianhua,gongsi;


        }
    }


    private void baoming() {
        //拼接展会编码
        StringBuilder builder=null;
        if (zhanhuiLists.size()>0){
             builder=new StringBuilder();
            builder.append("");
            for (ZhanhuiBean.ObjectsBean bean:zhanhuiLists){
                if (bean.isCheck()){
                    builder.append(bean.getSubConferenceCode());
                    builder.append(",");
                }
            }

        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("name", name.getText().toString().trim()) //姓名
                .add("location", zuoweihao.getText().toString().trim()) //座位号
                .add("companyName", baoCunBean.getMoban() + "") //客户id
                .add("assemblyId", baoCunBean.getZhanhuiBianMa() == null ? "" : baoCunBean.getZhanhuiBianMa()) //展会编码
                .add("sexs", xingbie.getText().toString().trim()) //性别
                .add("contact", lianxiren.getText().toString().trim()) //联系人
                .add("department", renyuanleixing.getText().toString().trim()) //人员类型（部门）
                .add("title", zhiweizhiwu.getText().toString().trim()) //手机号
                .add("come_from", gongsimingcheng.getText().toString().trim()) //公司名称
                .add("interviewee", gongsidizhi.getText().toString().trim()) //公司地址
                .add("contactWay", lianxifangshi.getText().toString().trim()) //手机号
                .add("roomType", fangjianleixing.getText().toString().trim()) //房间类型
                .add("roomNumber", fangjianhao.getText().toString().trim()) //房间号
                .add("industry", hangyeleixing.getText().toString().trim()) //行业类型
                .add("phone", shoujihaoma.getText().toString().trim()) //手机号
                .add("channel", "2") //手机号
                .add("sourceMeeting", builder==null?"":builder.toString()) //展会编码
//                .add("phone", shoujihaoma.getText().toString().trim()) //手机号
                .add("photo_ids", zhaopianPath)
                .build();


        Request.Builder requestBuilder = new Request.Builder();
        // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/savePeople.do");
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
        // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        final Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ghhghghh", "保存失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("ghhghghh", "保存" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    qiandao(jsonObject.get("sid").getAsInt() + "");

                } catch (Exception e) {

                    Log.d("DetecterActivity", e.getMessage() + "保存异常");
                }

            }
        });

    }

    //修改
    private void xiugai() {
        //拼接展会编码
        StringBuilder builder=null;
        if (zhanhuiLists.size()>0){
            builder=new StringBuilder();
            builder.append("");
            for (ZhanhuiBean.ObjectsBean bean:zhanhuiLists){
                if (bean.isCheck()){
                    builder.append(bean.getSubConferenceCode());
                    builder.append(",");
                }
            }

        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        Log.d("XinXiDialog", baoCunBean.getZhanhuiBianMa() + "展会编码    ");

        RequestBody body = new FormBody.Builder()
                .add("id", objectsBean.getId() + "")
                .add("name", name.getText().toString().trim()) //姓名
                .add("location", zuoweihao.getText().toString().trim()) //座位号
                .add("companyName", objectsBean.getCompanyName()) //客户id
                .add("assemblyId", objectsBean.getAssemblyId()) //展会编码
                .add("sexs", xingbie.getText().toString().trim()) //性别
                .add("contact", lianxiren.getText().toString().trim()) //联系人
                .add("department", renyuanleixing.getText().toString().trim()) //人员类型（部门）
                .add("title", zhiweizhiwu.getText().toString().trim()) //手机号
                .add("come_from", gongsimingcheng.getText().toString().trim()) //公司名称
                .add("interviewee", gongsidizhi.getText().toString().trim()) //公司地址
                .add("contactWay", lianxifangshi.getText().toString().trim()) //手机号
                .add("roomType", fangjianleixing.getText().toString().trim()) //房间类型
                .add("roomNumber", fangjianhao.getText().toString().trim()) //房间号
                .add("industry", hangyeleixing.getText().toString().trim()) //行业类型
                .add("phone", shoujihaoma.getText().toString().trim()) //手机号
                .add("channel", objectsBean.getChannel() + "") //来源
                .add("jobStatus", objectsBean.getJobStatus() + "") //手机号
                .add("subject_type", objectsBean.getSubject_type() + "") //手机号
                .add("photo_ids", zhaopianPath)
                .add("sourceMeeting",builder==null?"":builder.toString())
                .build();


        Request.Builder requestBuilder = new Request.Builder();
        // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/savePeople.do");
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
        // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        final Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ghhghghh", "修改失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("ghhghghh", "修改" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    qiandao(jsonObject.get("sid").getAsInt() + "");

                } catch (Exception e) {

                    Log.d("DetecterActivity", e.getMessage() + "修改异常");
                }

            }
        });

    }

    //传旷视检测照片质量
    private void link_P1(final File file) {

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        ;
        MultipartBody mBody;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        builder.addFormDataPart("photo", file.getName(), fileBody1);
        //builder.addFormDataPart("subject_id","228");
        mBody = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
                .header("Content-Type", "application/json")
                .post(mBody)
                .url(baoCunBean.getHoutaidizhi_ks() + "/subject/photo");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        EventBus.getDefault().post("msr2");
                        tishi.setText("验证图片质量出错");
                    }
                }, 0);
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string();

                    Log.d("AllConnects", "传照片" + ss);
                    int ii = 0;
                    final JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    if (jsonObject.get("code").getAsInt() == 0) {
                        JsonObject jo = jsonObject.get("data").getAsJsonObject();
                        ii = jo.get("id").getAsInt();
                        if (ii != 0) {
                            mHandler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    tishi.setText("图片质量符合要求");
                                }
                            }, 0);
                            link_P2(file);

                        } else {
                            mHandler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    tishi.setText("请重拍\n图片不符合要求");
                                    EventBus.getDefault().post("msr2");
                                }
                            }, 0);
                        }
                    } else {
                        mHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                EventBus.getDefault().post("msr2");
                                tishi.setText(jsonObject.get("desc").getAsString());
                            }
                        }, 0);
                    }
                } catch (Exception e) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            EventBus.getDefault().post("msr2");
                            tishi.setText("验证图片质量异常");
                        }
                    }, 0);
                    Log.d("WebsocketPushMsg2", e.getMessage());
                }
            }
        });

    }


    //传瑞瞳后台
    private void link_P2(final File file) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        ;
        MultipartBody mBody;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        builder.addFormDataPart("voiceFile", file.getName(), fileBody1);
        //builder.addFormDataPart("subject_id","228");
        mBody = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
                .header("Content-Type", "application/json")
                .post(mBody)
                .url(baoCunBean.getHoutaiDiZhi() + "/AppFileUploadServlet?FilePathPath=compareFilePath&AllowFileType=.jpg,.gif,.jpeg,.bmp,.png&MaxFileSize=10");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                zhaopianPath = null;
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        tishi.setText("上传头像失败");
                    }
                }, 0);
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功" + call.request().toString() + file.delete());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string();

                    Log.d("AllConnects", "传照片到瑞瞳" + ss);
                    int ii = 0;
                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    zhaopianPath = jsonObject.get("exDesc").getAsString();

                } catch (Exception e) {
                    zhaopianPath = null;
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            tishi.setText("上传头像失败");
                        }
                    }, 0);
                    Log.d("WebsocketPushMsg2", e.getMessage());
                }
            }
        });

    }

    //签到
    private void qiandao(final String id) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("exhibitionId", baoCunBean.getZhanhuiId())
                .add("subjectId", id)
                .add("name", name.getText().toString().trim()) //姓名
                .add("location", zuoweihao.getText().toString().trim()) //座位号
                .add("sexs", xingbie.getText().toString().trim()) //性别
                .add("contact", lianxiren.getText().toString().trim()) //联系人
                .add("department", renyuanleixing.getText().toString().trim()) //人员类型（部门）
                .add("title", zhiweizhiwu.getText().toString().trim()) //手机号
                .add("come_from", gongsimingcheng.getText().toString().trim()) //公司名称
                .add("interviewee", gongsidizhi.getText().toString().trim()) //公司地址
                .add("contactWay", lianxifangshi.getText().toString().trim()) //手机号
                .add("roomType", fangjianleixing.getText().toString().trim()) //房间类型
                .add("roomNumber", fangjianhao.getText().toString().trim()) //房间号
                .add("industry", hangyeleixing.getText().toString().trim()) //行业类型
                .add("industry", hangyeleixing.getText().toString().trim()) //行业类型
                .add("channel", objectsBean==null?"2":objectsBean.getChannel()+"") //来源
                .add("jobStatus", objectsBean==null?"":objectsBean.getJobStatus() + "") //手机号
                .add("subject_type", objectsBean==null?"":objectsBean.getSubject_type() + "") //手机号
                .add("photo_ids", zhaopianPath == null ? "" : zhaopianPath)
                .add("phone", shoujihaoma.getText().toString().trim()) //手机号
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

                Log.d("gghhhh", "签到失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("xinxi", "签到" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    int n = 1;
                    n = jsonObject.get("dtoResult").getAsInt();
                    final String ppp = jsonObject.get("dtoDesc").getAsString();
                    if (n == 0) {
                        //登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
                        mHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    BaoCunId baoCunId=new BaoCunId();
                                    baoCunId.setId(Long.valueOf(id));
                                    baoCunIdDao.insert(baoCunId);
                                    Log.d("XinXiDialog", "插入保存ID成功");
                                }catch (Exception e){
                                    Log.d("XinXiDialog", e.getMessage()+"插入保存ID异常");
                                }

                                TastyToast.makeText(context, "签到成功", TastyToast.LENGTH_LONG, TastyToast.INFO).show();
                                EventBus.getDefault().post("guanbi");
                                XinXiDialog.this.dismiss();


                            }
                        }, 0);


                    } else {

                        mHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                TastyToast.makeText(context, ppp, TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                            }
                        }, 0);


                    }
                } catch (Exception e) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            TastyToast.makeText(context, "签到异常", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                        }
                    }, 0);
                    Log.d("DetecterActivity", e.getMessage() + "");
                }

            }
        });

    }


    //查询单个
    private void chaxunOne(String id) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
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
        // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/getAppSubject.do");
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
        // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("gghhhh", "查询单个失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("xinxi", "查询单个人" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    Gson gson = new Gson();
                    final ChaXunBeans nameBean = gson.fromJson(jsonObject, ChaXunBeans.class);

                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // 设置信息
                            objectsBean = null;
                            objectsBean = new NameBean.ObjectsBean();
                            objectsBean.setAssemblyId(nameBean.getAssemblyId());
                            objectsBean.setChannel(nameBean.getChannel());
                            objectsBean.setCome_from(nameBean.getCome_from());
                            objectsBean.setCompanyName(nameBean.getCompanyName());
                            objectsBean.setContact(nameBean.getContact());
                            objectsBean.setContactWay(nameBean.getContactWay());
                            objectsBean.setDepartment(nameBean.getDepartment());
                            objectsBean.setId(nameBean.getId());
                            objectsBean.setIndustry(nameBean.getIndustry());
                            objectsBean.setInterviewee(nameBean.getInterviewee());
                            objectsBean.setJobStatus(nameBean.getJobStatus());
                            objectsBean.setLocation(nameBean.getLocation());
                            objectsBean.setMeetingId(nameBean.getMeetingId());
                            objectsBean.setName(nameBean.getName());
                            objectsBean.setPhoto_ids(nameBean.getPhoto_ids());
                            objectsBean.setRoomNumber(nameBean.getRoomNumber());
                            objectsBean.setRoomType(nameBean.getRoomType());
                            objectsBean.setPhone(nameBean.getPhone());
                            objectsBean.setSexs(nameBean.getSexs());
                            objectsBean.setSubject_type(nameBean.getSubject_type());
                            objectsBean.setTitle(nameBean.getTitle());
                            objectsBean.setStatus(nameBean.getStatus());

                            ////////////////////////////////////

                            if (objectsBean.getPhoto_ids() != null && !objectsBean.getPhoto_ids().equals(""))
                                zhaopianPath = objectsBean.getPhoto_ids();
                            name.setText(objectsBean.getName());
                            xingbie.setText(objectsBean.getSexs());
                            renyuanleixing.setText(objectsBean.getDepartment());
                            shoujihaoma.setText(objectsBean.getPhone());
                            zhiweizhiwu.setText(objectsBean.getTitle());
                            gongsimingcheng.setText(objectsBean.getCome_from());
                            gongsidizhi.setText(objectsBean.getInterviewee());
                            lianxiren.setText(objectsBean.getContact());
                            lianxifangshi.setText(objectsBean.getContactWay());
                            fangjianleixing.setText(objectsBean.getRoomType());
                            fangjianhao.setText(objectsBean.getRoomNumber());
                            zuoweihao.setText(objectsBean.getLocation());
                            hangyeleixing.setText(objectsBean.getIdentity());

                        }
                    }, 0);

                } catch (Exception e) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            TastyToast.makeText(context, "查询个人信息异常", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                        }
                    }, 0);
                    Log.d("DetecterActivity", e.getMessage() + "");
                }

            }
        });

    }

    //查询单个
    private void chaxunFHY() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

//        RequestBody body = new FormBody.Builder()
//                .add("id", id)
//                .build();

        Request.Builder requestBuilder = new Request.Builder();
        // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.get();
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/querySubConferenceApp.do?exhibitionId="+baoCunBean.getZhanhuiId());
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
        // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("gghhhh", "查询分会议失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("xinxi", "查询分会议" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    Gson gson = new Gson();
                    final ZhanhuiBean nameBean = gson.fromJson(jsonObject, ZhanhuiBean.class);

                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // 设置信息
                            if (zhanhuiLists.size()>0)
                                zhanhuiLists.clear();


                            zhanhuiLists.addAll(nameBean.getObjects());
                            zhanHuiAdapter.notifyDataSetChanged();


                        }
                    }, 0);

                } catch (Exception e) {
                    mHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            TastyToast.makeText(context, "查询展会列表异常", TastyToast.LENGTH_LONG, TastyToast.INFO).show();

                        }
                    }, 0);
                    Log.d("DetecterActivity", e.getMessage() + "");
                }

            }
        });

    }


}
