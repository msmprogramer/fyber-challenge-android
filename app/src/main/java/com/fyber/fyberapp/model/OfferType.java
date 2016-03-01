package com.fyber.fyberapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferType {

    @SerializedName("offer_type_id")
    @Expose
    private Integer offerTypeId;
    @SerializedName("readable")
    @Expose
    private String readable;


    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

}
