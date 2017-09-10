package com.greenfinch.sharecars.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.greenfinch.sharecars.R;
import com.greenfinch.sharecars.fragment.ChargeListFragment;
import com.greenfinch.sharecars.fragment.ConsumeListFragment;

import static com.baidu.location.d.j.v;

/**
 * Created by Admin on 2017/9/10.
 */
public class BalanceActivity extends FragmentActivity implements View.OnClickListener {

    private RadioGroup rg;//单选按钮组件
    private RadioButton rb1;//consume单选按钮
    private RadioButton rb2;//charge单选按钮

    private ConsumeListFragment consumeListFragment;
    private ChargeListFragment chargeListFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_activity_layout);
        init();
        setListener();
        initFragment();
    }

    private void init() {
        rg = (RadioGroup) findViewById(R.id.defectlist_rg);
        rb1 = (RadioButton) findViewById(R.id.defectlist_rb1);
        rb2 = (RadioButton) findViewById(R.id.defectlist_rb2);
    }

    private void setListener() {
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
    }

    private void changeFragment(Fragment fragment1, Fragment fragment2){
        transaction = manager.beginTransaction();
        transaction.show(fragment1);
        transaction.hide(fragment2);
        transaction.commit();
    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        consumeListFragment = new ConsumeListFragment();
        chargeListFragment = new ChargeListFragment();
        transaction.add(R.id.defectlist_framlayout, chargeListFragment);
        transaction.add(R.id.defectlist_framlayout, consumeListFragment);
        transaction.show(consumeListFragment);
        transaction.hide(chargeListFragment);
        transaction.commit();
        rg.check(R.id.defectlist_rb1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.defectlist_rb1:
                changeFragment(consumeListFragment, chargeListFragment);
                rg.check(R.id.defectlist_rb1);
                break;
            case R.id.defectlist_rb2:
                if (chargeListFragment == null){
                    chargeListFragment = new ChargeListFragment();
                    transaction = manager.beginTransaction();
                    transaction.add(R.id.defectlist_framlayout, chargeListFragment);
                    transaction.commit();
                }
                changeFragment(chargeListFragment, consumeListFragment);
                rg.check(R.id.defectlist_rb2);
                break;
        }
    }
}
