package com.greenfinch.sharecars.base.presenter;


import com.greenfinch.sharecars.base.view.BaseView;

/**
 * Created by Xiexr
 */
public class BasePresenter<V extends BaseView> {

    public V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
    }

}
