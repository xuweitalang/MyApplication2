package com.leador.a360safe.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xuwei on 2016/12/27.
 */

public class FocusTextView extends TextView {
    public FocusTextView(Context context) {
        super(context);
    }

    public FocusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写获取焦点的方法,由系统调用,调用的时候默认就能获取焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
