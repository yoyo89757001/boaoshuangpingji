package com.xiaojun.boaoshuangpingji.ui;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arcsoft.ageestimation.ASAE_FSDKAge;
import com.arcsoft.ageestimation.ASAE_FSDKEngine;
import com.arcsoft.ageestimation.ASAE_FSDKError;
import com.arcsoft.ageestimation.ASAE_FSDKFace;
import com.arcsoft.ageestimation.ASAE_FSDKVersion;
import com.arcsoft.facerecognition.AFR_FSDKEngine;
import com.arcsoft.facerecognition.AFR_FSDKError;
import com.arcsoft.facerecognition.AFR_FSDKFace;
import com.arcsoft.facerecognition.AFR_FSDKVersion;
import com.arcsoft.facetracking.AFT_FSDKEngine;
import com.arcsoft.facetracking.AFT_FSDKError;
import com.arcsoft.facetracking.AFT_FSDKFace;
import com.arcsoft.facetracking.AFT_FSDKVersion;
import com.arcsoft.genderestimation.ASGE_FSDKEngine;
import com.arcsoft.genderestimation.ASGE_FSDKError;
import com.arcsoft.genderestimation.ASGE_FSDKFace;
import com.arcsoft.genderestimation.ASGE_FSDKGender;
import com.arcsoft.genderestimation.ASGE_FSDKVersion;
import com.bumptech.glide.Glide;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.guo.android_extend.java.AbsLoop;
import com.guo.android_extend.java.ExtByteArrayOutputStream;
import com.guo.android_extend.tools.CameraHelper;
import com.guo.android_extend.widget.CameraFrameData;
import com.guo.android_extend.widget.CameraGLSurfaceView;
import com.guo.android_extend.widget.CameraSurfaceView;
import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;
import com.hanks.htextview.typer.TyperTextView;
import com.xiaojun.boaoshuangpingji.MyApplication;
import com.xiaojun.boaoshuangpingji.R;

import com.xiaojun.boaoshuangpingji.beans.BaoCunBean;
import com.xiaojun.boaoshuangpingji.beans.BaoCunBeanDao;
import com.xiaojun.boaoshuangpingji.beans.BitmapsBean;
import com.xiaojun.boaoshuangpingji.beans.MenBean;
import com.xiaojun.boaoshuangpingji.cookies.CookiesManager;
import com.xiaojun.boaoshuangpingji.interfaces.RecytviewCash;
import com.xiaojun.boaoshuangpingji.utils.FaceDB;
import com.xiaojun.boaoshuangpingji.utils.FileUtil;
import com.xiaojun.boaoshuangpingji.utils.GlideCircleTransform;
import com.xiaojun.boaoshuangpingji.utils.GsonUtil;
import com.xiaojun.boaoshuangpingji.utils.Utils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
 * Created by Administrator on 2018/6/8.
 */

public class CustomerDisplay extends Presentation implements CameraSurfaceView.OnCameraListener, View.OnTouchListener, Camera.AutoFocusCallback , RecytviewCash {
    private Context mContext;
    private Activity activity;
   // private TextView tv;
    private final String TAG = this.getClass().getSimpleName();

    private int mWidth, mHeight, mFormat;
    private CameraSurfaceView mSurfaceView;
    private CameraGLSurfaceView mGLSurfaceView;
    private Camera mCamera;

    AFT_FSDKVersion version = new AFT_FSDKVersion();
    AFT_FSDKEngine engine = new AFT_FSDKEngine();
    ASAE_FSDKVersion mAgeVersion = new ASAE_FSDKVersion();
    ASAE_FSDKEngine mAgeEngine = new ASAE_FSDKEngine();
    ASGE_FSDKVersion mGenderVersion = new ASGE_FSDKVersion();
    ASGE_FSDKEngine mGenderEngine = new ASGE_FSDKEngine();
    List<AFT_FSDKFace> result = new ArrayList<>();
    List<AFT_FSDKFace> result2 = new ArrayList<>();
    List<ASAE_FSDKAge> ages = new ArrayList<>();
    List<ASGE_FSDKGender> genders = new ArrayList<>();

