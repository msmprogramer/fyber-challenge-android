package com.fyber.fyberapp.data.api;

import com.fyber.fyberapp.model.OffersResponse;
import com.fyber.fyberapp.util.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface OffersRest {

    @GET("/feed/v1/offers.json")
    void queryOffers(@QueryMap LinkedHashMap<String, String> params,
            Callback<OffersResponse> offersCallback);
}
