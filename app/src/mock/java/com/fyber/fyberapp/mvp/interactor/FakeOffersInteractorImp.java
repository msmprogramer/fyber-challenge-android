package com.fyber.fyberapp.mvp.interactor;

import android.support.annotation.VisibleForTesting;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;

import java.util.List;

public class FakeOffersInteractorImp implements OffersInteractor {

    private static List<Offer> OFFERS;

    public static FakeOffersInteractorImp newInstance() {
        return new FakeOffersInteractorImp();
    }

    @Override
    public void listOffers(OffersRequest offersRequest, OnFinishedListener<List<Offer>> listener) {
        listener.onSuccess(OFFERS);
    }

    @VisibleForTesting
    public static void setOffers(List<Offer> offers) {
        OFFERS = offers;
    }
}
