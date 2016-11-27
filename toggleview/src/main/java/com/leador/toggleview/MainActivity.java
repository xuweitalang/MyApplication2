package com.leador.toggleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.leador.toggleview.ui.ToggleView;

/**
 * write by xw
 */
public class MainActivity extends AppCompatActivity {
    private ToggleView toggleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleView = (ToggleView) findViewById(R.id.toggleView);
//        toggleView.setSwitchBackgroundResource(R.mipmap.switch_background);
//        toggleView.setSlideButtonResource(R.mipmap.slide_button);
//        toggleView.setSwitchState(true);

        toggleView.setOnStateChangeListener(new ToggleView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isOpen) {
                Toast.makeText(MainActivity.this,isOpen+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
