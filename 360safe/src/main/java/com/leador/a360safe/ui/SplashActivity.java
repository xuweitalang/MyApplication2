package com.leador.a360safe.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leador.a360safe.R;
import com.leador.a360safe.utils.ConstantValue;
import com.leador.a360safe.utils.SpUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class SplashActivity extends AppCompatActivity {
    private TextView tvVersion;
    private ProgressBar progressBar;
    LinearLayout rlyt_root;
    private int mLocalVersionCode;
    public static final int VERSION_UPDATE = 100; //更新程序的状态码
    public static final int INTO_MAIN = 101;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VERSION_UPDATE:
                    showUpdateDialog();
                    break;
                case INTO_MAIN:
                    intoMain();
                    break;
            }
        }
    };

    private void showUpdateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("版本更新")
                .setMessage("吊炸天的更新")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //下载更新
                        downLoadApk();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intoMain();
            }
        }).show();
    }

    private void downLoadApk() {
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) { //判断SD卡是否可用
            // 获取sd路径,
            String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xxx.apk";
            HttpUtils httpUtils = new HttpUtils();
            String uri = "http://www.xxx.com.apk";
            httpUtils.download(uri, apkPath, new RequestCallBack<File>() { //apkPath为下载放置的位置，Uri为下载的地址
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    //下载成功调用
                    File result = responseInfo.result;
                    //安装apk
                    installApk(result);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    // 下载失败调用
                }

                @Override
                public void onStart() {
                    super.onStart(); // 开始下载调用
                }
            });
        }
    }

    /**
     *
     * @param result 安装文件
     */
    private void installApk(File result) {

    }

    private void intoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frash);
        initView();
        initData();
        initAnimation();
    }

    private void initAnimation() { //初始化动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        rlyt_root.startAnimation(alphaAnimation);
    }

    private void initData() {
        //1.获取版本名称
        tvVersion.setText("版本名称" + getVersionName());
        //2.检测版本更新（将本地版本号与服务端版本号比较）
        mLocalVersionCode = getVersionCode();
        //3,获取服务器版本号(客户端发请求,服务端给响应,(json,xml))
        //http://www.oxxx.com/update74.json?key=value  返回200 请求成功,流的方式将数据读取下来
        //json中内容包含:
        /* 更新版本的版本名称
         * 新版本的描述信息
		 * 服务器版本号
		 * 新版本apk下载地址*/

        if(SpUtil.getBoolean(this, ConstantValue.OPEN_UPDATE, false)){
            checkVersion();
        }else{
            //直接进入应用程序主界面
//			enterHome();
            //消息机制
//			mHandler.sendMessageDelayed(msg, 4000);

            //在发送消息4秒后去处理,ENTER_HOME状态码指向的消息
            mHandler.sendEmptyMessageDelayed(INTO_MAIN, 4000); //方法2
        }
        checkVersion();
    }

    /*
    解析json字符串：
    {
        versionName:"2.0",
        versionDes:"2.0版本发布了，狂拽炫酷吊炸天，快来下载啊",
        versionCode:"2"
        downLoadUrl:"http://www.xxx.com.apk"
    }*/

    //检测服务端版本
    private void checkVersion() {
        new Thread() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                Message msg = Message.obtain();
                try {

                    //1.封装URL地址
//                    URL url = new URL("http://192.168.10.10:8080/update.json"); //192.168.10.10服务端所在地址
                    //2.创建连接
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //3.设置请求参数
//                    connection.setConnectTimeout(2000); // 设置请求超时（还没连接成功）
//                    connection.setReadTimeout(3000); //设置读取超时（连接成功后但是未读取完数据）
//                    connection.setRequestMethod("POST"); //设置请求方式，默认为get请求
                    //4.获取响应码
//                    if(connection.getResponseCode() == 200) {
                    //5.获取读取流
//                        InputStream is = connection.getInputStream();
                    //6.将流转换成字符串
//                        String json = StreamUtil.stream2String(is);
//                        JSONObject jsonObject = new JSONObject(json);
//                        String serverVersionName = jsonObject.getString("versionName"); //从服务端获取到的版本号
                    String serverVersionCode = "2"; //模拟服务端版本
                    //7.比对版本号（服务端版本号>本地版本号，提示更新）
                    if (mLocalVersionCode < Integer.parseInt(serverVersionCode)) {
                        //弹出对话框，提示用户更新，运用消息机制
                        msg.what = VERSION_UPDATE;
                    } else {
                        //进入程序主界面
                        msg.what = INTO_MAIN;
                    }
//                }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //模拟网络请求，指定睡眠时间，如果网络请求时间大于4s则不作处理，小于4s让其睡眠满4s
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 4000) {
                        try {
                            Thread.sleep(4000 - (endTime - startTime));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }

    private int getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0); //从包管理对象中获取指定包名的基本信息，传0代表获取基本信息
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取版本名称
     */
    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0); //从包管理对象中获取指定包名的基本信息，传0代表获取基本信息
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initView() {
        tvVersion = (TextView) findViewById(R.id.tvVersion);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        rlyt_root = (LinearLayout) findViewById(R.id.llyt_root);
    }
}
