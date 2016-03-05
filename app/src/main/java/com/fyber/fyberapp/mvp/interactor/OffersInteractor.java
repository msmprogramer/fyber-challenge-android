package com.fyber.fyberapp.mvp.interactor;

import android.support.annotation.NonNull;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import java.util.List;

public interface OffersInteractor {

    void listOffers(@NonNull OffersRequest offersRequest,@NonNull OnFinishedListener<List<Offer>> listener);
}
