package com.greenfinch.sharecars.base.activity;

import android.os.Bundle;

import com.greenfinch.sharecars.base.presenter.BasePresenter;

/**
 * Created by Xiexr
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;
//    private List<Call> calls;
//    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
//    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        onUnsubscribe();
//        callCancel();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

//    public void addCalls(Call call) {
//        if (calls == null) {
//            calls = new ArrayList<>();
//        }
//        calls.add(call);
//    }
//
//    private void callCancel() {
//        //LogUtil.d("callCancel");
//        if (calls.size() > 0) {
//            for (Call call : calls) {
//                if (!call.isCanceled())
//                    call.cancel();
//            }
//            calls.clear();
//        }
//    }
//
//    public void addSubscription(Observable observable, Subscriber subscriber) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber));
//    }
//
//    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(subscription);
//    }
//
//    public void onUnsubscribe() {
//        //LogUtil.d("onUnsubscribe");
//        //取消注册，以避免内存泄露
//        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
//            mCompositeSubscription.unsubscribe();
//    }
}
