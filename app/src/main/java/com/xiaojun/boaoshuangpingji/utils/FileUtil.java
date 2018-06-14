package com.xiaojun.boaoshuangpingji.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by chenjun on 2017/5/17.
 */

public class FileUtil {
    public static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String PATH = "ruitongPD1";

    /**
     * 是否支持SDCard
     */
    public static boolean isSupportSDCard() {
        return Environment.getExternalStorageDirectory().exists();
    }

    /**
     * 检测文件或者路径是否存在
     * <p>
     * 可以给值为Null，如果给值null,判断路径是否存在
     */

    public static boolean isExists(String path, String fileName) {
        if (null == path && null == fileName) {
            return false;
        }
        String name;
        name = SDPATH + File.separator + path;
        File file = new File(name);
        if (!file.exists()) {
            file.mkdirs();
        }
        File fileNmae = new File(name, fileName);
        return fileNmae.exists();
    }

    public static boolean isExists(String path) {
        if (null == path) {
            return false;
        }
        String name;

        name = SDPATH + File.separator + path;

        File file = new File(name);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.exists();
    }

    /**
     * 检查SD卡是否可用
     */
    public static boolean isAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }


    /***
     *保存bitmap对象到文件中
     * @param bm
     * @param path
     * @param quality
     * @return
     */
    public static boolean saveBitmap2File(Bitmap bm, String path, int quality) {
        if (null == bm || bm.isRecycled()) {
            return false;
        }
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != bm) {
                if (!bm.isRecycled()) {
                    bm.recycle();
                }
                bm = null;
            }
        }
    }

    /**
     * 获取指定目录内所有文件路径
     * @param dirPath 需要查询的文件目录
     * @param
     */
    public static List<String> getAllFileXml(String dirPath, List<String> fileList) {
        File f = new File(dirPath);
        Log.d("FileUtilXML", "f.exists():" + f.exists()+dirPath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();
        // Log.d("FileUtil", "files.length:" + files.length);
        if(files==null){//判断权限
            return null;
        }

        Log.d("FileUtilXml", "文件夹个数" + files.length);

        for (File _file : files) {//遍历目录
            if(_file.isFile() && (_file.getName().endsWith("png") )){
                String _name=_file.getName();
                String filePath = _file.getAbsolutePath();//获取文件路径
                //  String fileName = _file.getName().substring(0,_name.length()-4);//获取文件名
                //  Log.d("LOGCAT","fileName:"+fileName);
                // Log.d("LOGCAT","filePath:"+filePath);
                try {
                    fileList.add(filePath);

                }catch (Exception e){
                    Log.d("FileUtil", e.getMessage()+"获取usb文件异常");
                }
            }
//            else if(_file.isDirectory()){//查询子目录
//                getAllFileXml(_file.getAbsolutePath(),fileList);
//            }
        }
        Log.d("FileUtil", "返回的xml个数:" + fileList.size());
        return fileList;
    }

}
