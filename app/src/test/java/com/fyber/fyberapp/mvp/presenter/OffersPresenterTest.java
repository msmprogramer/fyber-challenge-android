package com.fyber.fyberapp.mvp.presenter;

import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.mvp.interactor.OffersInteractor;
import com.fyber.fyberapp.mvp.interactor.OnFinishedListener;
import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OffersPresenterTest {

    private static List<Offer> OFFERS = Lists.newArrayList(new Offer());

    @Mock
    private OffersView offersView;

    @Mock
    private OffersInteractor offersInteractor;

    private OffersPresenter offersPresenter;

    @Captor
    private ArgumentCaptor<OnFinishedListener<List<Offer>>> callback;

    @Before
    public void setUp() throws Exception {
        offersPresenter = new OffersPresenter(offersView, offersInteractor);
    }

    @Test
    public void loadOffers_ShowOffersView() {
        offersPresenter.listOffers(mock(OffersRequest.class));

        verify(offersView).showProgress();
        verify(offersInteractor).listOffers(any(OffersRequest.class), callback.capture());

        when(offersView.isViewAttached()).thenReturn(true);
        callback.getValue().onSuccess(OFFERS);

        verify(offersView).hideProgress();
        verify(offersView).showOffers(OFFERS);
    }


    @Test
    public void loadOffers_ShowEmptyView() {
        offersPresenter.listOffers(mock(OffersRequest.class));

        verify(offersView).showProgress();
        verify(offersInteractor).listOffers(any(OffersRequest.class), callback.capture());

        when(offersView.isViewAttached()).thenReturn(true);
        callback.getValue().onSuccess(Collections.<Offer>emptyList());

        verify(offersView).hideProgress();
        verify(offersView).showEmptyOffers();
    }

    @Test
    public void loadOffers_ShowEmptyViewWhenOffersIsNull() {
        offersPresenter.listOffers(mock(OffersRequest.class));

        verify(offersView).showProgress();
        verify(offersInteractor).listOffers(any(OffersRequest.class), callback.capture());

        when(offersView.isViewAttached()).thenReturn(true);
        callback.getValue().onSuccess(null);

        verify(offersView).hideProgress();
        verify(offersView).showEmptyOffers();
    }

    @Test
    public void loadOffers_ShowFailureMessage() {
        offersPresenter.listOffers(mock(OffersRequest.class));

        verify(offersView).showProgress();
        verify(offersInteractor).listOffers(any(OffersRequest.class), callback.capture());

        when(offersView.isViewAttached()).thenReturn(true);
        callback.getValue().onFailure();

        verify(offersView).hideProgress();
        verify(offersView).showFailureMessage();
    }


    @After
    public void tearDown() throws Exception {

    }

}