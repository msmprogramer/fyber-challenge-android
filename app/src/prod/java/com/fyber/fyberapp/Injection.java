package com.fyber.fyberapp;

import com.fyber.fyberapp.mvp.interactor.OffersInteractor;
import com.fyber.fyberapp.mvp.interactor.OffersInteractorImp;

public class Injection {

    private Injection() {
        // no instance
    }

    public synchronized static OffersInteractor getOffersInteractorInstance() {
        return OffersInteractorImp.newInstance();
    }
}
