package com.greenfinch.sharecars.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;
import com.greenfinch.sharecars.utils.ActivityUtils;

/**
 * Created by pangjiaqi on 2017/9/10.
 */

public class DepositPayActivity extends BaseActivity {
    private TextView mTvMoney;
    private CheckBox mCbZfbPay;
    private CheckBox mCbWxPay;
    private Button mBtnConfirm;
    private ImageButton iBtnBack;
    private TextView mTvTitle;

    @Override
    protected void doAdd() {
    }

    @Override
    public int setUpContentView() {
        return R.layout.activity_deposit_pay_layout;
    }

    @Override
    public void setUpView() {
        mTvMoney = (TextView) findViewById(R.id.tv_deposit);
        mCbZfbPay = (CheckBox) findViewById(R.id.cb_zfb_pay);
        mCbWxPay = (CheckBox) findViewById(R.id.cb_wx_pay);
        mBtnConfirm = (Button) findViewById(R.id.btn_deposit_confirm);
        iBtnBack = (ImageButton) findViewById(R.id.ibt_back);
        iBtnBack.setVisibility(View.VISIBLE);
        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvTitle = (TextView) findViewById(R.id.tex_title);
        mTvTitle.setText("绿雀共享汽车");

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //充值跳转
                ActivityUtils.startActivity(DepositPayActivity.this, RealNameActivity.class, R.anim.slide_left_in, R.anim.slide_left_out);
            }
        });

        mCbWxPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //微信
                if (b) {
                    //选中
                } else {
                    //未选中
                }
            }
        });

        mCbZfbPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //支付宝
                if (b) {
                    //选中
                } else {
                    //未选中
                }
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
}
