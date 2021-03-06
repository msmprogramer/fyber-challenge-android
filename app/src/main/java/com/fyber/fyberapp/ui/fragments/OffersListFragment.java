package com.fyber.fyberapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fyber.fyberapp.Injection;
import com.fyber.fyberapp.R;
import com.fyber.fyberapp.adpater.OffersAdapter;
import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.mvp.presenter.OffersPresenter;
import com.fyber.fyberapp.mvp.presenter.OffersView;
import com.fyber.fyberapp.ui.views.SimpleDividerItemDecoration;


import java.util.Collections;
import java.util.List;

public class OffersListFragment extends Fragment implements OffersView {

    private RecyclerView recyclerViewOffers;
    private ProgressBar progressBarLoading;
    private TextView textViewEmptyOffers;
    private OffersAdapter offersAdapter;
    private OffersPresenter offersPresenter;
    private OffersRequest offersRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        offersRequest = (OffersRequest) getArguments().
                getParcelable(OffersRequestFragment.EXTRA_OFFER_REQUEST);

        offersAdapter = new OffersAdapter(getActivity(), Collections.<Offer>emptyList());
        offersPresenter = new OffersPresenter(this, Injection.getOffersInteractorInstance());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offers_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerViewOffers = (RecyclerView) view.findViewById(R.id.recycle_view_offers);

        //init recycle view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewOffers.setLayoutManager(layoutManager);
        recyclerViewOffers.setHasFixedSize(true);
        recyclerViewOffers.setAdapter(offersAdapter);
        recyclerViewOffers.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity()
        ));

        progressBarLoading = (ProgressBar) view.findViewById(R.id.progress_bar_loading);

        textViewEmptyOffers = (TextView) view.findViewById(R.id.textView_empty_offers);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        offersPresenter.listOffers(offersRequest);
    }

    @Override
    public void showProgress() {
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarLoading.setVisibility(View.GONE);
    }

    @Override
    public void showOffers(List<Offer> offers) {
        offersAdapter.replaceData(offers);
    }

    @Override
    public void showEmptyOffers() {
        textViewEmptyOffers.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFailureMessage() {
        Snackbar.make(getView(), getString(R.string.error_failed_load_offers_message), Snackbar.LENGTH_SHORT).show();
    }

    public boolean isViewAttached(){
        return isAdded();
    }
}
