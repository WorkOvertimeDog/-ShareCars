package com.greenfinch.sharecars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.greenfinch.sharecars.activity.GuideActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    int token = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JudgeLogin();
    }

    /**
     * 判断登录跳转界面，根据token，目前没借口；
     */
    private void JudgeLogin() {
        if (token == 0){
            ActivityUtils.startActivity(this, GuideActivity.class);
        }else {

        }
    }
}
