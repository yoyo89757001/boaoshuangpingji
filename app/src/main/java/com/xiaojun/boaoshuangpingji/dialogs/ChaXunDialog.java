package com.xiaojun.boaoshuangpingji.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import com.xiaojun.boaoshuangpingji.MyApplication;
import com.xiaojun.boaoshuangpingji.R;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;
import com.xiaojun.boaoshuangpingji.beans.NameBean;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;
import com.xiaojun.boaoshuangpingji.ui.DetecterActivity;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class ChaXunDialog extends Dialog  {
    private Button tijiao,guanbi;
    private EditText editText;
    private ListView listView;
    private Context context;
    private List<NameBean.ObjectsBean> stringList=new ArrayList<>();
    private final int TIMEOUT = 1000 * 30;
    private OkHttpClient okHttpClient = null;
    private BaoCunBeanDao baoCunBeanDao= MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
    private BaoCunBean baoCunBean=null;
    private PopupWindowAdapter3 adapter3=null;



    public ChaXunDialog(Context context) {
        super(context, R.style.dialog_style2);
        Window window =  this.getWindow();
        if ( window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = LayoutParams.WRAP_CONTENT;
                attr.width = LayoutParams.WRAP_CONTENT;
                attr.gravity = Gravity.CENTER;//设置dialog 在布局中的位置
            }
        }
        this.context=context;
        baoCunBean=baoCunBeanDao.load(123456L);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.chaxunitem, null);
        ScreenAdapterTools.getInstance().loadView(mView);
        tijiao=mView.findViewById(R.id.baoming);
        guanbi=mView.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChaXunDialog.this.dismiss();
            }
        });
        editText=mView.findViewById(R.id.editview);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(""))
                    chaxun(s);
                else {
                    if (stringList.size()>0)
                        stringList.clear();
                    adapter3.notifyDataSetChanged();

                }
            }
        });
        listView=mView.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XinXiDialog dialog=new XinXiDialog(context);
                dialog.show();

            }
        });
        adapter3=new PopupWindowAdapter3(context,stringList);
        listView.setAdapter(adapter3);


        super.setContentView(mView);
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


    public class PopupWindowAdapter3 extends BaseAdapter {

        private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
        private List<NameBean.ObjectsBean> fuWuQiBeanList;


        /*构造函数*/
        private PopupWindowAdapter3(Context context, List<NameBean.ObjectsBean> fuWuQiBeanList) {
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
                convertView = mInflater.inflate(R.layout.chaxunitem2222,null);
                ScreenAdapterTools.getInstance().loadView(convertView);
                holder = new ViewHolder();
                    /*得到各个控件的对象*/
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.dianhua = (TextView) convertView.findViewById(R.id.dianhua);
                holder.gongsi = (TextView) convertView.findViewById(R.id.gongsi);

                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.name.setText(fuWuQiBeanList.get(position).getName());
            holder.gongsi.setText(fuWuQiBeanList.get(position).getCome_from());
            holder.dianhua.setText(fuWuQiBeanList.get(position).getPhone());

            return convertView;
        }
        /*存放控件*/
        private class ViewHolder{
            private TextView name,dianhua,gongsi;


        }
    }



    private void chaxun(CharSequence s) {
        Log.d("ChaXunDialog", s.toString()+"yyyyyyyy");

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();

        RequestBody body = new FormBody.Builder()
                .add("phone", s.toString()) //客户id
                .add("companyName", baoCunBean.getMoban()+"") //客户id
                .add("assemblyId", baoCunBean.getZhanhuiId()) //展会id
                .add("pageNum", "1") //职位
                .add("pageSize", "200") //电话
                .build();

        Request.Builder requestBuilder = new Request.Builder();
        // requestBuilder.header("User-Agent", "Koala Admin");
        requestBuilder.header("Content-Type", "application/json");
        requestBuilder.post(body);
        requestBuilder.url(baoCunBean.getHoutaiDiZhi() + "/findPeople.do");
        //Log.d("DetecterActivity", baoCunBean.getHoutaiDiZhi()+"地址");
        // Log.d("DetecterActivity", baoCunBean.getZhanhuiId()+"展会id");
        final Request request = requestBuilder.build();

        final Call mcall = okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.d("ghhghghh", "查询失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String s = response.body().string();
                    Log.d("ghhghghh", "查询" + s);
                    JsonObject jsonObject = GsonUtil.parse(s).getAsJsonObject();
                    Gson gson=new Gson();
                    NameBean nameBean=gson.fromJson(jsonObject,NameBean.class);
                    if (nameBean.getObjects().size()>0){
                        Message message=Message.obtain();
                        message.what=1;
                        message.obj=nameBean;
                        handler.sendMessage(message);
                    }else {
                        Message message=Message.obtain();
                        message.what=2;
                        handler.sendMessage(message);
                    }

                }catch (Exception e){

                    Log.d("DetecterActivity", e.getMessage()+"查询异常");
                }

            }
        });

    }

   private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1){
                NameBean beans= (NameBean) msg.obj;
                if (stringList.size()>0)
                    stringList.clear();
                stringList.addAll(beans.getObjects());
                adapter3.notifyDataSetChanged();

            }else if (msg.what==2){
                if (stringList.size()>0)
                    stringList.clear();
                adapter3.notifyDataSetChanged();
            }


            return false;
        }
    });


}
