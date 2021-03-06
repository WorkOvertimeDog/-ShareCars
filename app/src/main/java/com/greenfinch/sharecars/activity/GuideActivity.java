package com.greenfinch.sharecars.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;

/**
 * Created by pangjiaqi on 2017/9/9.
 * 是否登录界面
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener {
    Button mBtnLogin;
    private boolean isRegister = false;

    @Override
    protected void doAdd() {

    }

    @Override
    public int setUpContentView() {
        return R.layout.activity_gudie_layout;
    }

    @Override
    public void setUpView() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    /**
     * 登录还是注册
     */
    @Override
    public void onClick(View view) {
        if (isRegister = true) {
            //如果未注册，跳转到注册界面
            ActivityUtils.startActivity(this, UserRegisterActivity.class, R.anim.fade_in, R.anim.fade_out);
        } else {
            //如果注册过，跳转到押金界面
            ActivityUtils.startActivity(this, DepositPayActivity.class, R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.finishAllActivities();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
