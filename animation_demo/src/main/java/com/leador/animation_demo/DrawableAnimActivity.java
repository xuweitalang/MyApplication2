package com.leador.animation_demo;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 帧动画（DrawableAnimation）：就是将一连串的图片播放出来的动画
 */
public class DrawableAnimActivity extends AppCompatActivity {
    ImageView ivAnim;
    Button botton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAnim = (ImageView) findViewById(R.id.ivAnim);
        botton = (Button) findViewById(R.id.button);
        ivAnim.setBackgroundResource(R.drawable.my_anim);
        final AnimationDrawable ad = (AnimationDrawable) ivAnim.getBackground();
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.start();
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                startActivity(new Intent(this,ViewDrawableActivity.class));
                break;
        }
    }


}
