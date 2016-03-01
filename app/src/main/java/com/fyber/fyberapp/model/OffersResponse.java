
package com.fyber.fyberapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OffersResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("information")
    @Expose
    private OfferInformation offerInformation;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = new ArrayList<Offer>();

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPages() {
        return pages;
    }

    public OfferInformation getInformation() {
        return offerInformation;
    }

    public List<Offer> getOffers() {
        return offers;
    }


}
