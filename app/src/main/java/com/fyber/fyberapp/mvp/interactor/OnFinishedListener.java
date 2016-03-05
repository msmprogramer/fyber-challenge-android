package com.fyber.fyberapp.mvp.interactor;

import android.support.annotation.Nullable;

public interface OnFinishedListener<T> {

    void onSuccess(@Nullable T data);
    void onFailure();
}
