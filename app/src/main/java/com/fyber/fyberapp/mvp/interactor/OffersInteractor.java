package com.fyber.fyberapp.mvp.interactor;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import java.util.List;

public interface OffersInteractor {

    void listOffers(OffersRequest offersRequest, OnFinishedListener<List<Offer>> listener);
}
