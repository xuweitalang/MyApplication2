package com.leador.androidannotationsdemo;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 首先@EActivity后必须要有一个layout id 来表示这
 个Activity所使用的布局，远来的onCreate方法就不用了
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    /**
     *@ViewById 就和原来
    的findViewById()方法一样，值得注意的是：@ViewById后的id是可以不写的，条件是控件变量名称要与xml中定义的id
    必须一致,也就是说 当我在xml文件中定义的TextView的id必须是：android:id="@+id/textView" . 这样我们在@ViewById
    后就不用再写括号了，直接写
     */
    @ViewById(R.id.textView)
    TextView textView;

    @ViewById
    EditText editText;

    @Click(R.id.btn)
    void myButton() {
        Toast.makeText(this,"点击按钮",Toast.LENGTH_SHORT).show();
    }

}
