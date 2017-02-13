package com.leador.toggleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.leador.toggleview.ui.RefreshListView;
import com.leador.toggleview.ui.SwitchView;
import com.leador.toggleview.ui.ToggleView;

import java.util.ArrayList;

/**
 * write by xw
 */
public class MainActivity extends AppCompatActivity {
    private ToggleView toggleView;
    private SwitchView switchView;
    private RefreshListView refreshListView;
    private ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toggleView = (ToggleView) findViewById(R.id.toggleView);
//        switchView = (SwitchView) findViewById(R.id.switchView);
        refreshListView = (RefreshListView) findViewById(R.id.refreshView);
        dataList = new ArrayList<>();
        for(int i=0;i<30;i++) {
            dataList.add("这是刷新数据"+i);
        }
        refreshListView.setAdapter(new MyAdapter(this,dataList));

//        toggleView.setSwitchBackgroundResource(R.mipmap.switch_background);
//        toggleView.setSlideButtonResource(R.mipmap.slide_button);
//        toggleView.setSwitchState(true);

        /*toggleView.setOnStateChangeListener(new ToggleView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isOpen) {
                Toast.makeText(MainActivity.this,isOpen+"",Toast.LENGTH_SHORT).show();
            }
        });

        switchView.setOnStateChangeListener(new SwitchView.StateChangeListener() {
            @Override
            public void onStateChange(boolean state) {
                Toast.makeText(MainActivity.this,state + "",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

}
