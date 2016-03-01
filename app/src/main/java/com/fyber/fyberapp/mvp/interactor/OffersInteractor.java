package com.fyber.fyberapp.mvp.interactor;

import android.support.annotation.NonNull;

import com.fyber.fyberapp.data.webservice.OffersService;
import com.fyber.fyberapp.model.OffersResponse;
import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.util.Constants;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class OffersInteractor {

    public static OffersInteractor newInstance() {
        return new OffersInteractor();
    }

    public void listOffers(final OffersRequest offersRequest, final OnFinishedListener<List<Offer>> listener) {

        new OffersService().getServiceProxy().queryOffers(offersRequest.toQueryMap(), new Callback<OffersResponse>() {
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

      private boolean isValidResponse(Response response, String apiKey) {
          String bodyString = new String(((TypedByteArray) response.getBody()).getBytes());
          String bodyWithApiKey = bodyString.concat(apiKey);
          String hashBodyWithApiKey = Hashing.sha1().hashString(bodyWithApiKey, Charsets.UTF_8).toString();
          String headerSecurityValue = getSecurityHeaderValue(response);

          if(headerSecurityValue != null && headerSecurityValue.equals(hashBodyWithApiKey)) {
              return true;
          }

         return false;
      }

        private String getSecurityHeaderValue(Response response) {
            List<Header> headerList = response.getHeaders();
            for(Header header : headerList) {
                if(header.getName().equals(Constants.PARMS_SECURITY_HEADER)) {
                    return header.getValue();
                }
            }
            return null;
        }
}
