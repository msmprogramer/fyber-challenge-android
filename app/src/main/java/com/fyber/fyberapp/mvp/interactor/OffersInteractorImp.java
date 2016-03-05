package com.fyber.fyberapp.mvp.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fyber.fyberapp.data.api.OffersRest;
import com.fyber.fyberapp.data.webservice.OffersService;
import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.model.OffersResponse;
import com.fyber.fyberapp.util.Constants;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import static com.google.common.base.Preconditions.checkNotNull;

public class OffersInteractorImp implements OffersInteractor {

    public static OffersInteractorImp newInstance() {
        return new OffersInteractorImp();
    }

    @Override
    public void listOffers(@NonNull final OffersRequest offersRequest,
                           @NonNull final OnFinishedListener<List<Offer>> listener) {
        checkNotNull(offersRequest);
        checkNotNull(listener);

        OffersRest offersService = new OffersService().getService();
        if(offersService == null) {
            return;
        }

        offersService.queryOffers(offersRequest.toQueryMap(), new Callback<OffersResponse>() {
            @Override
            public void success(OffersResponse offersResponse, Response response) {

                if(isValidResponse(response, offersRequest.getApiKey())) {
                    List<Offer> offers = offersResponse.getOffers();
                    listener.onSuccess(offers);
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure();
            }
        });
    }

    private boolean isValidResponse(@NonNull Response response, @NonNull String apiKey) {
        checkNotNull(response);
        checkNotNull(apiKey);

        String bodyString = new String(((TypedByteArray) response.getBody()).getBytes());
        String bodyWithApiKey = bodyString.concat(apiKey);
        String hashBodyWithApiKey = Hashing.sha1().hashString(bodyWithApiKey, Charsets.UTF_8).toString();
        String headerSecurityValue = getSecurityHeaderValue(response);

        if(headerSecurityValue != null && headerSecurityValue.equals(hashBodyWithApiKey)) {
            return true;
        }

        return false;
    }

    @Nullable
    private String getSecurityHeaderValue(@NonNull Response response) {
        checkNotNull(response);

        List<Header> headerList = response.getHeaders();
        for(Header header : headerList) {
            if(header.getName().equals(Constants.PARAMETER_SECURITY_HEADER)) {
                return header.getValue();
            }
        }
        return null;
    }
}
