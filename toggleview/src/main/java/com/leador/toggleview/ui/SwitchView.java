package com.leador.toggleview.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.leador.toggleview.R;

/**
 * Created by xuwei on 2016/12/18.
 */

public class SwitchView extends View {
    private Bitmap backBitmap;
    private Bitmap switchBitmap;
    private boolean isOpen = false;
    private float currentX;
    private boolean state;
    private StateChangeListener listener;

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SwitchView);
        int backBitmapId = array.getResourceId(R.styleable.SwitchView_back_bitmap,-1);
        int switchBitmapId = array.getResourceId(R.styleable.SwitchView_switch_bitmap,-1);
        final boolean state = array.getBoolean(R.styleable.SwitchView_new_state,false);
        /**
         * 在TypedArray后调用recycle主要是为了缓存。当recycle被调用后，这就说明这个对象从现在可以被重用了
         */
        array.recycle();

        setBackBitmap(backBitmapId);
        setSwitchBitmap(switchBitmapId);
        setState(state);
    }

    private void setBackBitmap(int backBitmapId) {
        backBitmap = BitmapFactory.decodeResource(getResources(),backBitmapId);
    }

    private void setSwitchBitmap(int switchBitmapId) {
        switchBitmap = BitmapFactory.decodeResource(getResources(),switchBitmapId);
    }

    private void setState(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(backBitmap.getWidth(),backBitmap.getHeight());
    }

    boolean TouchMode = false; //触摸模式

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //绘制背景
        canvas.drawBitmap(backBitmap,0,0,paint);
        //绘制滑块
        if(TouchMode) {
            float maxLeft = backBitmap.getWidth() - switchBitmap.getWidth();
            float newLeft = currentX - switchBitmap.getWidth()/2.0f;
            if(newLeft<0) {
                newLeft = 0;
            }
            if(newLeft > maxLeft) {
                newLeft = maxLeft;
            }
            canvas.drawBitmap(switchBitmap,newLeft,0,paint);
        }else {
            if(isOpen) {
                canvas.drawBitmap(switchBitmap,backBitmap.getWidth() - switchBitmap.getWidth(),0,paint);
            } else {
                canvas.drawBitmap(switchBitmap,0,0,paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchMode = true;
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                TouchMode = false;
                currentX = event.getX();
                float center = switchBitmap.getWidth()/2.0f;
                state = currentX > center;
                if(state != isOpen && listener != null) {
                    listener.onStateChange(state);
                }
                isOpen = state;
                break;
        }
        invalidate();
        return true;
    }

    public interface StateChangeListener{
        void onStateChange(boolean state);
    }

    public void setOnStateChangeListener(StateChangeListener listener) {
        this.listener = listener;
    }
}
