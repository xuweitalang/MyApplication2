package com.leador.animation_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 补间动画（View）功能：透明、旋转、缩放、位移，补间动画不会改变控件真实的坐标
 */
public class ViewDrawableActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btnInto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drawable);
        image = (ImageView) findViewById(R.id.image);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btnInto = (Button) findViewById(R.id.btnInto);
        btnInto.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1: //透明动画：AlphaAnimation
                /*AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f); //0.0表示完全透明
                alphaAnimation.setDuration(1000); //执行时间
                alphaAnimation.setRepeatMode(Animation.REVERSE); //重复模式
                alphaAnimation.setRepeatCount(1); //重复次数
                image.startAnimation(alphaAnimation);*/

                //通过加载xml动画文件执行动画
                Animation animation = AnimationUtils.loadAnimation(this,R.anim.set_test);
                image.startAnimation(animation);
                break;
            case R.id.btn2: //旋转动画：RotateAnimation
//                RotateAnimation rotateAnimation = new RotateAnimation(0,360); //旋转角度从0到360度

                //Animation.RELATIVE_TO_SELF表示相对于自己旋转，0.5f表示基于宽或者高的中心点旋转
                /*RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000); //执行时间
                rotateAnimation.setRepeatMode(Animation.REVERSE); //重复模式
                rotateAnimation.setRepeatCount(1); //重复次数
                image.startAnimation(rotateAnimation);*/

                Animation routateAnim = AnimationUtils.loadAnimation(this,R.anim.rotate_test);
                image.startAnimation(routateAnim);
                break;
            case R.id.btn3: //缩放动画：ScaleAnimation

                //从1.0缩放到2.0，即放大到两倍
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,2.0f,1.0f,2.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(1000); //执行时间
                scaleAnimation.setRepeatMode(Animation.REVERSE); //重复模式
                scaleAnimation.setRepeatCount(1); //重复次数
                image.startAnimation(scaleAnimation);
                break;
            case R.id.btn4: //位移动画：TranslateAnimation
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.2f,Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0.2f);
                translateAnimation.setDuration(1000); //执行时间
                translateAnimation.setRepeatMode(Animation.REVERSE); //重复模式
//                translateAnimation.setRepeatCount(1); //重复次数
                translateAnimation.setFillAfter(true); //动画执行完后停留在执行完的位置
                image.startAnimation(translateAnimation);
                break;
            case R.id.btn5: //一起执行
                AnimationSet set = new AnimationSet(true);

                AlphaAnimation alphaAnimation1 = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation1.setDuration(1000); //执行时间
                alphaAnimation1.setRepeatMode(Animation.REVERSE); //重复模式
                alphaAnimation1.setRepeatCount(1); //重复次数

                RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation1.setDuration(1000); //执行时间
                rotateAnimation1.setRepeatMode(Animation.REVERSE); //重复模式
                rotateAnimation1.setRepeatCount(1); //重复次数

                ScaleAnimation scaleAnimation1 = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation1.setDuration(1000); //执行时间
                scaleAnimation1.setRepeatMode(Animation.REVERSE); //重复模式
                scaleAnimation1.setRepeatCount(1); //重复次数

                TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.2f, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.2f);
                translateAnimation1.setDuration(1000); //执行时间
                translateAnimation1.setRepeatMode(Animation.REVERSE); //重复模式
//                translateAnimation.setRepeatCount(1); //重复次数
                translateAnimation1.setFillAfter(true); //动画执行完后停留在执行完的位置

                set.addAnimation(alphaAnimation1);
                set.addAnimation(rotateAnimation1);
                set.addAnimation(scaleAnimation1);
                set.addAnimation(translateAnimation1);
                image.startAnimation(set);
                break;

            case R.id.btnInto:
                startActivity(new Intent(this,ObjectDrawableActivity.class));
        }
    }
}
