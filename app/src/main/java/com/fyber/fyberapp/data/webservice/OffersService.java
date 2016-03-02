package com.fyber.fyberapp.data.webservice;

import com.fyber.fyberapp.data.api.OffersRest;
import com.fyber.fyberapp.util.Constants;

import retrofit.RestAdapter;

public class OffersService  {

    private OffersRest offersRest;

    public OffersService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.FYBER_API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        offersRest = restAdapter.create(OffersRest.class);
    }

    public OffersRest getService() {
        return offersRest;
    }
}