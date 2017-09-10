package com.greenfinch.sharecars.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;

/**
 * Created by pangjiaqi on 2017/9/10.
 * 认证成功界面
 */

public class IdentitySuccessActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtnIsConfirm;
    private ImageButton iBtnBack;

    @Override
    protected void doAdd() {

    }

    @Override
    public int setUpContentView() {
        return R.layout.activity_identity_layout;
    }

    @Override
    public void setUpView() {
        mBtnIsConfirm = (Button) findViewById(R.id.btn_identity_confirm);
        iBtnBack = (ImageButton) findViewById(R.id.ibt_back);
        iBtnBack.setVisibility(View.VISIBLE);
        mBtnIsConfirm.setOnClickListener(this);
        iBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_identity_confirm:
                //跳转到主界面。（立即用车）
                ActivityUtils.startActivity(IdentitySuccessActivity.this, HomeActivity.class, R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.ibt_back:
                finish();
                break;
        }
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
