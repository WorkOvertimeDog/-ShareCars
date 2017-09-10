package com.greenfinch.sharecars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;
import com.greenfinch.sharecars.utils.RegexUtils;

/**
 * Created by pangjiaqi on 2017/9/9.
 */

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtInputPhone;
    private EditText mEtInputCode;
    private Button mBtnObtainCode;
    private Button mBtnVerifyPhone;
    private CheckBox mCbSelect;
    private String getPhone;
    private String getCode;
    private boolean isTure;

    @Override
    protected void doAdd() {

    }

    @Override
    public int setUpContentView() {
        return R.layout.activity_user_register_layout;
    }

    @Override
    public void setUpView() {
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void initViews() {
        mEtInputPhone = (EditText) findViewById(R.id.et_input_phone);
        mEtInputCode = (EditText) findViewById(R.id.et_input_code);
        mBtnObtainCode = (Button) findViewById(R.id.btn_obtain_code);
        mBtnVerifyPhone = (Button) findViewById(R.id.btn_verify_phone);
        mCbSelect = (CheckBox) findViewById(R.id.cb_select);
        mBtnVerifyPhone.setOnClickListener(this);
        mBtnObtainCode.setOnClickListener(this);
        mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getPhone = mEtInputPhone.getText().toString().trim();
                getCode = mEtInputCode.getText().toString().trim();
                //全局控制
                boolean isValidate;
                isTure = RegexUtils.isMobileExact(getPhone);
                if (b) {
                    if ("".equals(getPhone)) {
                        isValidate = false;
                    } else {
                        isValidate = true;
                    }
                } else {
                    isValidate = false;
                    Toast.makeText(UserRegisterActivity.this, "取消勾选协议，将无法完成注册", Toast.LENGTH_SHORT).show();
                }

//                if (isValidate) {
//                    mBtnVerifyPhone.setBackgroundColor(Color.parseColor("#000000"));
//                    mBtnVerifyPhone.setEnabled(true);
//                } else {
//                    mBtnVerifyPhone.setBackgroundColor(Color.parseColor("#e3e3e3"));
//                    mBtnVerifyPhone.setEnabled(false);
//                }
            }
        });

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        getPhone = mEtInputPhone.getText().toString();
        getCode = mEtInputCode.getText().toString();
        isTure = RegexUtils.isMobileExact(getPhone);
        switch (view.getId()) {
            case R.id.btn_obtain_code:
                if ("".equals(getPhone)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (isTure == false) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                } else {
                    //从服务器获取验证码
                    Toast.makeText(this, "从服务器获取验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_verify_phone:
                //这里检测获取的验证码与输入的验证码是否一致
                if ("".equals(getCode)) {
                    Toast.makeText(this, "验证码输入不能为空", Toast.LENGTH_SHORT).show();
                } else if (getCode.length() != 4) {
                    Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityUtils.startActivity(this, DepositPayActivity.class);
                }
//                if ("".equals(getCode)) {
//                    Toast.makeText(this, "验证码输入不能为空", Toast.LENGTH_SHORT).show();
//                } else if (getCode.length() != 4) {
//                    Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
//                } else {
//                    ActivityUtils.startActivity(this, HomeActivity.class);
//                }
                Intent intent = new Intent(UserRegisterActivity.this, HomeActivity.class);
                startActivity(intent);
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
