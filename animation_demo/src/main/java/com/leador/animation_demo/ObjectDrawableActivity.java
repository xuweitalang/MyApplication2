package com.leador.animation_demo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 *ObjectAnimator(属性动画)：属性动画会改变控件真实坐标位置
 */
public class ObjectDrawableActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_drawable);
        iv = (ImageView) findViewById(R.id.iv);
    }

    public void alpha(View v) {
        /**
         * 参数说明：
         * iv：执行动画的目标
         * "alpha":属性名称
         * float...values  :可变参数,这里值透明度的依次变化的值
         */
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"alpha",0,0.2f,0.4f,0.6f,0.8f,1.0f);
        oa.setDuration(2000);
        oa.start();
    }

    //旋转
    public void rotate(View v) {
        /**
         * 参数说明：
         * iv：执行动画的目标
         * "alpha":属性名称
         * float...values  :可变参数,这里值透明度的依次变化的值
         */
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"rotation",0,90,180,270,260,270,180,90,0);
        oa.setDuration(2000);
        oa.start();
    }

    //缩放
    public void scale(View v) {
        /**
         * 参数说明：
         * iv：执行动画的目标
         * "alpha":属性名称
         * float...values  :可变参数,这里值透明度的依次变化的值
         */
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"scaleX",0,0.5f,1,0.5f,1.0f);
        oa.setDuration(2000);
        oa.start();
    }

    //位移
    public void translate(View v) {
        /**
         * 参数说明：
         * iv：执行动画的目标
         * "alpha":属性名称
         * float...values  :可变参数,这里值透明度的依次变化的值
         */
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"translationX",0,2,10);
        oa.setDuration(2000);
        oa.start();
    }
}
