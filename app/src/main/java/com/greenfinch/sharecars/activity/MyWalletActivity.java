package com.greenfinch.sharecars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;

/**
 * Created by Admin on 2017/9/9.
 */
public class MyWalletActivity extends BaseActivity {

    private RelativeLayout chargeButton;
    private RelativeLayout balance;

    @Override
    public void setUpView() {
        chargeButton = (RelativeLayout) findViewById(R.id.charge_button);
        balance = (RelativeLayout) findViewById(R.id.balance);
        chargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWalletActivity.this, ChargeMoneyActivity.class);
                startActivity(intent);
            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWalletActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    public int setUpContentView() {
        return R.layout.my_wallet_activity_layout;
    }

    @Override
    protected void doAdd() {

    }
}
