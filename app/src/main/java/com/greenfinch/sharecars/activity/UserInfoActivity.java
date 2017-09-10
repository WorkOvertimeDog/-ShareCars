package com.greenfinch.sharecars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;

/**
 * Created by Admin on 2017/9/10.
 */
public class UserInfoActivity extends BaseActivity {

    private RelativeLayout myCredit;

    @Override
    public void setUpView() {
        myCredit = (RelativeLayout) findViewById(R.id.my_credit);
        myCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoActivity.this, MyCreditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int setUpContentView() {
        return R.layout.user_info_activity_layout;
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    protected void doAdd() {

    }
}
