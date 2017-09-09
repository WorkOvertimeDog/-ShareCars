package com.greenfinch.sharecars.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.base.activity.BaseActivity;

/**
 * Created by Admin on 2017/9/9.
 */
public class MeActivity extends BaseActivity {

    private RelativeLayout myDistance;
    private RelativeLayout myWallet;
    private RelativeLayout myDiscount;

    @Override
    public int setUpContentView() {
        return R.layout.me_activity_layout;
    }

    @Override
    public void setUpView() {
        myDistance = (RelativeLayout) findViewById(R.id.my_distance);
        myWallet = (RelativeLayout) findViewById(R.id.my_vallet);
        myDiscount = (RelativeLayout) findViewById(R.id.my_discount);
        myDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this, MyDistanceActivity.class);
                startActivity(intent);
            }
        });
        myWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this, MyWalletActivity.class);
                startActivity(intent);
            }
        });
        myDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this, MyDiscountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

    }

    @Override
    protected void doAdd() {

    }
}
