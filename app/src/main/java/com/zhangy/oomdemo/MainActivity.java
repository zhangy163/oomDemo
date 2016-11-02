package com.zhangy.oomdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ImageReader;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private TextView mContent;
    private TextView mLoading;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mContent.setOnClickListener(this);
        mLoading.setOnClickListener(this);

    }

    private void init() {
        mTitle = (TextView) findViewById(R.id.mTitle);
        mContent = (TextView) findViewById(R.id.mContent);
        mLoading = (TextView) findViewById(R.id.mLoading);
        mImage = (ImageView) findViewById(R.id.mImageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mContent:
                //测试每个app可用的最大内存(android每一个app都运行在自己独立的沙箱里面)
                ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(Context.ACTIVITY_SERVICE);
                int memoryClass = activityManager.getMemoryClass();//返回本机给每个APP分配的运行内存
                mContent.setText("最大内存：" + memoryClass);
                break;
            case R.id.mLoading:
                //BitmapFactory提供了四种方法，加载Bitmap对象
                //1、decodeFile  文件
                //2、decodeResource 资源
                //以上这两种调用了decodeStream方法，这四种方法都是在底层实现

                //3、decodeStream 输入流
                //2、decodeByteArray 字节数组
                // Bitmap bigPicBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                mImage.setImageBitmap(new ImageResizer().decodeSampledBitmapFromResource(getResources(),R.mipmap.ic_launcher,200,100));
                break;
        }
    }
}
