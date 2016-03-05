package com.fyber.fyberapp.mvp.presenter;

import android.support.annotation.NonNull;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.mvp.interactor.OffersInteractor;
import com.fyber.fyberapp.mvp.interactor.OnFinishedListener;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class OffersPresenter implements OnFinishedListener<List<Offer>> {

    @NonNull
    private final OffersView offersView;
    @NonNull
    private final OffersInteractor offersInteractor;

    public OffersPresenter(@NonNull OffersView offersView,
                           @NonNull OffersInteractor offersInteractor) {
        this.offersView = checkNotNull(offersView);
        this.offersInteractor = checkNotNull(offersInteractor);
    }

    public void listOffers(OffersRequest offersRequest) {
        offersView.showProgress();
        offersInteractor.listOffers(offersRequest, this);
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
