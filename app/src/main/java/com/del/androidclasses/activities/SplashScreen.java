package com.del.androidclasses.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import com.del.androidclasses.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView img;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        
        new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    Intent next = new Intent();
                    next.setClass(getApplicationContext(), MainActivity.class);
                   startActivity(next);
                    finish();
                }
            }, 2500);
    }
    
    private void initView(){
    img = (ImageView)findViewById(R.id.app_logo);
    text = (TextView)findViewById(R.id.app_name);
    
    }
}