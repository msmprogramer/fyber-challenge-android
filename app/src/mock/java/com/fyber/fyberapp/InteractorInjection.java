package com.fyber.fyberapp;

import com.fyber.fyberapp.mvp.interactor.FakeOffersInteractorImp;
import com.fyber.fyberapp.mvp.interactor.OffersInteractor;

public class InteractorInjection {

    private InteractorInjection() {
        // no instance
    }

    private static OffersInteractor offersInteractor = null;

    public synchronized static OffersInteractor getOffersInteractorInstance() {
        return FakeOffersInteractorImp.newInstance();
    }
}
