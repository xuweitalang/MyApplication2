package com.leador.sdcardfiledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPath;
    ListView lvFile;
    Button btnBack;
    File currentParent;//记录当前的父文件夹
    File[] currentFiles;// 记录当前路径下的所有文件的文件数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        File root = new File("/mnt/sdcard/"); //获取系统sdcard的根目录
        if(root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            inflateListView(currentFiles);
        }
        lvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(currentFiles[i].isFile()) {
                    return; //如果点击的是文件，不做任务处理
                }
                File [] temp = currentFiles[i].listFiles(); //点击的是文件夹
                if(temp==null || temp.length==0) {
                    Toast.makeText(MainActivity.this,"该路径下不可访问或者该路径下没有文件夹",Toast.LENGTH_LONG).show();
                }else {
                    inflateListView(temp);
                }
            }
        });
    }

    private void inflateListView(File[] currentFiles) {
        List<Map<String,Object>> listItems = new ArrayList<>();
        for(int i=0;i<currentFiles.length;i++) {
            Map<String,Object> listItem = new HashMap<>();
            if(currentFiles[i].isDirectory()) {
                listItem.put("icon",R.mipmap.model_rec_icon_puzzle_a);
            }else {
                listItem.put("icon",R.mipmap.model_rec_icon_puzzle_blue);
            }
            listItem.put("fileName",currentFiles[i]);
            listItems.add(listItem);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.file_list_item,
                new String[]{"icon","fileName"},new int[]{R.id.iv_icon,R.id.tv_file_name});
        lvFile.setAdapter(adapter);
        try {
            tvPath.setText("当前路径为："+currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void init() {
        tvPath = (TextView) findViewById(R.id.tv_path);
        lvFile = (ListView) findViewById(R.id.lv_file);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()) {
             case R.id.btn_back:
                 try {
                     if(!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
                         currentParent = currentParent.getParentFile(); //获取上一级目录
                         currentFiles = currentParent.listFiles();
                         inflateListView(currentFiles);
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
         }
    }
}
