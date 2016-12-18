package com.leador.toggleview.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xuwei on 2016/11/27.
 * 自定义控件编写
 *
 * android界面绘制流程：
 *
 * 测量        摆放       绘制
 * measure --> layout --> draw
 *    |            |        |
 * onMeasure-> onLaout-> onDraw 重写这些方法，实现自定义控件
 * 注意：对于没有子控件的view，则不用重写onLayout()方法，即继承View的自定义控件。
 * 继承ViewGroup的控件有子控件，则需要重写onLayout()方法
 *
 * View:
 * onMeasure()在这个方法中绘制自己的宽高 --> onDraw() 绘制自己的内容
 *
 * ViewGroup：
 * onMeasure()在这个方法中绘制自己的宽高和子view的宽高 -->onLayout()摆放所有子view --> onDraw() 绘制自己的内容
 *
 * 控件如果放置在activity或fragment中，以上三个方法都是执行完onResume()方法之后执行
 */
public class ToggleView extends View {

    private Bitmap slideBtnBitmap;
    private Bitmap switchBgBitmap;
    private boolean isOpen = false; //默认关闭状态
    private float currentX;
    private OnStateChangeListener onStateChangeListener;
    private boolean state;

    //用于代码创建控件
    public ToggleView(Context context) {
        super(context);
    }

    //用于在xml中使用，可指定自定义属性
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取配置的自定义属性
        String nameSpace = "http://schemas.android.com/apk/res/com.leador.toggleview"; //命名空间
        int switchBgResource = attrs.getAttributeResourceValue(nameSpace,"switch_background",-1);
        int slideBtnResource = attrs.getAttributeResourceValue(nameSpace,"slide_button",-1);
        boolean state = attrs.getAttributeBooleanValue(nameSpace,"state",false);

        setSwitchBackgroundResource(switchBgResource);
        setSlideButtonResource(slideBtnResource);
        setSwitchState(state);

    }

    //用于在xml中使用，可指定自定义属性,如果定义了样式，走此构造函数
    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 绘制控件的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(switchBgBitmap.getWidth(),switchBgBitmap.getHeight());
    }

    /**
     * 绘制内容
     * @param canvas：画布：显示绘制的内容
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //1.绘制背景
        Paint paint = new Paint();
        canvas.drawBitmap(switchBgBitmap,0,0,paint);

        //2.绘制滑块
        if(isTouchMode) { //触摸状态时根据当前用户触摸到的位置画滑块

            float maxLeft = switchBgBitmap.getWidth() - slideBtnBitmap.getWidth();
            //让滑块向左移动自身一半大小位置
            float newLeft = currentX - slideBtnBitmap.getWidth()/2.0f;
            //限定滑块范围
            if(newLeft<0) { // 左边范围
                newLeft = 0;
            }else if(newLeft>maxLeft) {
                newLeft = maxLeft; // 右边范围
            }
            canvas.drawBitmap(slideBtnBitmap,newLeft,0,paint);
        }else { // 根据开关状态直接绘制图片位置
            if(isOpen) {
                canvas.drawBitmap(slideBtnBitmap,switchBgBitmap.getWidth()-slideBtnBitmap.getWidth(),0,paint);
            }else {
                canvas.drawBitmap(slideBtnBitmap,0,0,paint);
            }
        }
    }


    boolean isTouchMode = false; //默认不是触摸状态

    /**
     * 重写触摸事件，响应用户的触摸
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouchMode = true;
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                isTouchMode = false;
                currentX = event.getX();

                float center = switchBgBitmap.getWidth()/2.0f;
                //根据当前滑动位置和控件中心位置判断开关状态
                state = currentX > center;
                if(state != isOpen && onStateChangeListener != null) { //如果开关状态变化则调用监听方法
                    onStateChangeListener.onStateChange(state);
                }
                isOpen = state;

                break;
        }

        invalidate(); //会引发onDraw方法重新调用，里面的变量重新生效，界面会刷新
        return true; // 返回true：消费了用户的触摸事件，才可以收到到其他的事件
    }

    /**
     * 设置图片切换开关状态：true为开
     * @param isOpen
     */
    public void setSwitchState(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 设置滑块图片
     * @param slide_button
     */
    public void setSlideButtonResource(int slide_button) {
        slideBtnBitmap = BitmapFactory.decodeResource(getResources(),slide_button);
    }

    /**
     * 设置背景图片
     * @param switch_background
     */
    public void setSwitchBackgroundResource(int switch_background) {
        switchBgBitmap = BitmapFactory.decodeResource(getResources(),switch_background);
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public interface OnStateChangeListener {
        void onStateChange(boolean isOpen); //接口回调，将当前状态传递出去
    }

}
