package com.greenfinch.sharecars.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;

/**
 * Created by pangjiaqi on 2017/9/10.
 */

public class RealNameActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton mIvBack;
    private TextView mTvTitle;
    private EditText etIntputName;
    private EditText etInputId;
    private EditText etInputDriving;
    private Button mBtnConfirm;

    @Override
    protected void doAdd() {

    }

    @Override
    public int setUpContentView() {
        return R.layout.activity_real_name_layout;
    }

    @Override
    public void setUpView() {
        mIvBack = (ImageButton) findViewById(R.id.ibt_back);
        mTvTitle = (TextView) findViewById(R.id.tex_title);
        etIntputName = (EditText) findViewById(R.id.et_input_name);
        etInputId = (EditText) findViewById(R.id.et_input_id_card);
        etInputDriving = (EditText) findViewById(R.id.et_input_driving_license);
        mBtnConfirm = (Button) findViewById(R.id.btn_real_name_confirm);
        mIvBack.setOnClickListener(this);
        mIvBack.setVisibility(View.VISIBLE);
        mBtnConfirm.setOnClickListener(this);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.ibt_back:
                finish();
                break;
            //验证身份
            case R.id.btn_real_name_confirm:
                //跳转认证成功界面
                ActivityUtils.startActivity(RealNameActivity.this, IdentitySuccessActivity.class, R.anim.slide_right_in, R.anim.slide_left_out);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
