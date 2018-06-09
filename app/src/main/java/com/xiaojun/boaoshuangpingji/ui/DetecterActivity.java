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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.xiaojun.boaoshuangpingji.R;
import com.xiaojun.boaoshuangpingji.utils.CustomerEngine;

import com.xiaojun.boaoshuangpingji.beans.MenBean;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;

import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.xiaojun.boaoshuangpingji.utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;


import java.util.Date;

import java.util.Vector;


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

/**
 * Created by gqj3375 on 2017/4/28.
 */

public class DetecterActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();
	private TextView shezhi;
	private int mWidth, mHeight, mFormat;
	private OkHttpClient okHttpClient=null;

	private static Vector<Bitmap> bitmapList=new Vector<>();

	private final int TIMEOUT=1000*30;
	private  String screen_token=null;



	public  Handler handler=new Handler(new Handler.Callback() {

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

		setContentView(R.layout.activity_camera);
		shezhi=findViewById(R.id.shezhi);
		shezhi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(DetecterActivity.this,SheZhiActivity.class));
			}
		});


		CustomerEngine.getInstance(getApplicationContext(),DetecterActivity.this);
	}



	@Override
	protected void onStop() {

		super.onStop();

	}



	@Override
	protected void onDestroy() {

		super.onDestroy();

	}




	//首先登录-->获取所有主机-->创建或者删除或者更新门禁
	private void getOkHttpClient2(){
		okHttpClient = new OkHttpClient.Builder()
				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
				.cookieJar(new CookiesManager())
				.retryOnConnectionFailure(true)
				.build();

		RequestBody body = new FormBody.Builder()
				.add("username", "test@megvii.com")
				.add("password", "123456")
                .add("pad_id", Utils.getIMSI())
                .add("device_type", "2")
				.build();

		Request.Builder requestBuilder = new Request.Builder();
		requestBuilder.header("User-Agent", "Koala Admin");
		requestBuilder.header("Content-Type","application/json");
		requestBuilder.post(body);
		requestBuilder.url("http://192.168.2.64"+"/pad/login");
		final Request request = requestBuilder.build();

		Call mcall= okHttpClient.newCall(request);
		mcall.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.d(TAG, "登陆失败"+e.getMessage());
			}
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String s=response.body().string();
				Log.d(TAG, "登录"+s);
				JsonObject jsonObject= GsonUtil.parse(s).getAsJsonObject();
				int n=1;
				n=jsonObject.get("code").getAsInt();
				if (n==0){
					//登录成功,后续的连接操作因为cookies 原因,要用 MyApplication.okHttpClient
					JsonObject jo=jsonObject.get("data").getAsJsonObject();
					screen_token=jo.get("screen_token").getAsString();
					Log.d("DetecterActivity", screen_token);
				}
				else {

				}
			}
		});

	}


	public static final int TIMEOUT2 = 1000 * 5;
	// 1:N 对比
	private void link_P2(final File file, final int size) {
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

		RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"),file);

		builder.addFormDataPart("image",file.getName(), fileBody1);
		builder.addFormDataPart("screen_token",screen_token==null?"":screen_token);
		mBody = builder.build();

		Request.Builder requestBuilder = new Request.Builder()
				.header("Content-Type", "application/json")
				.post(mBody)
				.url("http://192.168.2.64"+ ":8866/recognize");

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

				Log.d("AllConnects", "请求识别成功" + call.request().toString()+file.delete());
				//获得返回体
				try {
					ResponseBody body = response.body();
					String ss = body.string();
					Log.d("AllConnects", "传照片" + ss);
					String s2=ss.replace("\\\\u","@!@#u").replace("\\","")
							.replace("tag\": \"{","tag\":{")
							.replace("jpg\"}\"","jpg\"}")
							.replace("@!@#","\\");


					Log.d("AllConnects", "传照片2" + s2);

					JsonObject jsonObject = GsonUtil.parse(s2).getAsJsonObject();
					Gson gson = new Gson();
					MenBean menBean = gson.fromJson(jsonObject, MenBean.class);
					if (menBean.isCan_door_open()){

						Message message=Message.obtain();
						message.arg1=1;
						message.obj=menBean;
						handler.sendMessage(message);
					}

					Log.d("DetecterActivity", "menBean:" + menBean.isCan_door_open());

				} catch (Exception e) {
					Log.d("WebsocketPushMsg", e.getMessage()+"");
				}finally {
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
		File file = new File(Environment.getExternalStorageDirectory(),filename+".png");
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
}
