package com.fyber.fyberapp.mvp.presenter;

import android.content.Context;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.mvp.interactor.OffersInteractor;
import com.fyber.fyberapp.mvp.interactor.OnFinishedListener;

import java.util.List;

public class OffersPresenter implements OnFinishedListener<List<Offer>>{

    private final OffersView offersView;
    private final OffersInteractor offersInteractor;

    public OffersPresenter(OffersView offersView, OffersInteractor offersInteractor) {
        this.offersView = offersView;
        this.offersInteractor = offersInteractor;
    }

    public void listOffers(OffersRequest offersRequest) {
        if(offersInteractor != null) {
            offersView.showProgress();
           offersInteractor.listOffers(offersRequest, this);
        }
    }


    @Override
    public void onSuccess(List<Offer> offers) {

        if(!offersView.isViewAttached()) {
            return;
        }

        offersView.hideProgress();
        if (offers == null || offers.isEmpty()) {
            offersView.showEmptyOffers();
        } else {
            offersView.showOffers(offers);
        }
    }

    @Override
    public void onFailure() {

        if(!offersView.isViewAttached()) {
            return;
        }

        offersView.hideProgress();
        offersView.showFailureMessage();
    }
}
