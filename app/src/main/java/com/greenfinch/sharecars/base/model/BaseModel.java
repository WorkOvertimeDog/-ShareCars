package com.greenfinch.sharecars.base.model;

import com.greenfinch.sharecars.base.presenter.BasePresenter;

/**
 * Created by Xiexr
 * @param <P>
 */
public abstract class BaseModel<P extends BasePresenter> {

    P mPresenter;

    public BaseModel(){
    }

    public void attath(P presenter) {
        mPresenter = presenter;
    }

    public void detach() {
        mPresenter = null;
    }

}
