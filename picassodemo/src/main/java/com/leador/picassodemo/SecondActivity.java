package com.leador.picassodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements MyAdapter.MyItemClickListner {
    RecyclerView rcView;
    List<NewsBean> list;
    MyAdapter adapter;
    String uri = "http://apis.baidu.com/txapi/huabian/newtop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        Parameters para = new Parameters();
        para.put("num", 10);
        para.put("page",1);
        ApiStoreSDK.execute(uri,
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {

                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess");
                        if (responseString != null) {
                            Log.i("TAG","result = "+responseString);
                            Gson gson = GsonUtil.getGson();
                            list = new ArrayList<>();
                            Type type = new TypeToken<Result<List<NewsBean>>>(){}.getType();
                            Result<List<NewsBean>> result = gson.fromJson(responseString,type);
                            list = result.getNewslist();
                            adapter = new MyAdapter(SecondActivity.this,list);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
//            layoutManager.setOrientation(OrientationHelper.VERTICAL);
                            rcView.setLayoutManager(layoutManager);
                            rcView.setAdapter(adapter);
                            boolean isMainThread = Thread.currentThread().getName().equals("main");
                            Log.i("TAG","isMainThread = "+isMainThread);
                            adapter.setOnItemClickListener(SecondActivity.this);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                    }

                });


//        new MyAsyncTask().execute();

    }

    private void initView() {
        rcView = (RecyclerView) findViewById(R.id.rcView);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,WebActivity.class);
        intent.putExtra("uri",list.get(position).getUrl());
        startActivity(intent);

        //通过系统自带浏览器打开网页
        /*Uri uri = Uri.parse(list.get(position).getUrl());
        startActivity(new Intent(Intent.ACTION_VIEW,uri));*/
    }


    /*class MyAsyncTask extends AsyncTask<Void,Void,List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(Void... voids) {
            Parameters para = new Parameters();
            para.put("num", 10);
            para.put("page",1);
            ApiStoreSDK.execute(uri,
                    ApiStoreSDK.GET,
                    para,
                    new ApiCallBack() {

                        @Override
                        public void onSuccess(int status, String responseString) {
                            Log.i("sdkdemo", "onSuccess");
                            if (responseString != null) {
                                Log.i("TAG","result = "+responseString);
                                Gson gson = GsonUtil.getGson();
                                list = new ArrayList<>();
                                Type type = new TypeToken<Result<List<NewsBean>>>(){}.getType();
                                Result<List<NewsBean>> result = gson.fromJson(responseString,type);
                                list = result.getNewslist();
                                adapter = new MyAdapter(SecondActivity.this,list);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
                                rcView.setLayoutManager(layoutManager);
                                rcView.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onComplete() {
                            Log.i("sdkdemo", "onComplete");
                        }

                        @Override
                        public void onError(int status, String responseString, Exception e) {
                            Log.i("sdkdemo", "onError, status: " + status);
                            Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                        }

                    });
            return list;
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeen) {
            adapter = new MyAdapter(SecondActivity.this,list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
//            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            rcView.setLayoutManager(layoutManager);

            rcView.setAdapter(adapter);
        }
    }*/
}
