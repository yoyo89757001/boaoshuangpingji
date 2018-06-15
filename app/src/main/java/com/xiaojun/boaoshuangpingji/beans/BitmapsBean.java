package com.xiaojun.boaoshuangpingji.beans;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/6/15.
 */

public class BitmapsBean {
    private Bitmap bitmap;

    public BitmapsBean(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
