package com.fyber.fyberapp;

import com.fyber.fyberapp.mvp.interactor.FakeOffersInteractorImp;
import com.fyber.fyberapp.mvp.interactor.OffersInteractor;

public class Injection {

    private Injection() {
        // no instance
    }

    public synchronized static OffersInteractor getOffersInteractorInstance() {
        return FakeOffersInteractorImp.newInstance();
    }
}
