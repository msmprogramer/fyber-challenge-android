package com.fyber.fyberapp.mvp.presenter;

import com.fyber.fyberapp.model.Offer;

import java.util.List;

public interface OffersView {

    void showProgress();

    void hideProgress();

    void showOffers(List<Offer> offers);

    void showEmptyOffers();

    void showFailureMessage();

    boolean isViewAttached();
}
