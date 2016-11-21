package com.leador.demoforbutterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在AndroidStudio->File->Settings->Plugins->搜索Zelezny下载添加就行 ，
 * 可以快速生成对应组件的实例对象，不用手动写。
 * 使用时，在要导入注解的Activity(此处为34行) 或 Fragment 或 ViewHolder的layout资源代码上，
 * 右键——>Generate——Generate ButterKnife Injections。
 *
 * 注意：有些ButterKnife的引入会可能会报空指针异常，可参考此项目的build.gradle文件
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //ButterKnife.bind(this)用于绑定Activity，必须在setContentView之后：

        tv1.setText("你好");
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Toast.makeText(this,"点击1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(this,"点击2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this,"点击3",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