    private int dw,dh;
    private OkHttpClient okHttpClient=null;
    int mCameraID;
    int mCameraRotate;
    boolean mCameraMirror;
    byte[] mImageNV21 = null;
    FRAbsLoop mFRAbsLoop = null;
    AFT_FSDKFace mAFT_FSDKFace = null;
    Handler mHandler;

    private BlockingQueue<String> basket = new LinkedBlockingQueue<String>(5);
    private static Vector<MenBean> menBeansList=new Vector<>();
    private static boolean isA=true;
    private static final String syString="kkkkk";


    private static Vector<Bitmap> bitmapList=new Vector<>();

    private final int TIMEOUT=1000*30;
    private  String screen_token=null;
    private LinearLayout linearLayout;
    private HorizontalScrollView scrollView;
    private BaoCunBeanDao baoCunBeanDao=MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
    private BaoCunBean baoCunBean=null;

    public  Handler handler=new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(final Message msg) {
            switch (msg.what) {

                case 999:

                    if (menBeansList.size()>0){
                        linearLayout.removeViewAt(0);
                        menBeansList.remove(0);
                    }

                    break;
            }

            if (msg.arg1==1) {
                MenBean dataBean = (MenBean) msg.obj;
                try {

                    switch (dataBean.getPerson().getTag().getSubject_type()) {
                        case 0: //员工
                            //Log.d(TAG, "员工k");
                            int a = 0;
                            for (int i2 = 0; i2 < menBeansList.size(); i2++) {
                                if (Objects.equals(menBeansList.get(i2).getPerson().getTag().getId(), dataBean.getPerson().getTag().getId())) {
                                    a = 1;
                                }
                            }

                            if (a == 0) {
                                //推送到主屏
                                EventBus.getDefault().post(dataBean);

                                menBeansList.add(dataBean);
                               // int i1 = menBeansList.size();
                                final View view3 = View.inflate(mContext, R.layout.tanchuang_item213, null);
                                ScreenAdapterTools.getInstance().loadView(view3);
                                TextView name3 = (TextView) view3.findViewById(R.id.name);
                                ImageView touxiang = (ImageView) view3.findViewById(R.id.touxiang);
                                name3.setText("测试的");
                                TextView zhiwei = (TextView) view3.findViewById(R.id.zhiwei);
                                zhiwei.setText("职位");
                                TyperTextView huanyinyu = (TyperTextView) view3.findViewById(R.id.typerTextView);
                                huanyinyu.setTyperSpeed(100);
                                huanyinyu.setCharIncrease(1);
                                huanyinyu.setAnimationListener(new AnimationListener() {
                                    @Override
                                    public void onAnimationEnd(HTextView hTextView) {


                                    }
                                });
                              //  huanyinyu.setText();
                                huanyinyu.animateText("dfa法防是非得失发的说");

                                //huanyinyu.setText(hyy);
                                //synthesizer.speak(hyy==null?"":hyy);

                                Glide.with(mContext)
                                        //	.load(R.drawable.vvv)
                                        .load(baoCunBean.getHoutaidizhi_ks()+dataBean.getPerson().getTag().getAvatar())
                                        .error(R.drawable.erroy_bg)
                                        //.apply(myOptions)
                                      //  .transform(new GlideRoundTransform(MyApplication.getAppContext(), 20))
                                        .transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
                                        .into(touxiang);

                                linearLayout.addView(view3);


                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.fullScroll(ScrollView.FOCUS_RIGHT);
                                    }
                                });

                                //动画
                                SpringSystem springSystem3 = SpringSystem.create();
                                final Spring spring3 = springSystem3.createSpring();
                                //两个参数分别是弹力系数和阻力系数
                                spring3.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(90, 8));
                                // 添加弹簧监听器
                                spring3.addListener(new SimpleSpringListener() {
                                    @Override
                                    public void onSpringUpdate(Spring spring) {
                                        // value是一个符合弹力变化的一个数，我们根据value可以做出弹簧动画
                                        float value = (float) spring.getCurrentValue();
                                        //Log.d(TAG, "value:" + value);
                                        //基于Y轴的弹簧阻尼动画
                                        //	helper.itemView.setTranslationY(value);
                                        // 对图片的伸缩动画
                                        //float scale = 1f - (value * 0.5f);
                                        view3.setScaleX(value);
                                        view3.setScaleY(value);
                                    }
                                });
                                // 设置动画结束值
                                spring3.setEndValue(1f);

//                                adapter.notifyItemInserted(i1);
//                                manager2.scrollToPosition(i1 - 1);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {

                                            SystemClock.sleep(8000);
                                            Message message = Message.obtain();
                                            message.what = 999;
                                            handler.sendMessage(message);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }).start();

                            }
                            break;



                    }
                } catch (Exception e) {
                    //Log.d("WebsocketPushMsg", e.getMessage());
                    e.printStackTrace();
                }

            }

            return false;
        }
    });

    @Override
    public void reset() {

    }

    class FRAbsLoop extends AbsLoop {

        AFR_FSDKVersion version = new AFR_FSDKVersion();
        AFR_FSDKEngine engine = new AFR_FSDKEngine();
        AFR_FSDKFace result = new AFR_FSDKFace();
        List<FaceDB.FaceRegist> mResgist = ((MyApplication)mContext.getApplicationContext()).mFaceDB.mRegister;
        List<ASAE_FSDKFace> face1 = new ArrayList<>();
        List<ASGE_FSDKFace> face2 = new ArrayList<>();

        @Override
        public void setup() {
            AFR_FSDKError error = engine.AFR_FSDK_InitialEngine(FaceDB.appid, FaceDB.fr_key);
            Log.d(TAG, "AFR_FSDK_InitialEngine = " + error.getCode());
            error = engine.AFR_FSDK_GetVersion(version);
            Log.d(TAG, "FR=" + version.toString() + "," + error.getCode()); //(210, 178 - 478, 446), degree = 1　780, 2208 - 1942, 3370
        }

        @Override
        public void loop() {

//			if (mImageNV21 != null) {
//				long time = System.currentTimeMillis();
//				AFR_FSDKError error = engine.AFR_FSDK_ExtractFRFeature(mImageNV21, mWidth, mHeight, AFR_FSDKEngine.CP_PAF_NV21, mAFT_FSDKFace.getRect(), mAFT_FSDKFace.getDegree(), result);
//				Log.d(TAG, "AFR_FSDK_ExtractFRFeature cost :" + (System.currentTimeMillis() - time) + "ms");
//				Log.d(TAG, "Face=" + result.getFeatureData()[0] + "," + result.getFeatureData()[1] + "," + result.getFeatureData()[2] + "," + error.getCode());
//				AFR_FSDKMatching score = new AFR_FSDKMatching();
//				float max = 0.0f;
//				String name = null;
//				for (FaceDB.FaceRegist fr : mResgist) {
//					for (AFR_FSDKFace face : fr.mFaceList) {
//						error = engine.AFR_FSDK_FacePairMatching(result, face, score);
//						Log.d(TAG,  "Score:" + score.getScore() + ", AFR_FSDK_FacePairMatching=" + error.getCode());
//						if (max < score.getScore()) {
//							max = score.getScore();
//							name = fr.mName;
//						}
//					}
//				}
//				//age & gender
//				face1.clear();
//				face2.clear();
//				face1.add(new ASAE_FSDKFace(mAFT_FSDKFace.getRect(), mAFT_FSDKFace.getDegree()));
//				face2.add(new ASGE_FSDKFace(mAFT_FSDKFace.getRect(), mAFT_FSDKFace.getDegree()));
//				ASAE_FSDKError error1 = mAgeEngine.ASAE_FSDK_AgeEstimation_Image(mImageNV21, mWidth, mHeight, AFT_FSDKEngine.CP_PAF_NV21, face1, ages);
//				ASGE_FSDKError error2 = mGenderEngine.ASGE_FSDK_GenderEstimation_Image(mImageNV21, mWidth, mHeight, AFT_FSDKEngine.CP_PAF_NV21, face2, genders);
//				Log.d(TAG, "ASAE_FSDK_AgeEstimation_Image:" + error1.getCode() + ",ASGE_FSDK_GenderEstimation_Image:" + error2.getCode());
//				Log.d(TAG, "age:" + ages.get(0).getAge() + ",gender:" + genders.get(0).getGender());
//				final String age = ages.get(0).getAge() == 0 ? "年龄未知" : ages.get(0).getAge() + "岁";
//				final String gender = genders.get(0).getGender() == -1 ? "性别未知" : (genders.get(0).getGender() == 0 ? "男" : "女");
//
//				//crop
//				byte[] data = mImageNV21;
//				YuvImage yuv = new YuvImage(data, ImageFormat.NV21, mWidth, mHeight, null);
//				ExtByteArrayOutputStream ops = new ExtByteArrayOutputStream();
//				yuv.compressToJpeg(mAFT_FSDKFace.getRect(), 100, ops);
//				final Bitmap bmp = BitmapFactory.decodeByteArray(ops.getByteArray(), 0, ops.getByteArray().length);
//				try {
//					ops.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//				if (max > 0.6f) {
//					//fr success.
//					final float max_score = max;
//					Log.d(TAG, "fit Score:" + max + ", NAME:" + name);
//					final String mNameShow = name;
//					mHandler.removeCallbacks(hide);
//					mHandler.post(new Runnable() {
//						@Override
//						public void run() {
//
//							mTextView.setAlpha(1.0f);
//							mTextView.setText(mNameShow);
//							mTextView.setTextColor(Color.RED);
//							mTextView1.setVisibility(View.VISIBLE);
//							mTextView1.setText("置信度：" + (float)((int)(max_score * 1000)) / 1000.0);
//							mTextView1.setTextColor(Color.RED);
//							mImageView.setRotation(mCameraRotate);
//							if (mCameraMirror) {
//								mImageView.setScaleY(-1);
//							}
//							mImageView.setImageAlpha(255);
//							mImageView.setImageBitmap(bmp);
//						}
//					});
//				} else {
//					final String mNameShow = "未识别";
//					DetecterActivity.this.runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							mTextView.setAlpha(1.0f);
//							mTextView1.setVisibility(View.VISIBLE);
//							mTextView1.setText( gender + "," + age);
//							mTextView1.setTextColor(Color.RED);
//							mTextView.setText(mNameShow);
//							mTextView.setTextColor(Color.RED);
//							mImageView.setImageAlpha(255);
//							mImageView.setRotation(mCameraRotate);
//							if (mCameraMirror) {
//								mImageView.setScaleY(-1);
//							}
//							mImageView.setImageBitmap(bmp);
//						}
//					});
//				}
//				mImageNV21 = null;
//			}

        }

        @Override
        public void over() {
            AFR_FSDKError error = engine.AFR_FSDK_UninitialEngine();
            Log.d(TAG, "AFR_FSDK_UninitialEngine : " + error.getCode());
        }
    }



    public CustomerDisplay(Context outerContext, Display display,Activity activity) {
        super(outerContext,display);
        mContext = outerContext;
        this.activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        baoCunBean= baoCunBeanDao.load(123456L);
        if (baoCunBean!=null){
            screen_token= baoCunBean.getScreen_token();
        }
        mCameraID =  Camera.CameraInfo.CAMERA_FACING_BACK ;
        mCameraRotate =  90 ;
        mCameraMirror =  false;

        mFormat = ImageFormat.NV21;
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                return false;
            }
        });

        dw = Utils.getDisplaySize(mContext).x;
        dh = Utils.getDisplaySize(mContext).y;

        setContentView(R.layout.view_display_customer);
        //ScreenAdapterTools.getInstance().reset(this);//如果希望android7.0分屏也适配的话,加上这句
        //在setContentView();后面加上适配语句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        linearLayout=findViewById(R.id.linearLayout);
        scrollView=findViewById(R.id.scrollView);
        mGLSurfaceView = (CameraGLSurfaceView) findViewById(R.id.glsurfaceView);
        mGLSurfaceView.setOnTouchListener(this);
        mSurfaceView = (CameraSurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.setOnCameraListener(this);
        mSurfaceView.debug_print_fps(false, false);

        AFT_FSDKError err = engine.AFT_FSDK_InitialFaceEngine(FaceDB.appid, FaceDB.ft_key, AFT_FSDKEngine.AFT_OPF_0_HIGHER_EXT, 16, 5);
        Log.d(TAG, "AFT_FSDK_InitialFaceEngine =" + err.getCode());
        err = engine.AFT_FSDK_GetVersion(version);
        Log.d(TAG, "AFT_FSDK_GetVersion:" + version.toString() + "," + err.getCode());

        ASAE_FSDKError error = mAgeEngine.ASAE_FSDK_InitAgeEngine(FaceDB.appid, FaceDB.age_key);
        Log.d(TAG, "ASAE_FSDK_InitAgeEngine =" + error.getCode());
        error = mAgeEngine.ASAE_FSDK_GetVersion(mAgeVersion);
        Log.d(TAG, "ASAE_FSDK_GetVersion:" + mAgeVersion.toString() + "," + error.getCode());

        ASGE_FSDKError error1 = mGenderEngine.ASGE_FSDK_InitgGenderEngine(FaceDB.appid, FaceDB.gender_key);
        Log.d(TAG, "ASGE_FSDK_InitgGenderEngine =" + error1.getCode());
        error1 = mGenderEngine.ASGE_FSDK_GetVersion(mGenderVersion);
        Log.d(TAG, "ASGE_FSDK_GetVersion:" + mGenderVersion.toString() + "," + error1.getCode());

//        RelativeLayout.LayoutParams  params1= (RelativeLayout.LayoutParams) recyclerView2.getLayoutParams();
//        params1.height=dh*2/3;
//        recyclerView2.setLayoutParams(params1);
//        recyclerView2.invalidate();

        mFRAbsLoop = new FRAbsLoop();
        mFRAbsLoop.start();


//        List<String> xmls=new ArrayList<>();
//        final List<String> xmlList= FileUtil.getAllFileXml(Environment.getExternalStorageDirectory().getAbsolutePath(),xmls);
//        if (xmlList.size()>0){
//            for (String s:xmlList){
//                File f=new File(s);
//                Log.d("CustomerDisplay", "f.delete():" + f.delete());
//
//            }
//        }
    }





    @Override
    protected void onStop() {
        isA=true;
        super.onStop();

    }

    @Override
    public void onDisplayRemoved() {
        super.onDisplayRemoved();
    }



    @Override
    public Camera setupCamera() {
        try {
            mCamera = Camera.open(0);
            //int rotateDegree = getPreviewRotateDegree(1);
            //mCamera.setDisplayOrientation(rotateDegree);
            mSurfaceView.setupGLSurafceView(mGLSurfaceView, true, mCameraMirror, 0);

            int PreviewWidth = 0;
            int PreviewHeight = 0;
            Camera.Parameters parameters  = mCamera.getParameters();
            // 选择合适的预览尺寸
            List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
            // 如果sizeList只有一个我们也没有必要做什么了，因为就他一个别无选择
            if (sizeList.size() > 1) {
                for (Camera.Size cur : sizeList) {
                    if (cur.width >= PreviewWidth
                            && cur.height >= PreviewHeight) {
                        PreviewWidth = cur.width;
                        PreviewHeight = cur.height;
                        break;
                    }
                }
            }
            parameters.setPreviewSize(PreviewWidth, PreviewHeight); //获得摄像区域的大小
            parameters.setPictureSize(PreviewWidth, PreviewHeight);//设置拍出来的屏幕大小
            //parameters.setPreviewSize(mWidth, mHeight);
            parameters.setPreviewFormat(mFormat);

            for( Camera.Size size : parameters.getSupportedPreviewSizes()) {
                Log.d(TAG, "SIZE:" + size.width + "x" + size.height);
            }
            for( Integer format : parameters.getSupportedPreviewFormats()) {
                Log.d(TAG, "FORMAT:" + format);
            }

            List<int[]> fps = parameters.getSupportedPreviewFpsRange();
            for(int[] count : fps) {
                Log.d(TAG, "T:");
                for (int data : count) {
                    Log.d(TAG, "V=" + data);
                }
            }


            //parameters.setPreviewFpsRange(15000, 30000);
            //parameters.setExposureCompensation(parameters.getMaxExposureCompensation());
            //parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
            //parameters.setAntibanding(Camera.Parameters.ANTIBANDING_AUTO);
            //parmeters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            //parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
            //parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);

            mCamera.setParameters(parameters);


        } catch (Exception e) {
            Log.d("fffffffff", e.getMessage()+"fff");
            if (mCamera!=null)
                mCamera.release();
            SystemClock.sleep(200);

            mCamera = Camera.open(0);
            int rotateDegree = getPreviewRotateDegree(0);
            mCamera.setDisplayOrientation(rotateDegree);
            mSurfaceView.setupGLSurafceView(mGLSurfaceView, true, mCameraMirror, getPreviewRotateDegree(0));

        }
        if (mCamera != null) {
            mWidth = mCamera.getParameters().getPreviewSize().width;
            mHeight = mCamera.getParameters().getPreviewSize().height;
        }
        return mCamera;
    }



    private int getPreviewRotateDegree(int p) {
        int phoneDegree = 0;
        int result = 0;
        //获得手机方向
        int phoneRotate = activity.getWindowManager().getDefaultDisplay().getOrientation();
        //得到手机的角度
        switch (phoneRotate) {
            case Surface.ROTATION_0:
                phoneDegree = 0;
                break;        //0
            case Surface.ROTATION_90:
                phoneDegree = 90;
                break;      //90
            case Surface.ROTATION_180:
                phoneDegree = 180;
                break;    //180
            case Surface.ROTATION_270:
                phoneDegree = 270;
                break;    //270
        }
        //分别计算前后置摄像头需要旋转的角度
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        if (p == 1) {
            Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_FRONT, cameraInfo);
            result = (cameraInfo.orientation + phoneDegree) % 360;
            result = (360 - result) % 360;
        } else {
            Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
            result = (cameraInfo.orientation - phoneDegree + 360) % 360;
        }
        return result;
    }


    @Override
    public void setupChanged(int format, int width, int height) {
        RelativeLayout.LayoutParams relativeLayout= (RelativeLayout.LayoutParams) mGLSurfaceView.getLayoutParams();
        //relativeLayout.topMargin=dh/3+50;
        relativeLayout.height=dh/3;
        relativeLayout.width=dh/3;
        mGLSurfaceView.setLayoutParams(relativeLayout);
        mGLSurfaceView.invalidate();
        Log.d("fffffffff", "fffffffffffff");
    }

    @Override
    public boolean startPreviewLater() {

        return false;
    }

    @Override
    public Object onPreview(final byte[] data, final int width, final int height, int format, long timestamp) {
//		Log.d("DetecterActivity", "width:" + width);
//		Log.d("DetecterActivity", "height:" + height);
        //	Log.d(TAG, "AFT_FSDK_FaceFeatureDetect =" + err.getCode());
        //	Log.d(TAG, "Face=" + result.size());
//		for (AFT_FSDKFace face : result) {
//			Log.d(TAG, "Face:" + face.toString());
//		}

        AFT_FSDKError err = engine.AFT_FSDK_FaceFeatureDetect(data, width, height, AFT_FSDKEngine.CP_PAF_NV21, result);
        if (isA) {
            isA=false;
            final int size=result.size();
            if (!result.isEmpty()) {
                if (bitmapList.size()>0){
                    bitmapList.clear();
                }
                try {
                for (AFT_FSDKFace fsdkFace : result){
                    YuvImage yuv = new YuvImage(data, ImageFormat.NV21, mWidth, mHeight, null);
                    ExtByteArrayOutputStream ops = new ExtByteArrayOutputStream();
                    Rect rect=fsdkFace.getRect();
                    Rect rect1=new Rect();
                    rect1.set((rect.left-80)<0?0:(rect.left-80),(rect.top-80)<0?0:(rect.top-80),(rect.right+80)>width?width:(rect.right+80)
                            ,(rect.bottom+80)>height?height:(rect.bottom+80));

                    yuv.compressToJpeg(rect1, 100, ops);
                  //  yuv.compressToJpeg(fsdkFace.getRect(), 100, ops);
                    final Bitmap bmp = BitmapFactory.decodeByteArray(ops.getByteArray(), 0, ops.getByteArray().length);
                    bitmapList.add(bmp);
                    //推送到主屏---抓脸图片
                    EventBus.getDefault().post(new BitmapsBean(bmp));

                    try {

                        ops.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        isA=true;
                         break;
                    }
                       }
                    }
                    catch (Exception e) {
                    isA=true;
                }
                if (baoCunBean!=null && baoCunBean.getHoutaidizhi_ks()!=null  ){
                if (bitmapList.size()>0){
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                for (Bitmap bitmap:bitmapList){

                                    synchronized (syString){
                                        link_P2(compressImage(bitmap));
                                        try {
                                            syString.wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                            isA=true;
                                        }

                                    }

                                }
                                //循环完
                                isA=true;

                            }
                        }).start();

                    }else {
                        isA=true;
                    }
                }else {
                    isA=true;
                }

            }else {

                isA=true;
                if (bitmapList.size()>0){
                    bitmapList.clear();

                }

            }

        }

        //copy rects
        Rect[] rects = new Rect[result.size()];
        for (int i = 0; i < result.size(); i++) {
            rects[i] = new Rect(result.get(i).getRect());
        }
        //clear result.
        result.clear();
        //return the rects for render.
        return rects;
    }

    @Override
    public void onBeforeRender(CameraFrameData data) {

    }

    @Override
    public void onAfterRender(CameraFrameData data) {

        mGLSurfaceView.getGLES2Render().draw_rect((Rect[])data.getParams(), Color.GREEN, 2);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        CameraHelper.touchFocus(mCamera, event, v, this);
        return false;
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        if (success) {
            Log.d(TAG, "Camera Focus SUCCESS!");
        }
    }


    private static final int TIMEOUT2 = 1000 * 5;
    // 1:N 对比
    private void link_P2(final File file) {
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
                .url(baoCunBean.getHoutaidizhi_ks()+ ":8866/recognize");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("CustomerDisplay", "file.delete():" + file.delete());
                Log.d("AllConnects", "请求识别失败" + e.getMessage());
                synchronized (syString){
                    syString.notify();

                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功" + call.request().toString());
                //获得返回体
                try {
                    ResponseBody body = response.body();
                    String ss = body.string();
                   // Log.d("AllConnects", "传照片" + ss);
                    String s2=ss.replace("\\\\u","@!@#u").replace("\\","")
                            .replace("tag\": \"{","tag\":{")
                            .replace("jpg\"}\"","jpg\"}")
                            .replace("@!@#","\\")
                            .replace("0}\"","0}");


                    Log.d("AllConnects", "传照片2" + s2);

                    JsonObject jsonObject = GsonUtil.parse(s2).getAsJsonObject();
                    Gson gson = new Gson();
                    MenBean menBean = gson.fromJson(jsonObject, MenBean.class);
                    if (menBean.getPerson().getConfidence()>78){

                        Message message=Message.obtain();
                        message.arg1=1;
                        message.obj=menBean;
                        handler.sendMessage(message);
                    }


                } catch (Exception e) {
                    Log.d("WebsocketPushMsg", e.getMessage()+"");
                }finally {
                    Log.d("CustomerDisplay", "file.delete():" + file.delete());
                    synchronized (syString){
                        syString.notify();

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