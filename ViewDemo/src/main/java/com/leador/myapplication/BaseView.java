package com.leador.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuwei on 2016/8/27.
 */
public abstract class BaseView extends View {
    private Bitmap bitmap;
    private float index;
    private MyThread myThread;
    private Paint paint;
    private float angle;

    public BaseView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }

    public abstract void drawSub(Canvas canvas);
        /*paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(0xff00ff00);
        canvas.drawText("nihao",0,30,paint);
        canvas.drawBitmap(bitmap,0,60,paint);
        canvas.drawText("滚动测试",index,300,paint);
        RectF f = new RectF(0,200,100,300);
        canvas.drawArc(f,0,angle,true,paint);*/

    @Override
    protected void onDraw(Canvas canvas) {
        if (myThread == null) {
            myThread = new MyThread();
            myThread.start();
        }else {
            drawSub(canvas);
        }
    }

    public abstract void logic();
        /*index += 5;
        angle += 5;
        if(angle>360) {
            angle = 0;
        }
        if(index>getWidth()) { //滚出屏幕
            index = - paint.measureText("滚动测试");
        }*/

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                logic();
                postInvalidate(); //线程中更新绘制时调用
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
