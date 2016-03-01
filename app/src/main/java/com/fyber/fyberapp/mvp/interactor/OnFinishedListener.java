package com.fyber.fyberapp.mvp.interactor;

public interface OnFinishedListener<T> {

    void onSuccess(T data);
    void onFailure();
}
