package com.leador.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by xuwei on 2016/8/27.
 */
public class MyView extends BaseView {
    Paint paint;
    private float index;
    int textColor;
    int textSize;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.titleTextStyle);
        textColor = array.getColor(R.styleable.titleTextStyle_titleTextColor,0);
        textSize = array.getDimensionPixelSize(R.styleable.titleTextStyle_titleTextSize,0);
        array.recycle();//释放重用
    }

    @Override
    public void drawSub(Canvas canvas) {
        paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        canvas.drawText("只是测试专用",50,index,paint);
    }

    @Override
    public void logic() {
        index +=2;
        if(index>getHeight()) {
            index = -paint.measureText("只是测试专用");
        }
    }
}
