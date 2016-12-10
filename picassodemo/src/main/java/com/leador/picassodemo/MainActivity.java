package com.leador.picassodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Picasso框架使用：
 * 功能：实现图片下载和缓存功能，除了加载网络图片picasso还支持加载
 * Resources, assets, files, content providers中的资源文件
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    private ImageView iv2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        String imgUrl = "http://i.imgur.com/DvpvklR.png";
//        Picasso.with(this).load(imgUrl).into(iv);
//        Picasso.with(this).load(R.mipmap.ic_launcher).into(iv);

        //可以通过resize方法对图片大小进行控制，已减小内存占用，注意：加载几兆的网络图片资源可能会显示不了
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").resize(100,100).centerCrop().into(iv);

        //Place holders-空白或者错误占位图片：
        // picasso提供了两种占位图片，未加载完成或者加载发生错误的时需要一张图片作为提示
        Picasso.with(this).load(R.mipmap.module_media).resize(100,100).centerCrop()
                .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(iv2);
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        iv2 = (ImageView) findViewById(R.id.iv2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }
}
