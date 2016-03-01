package com.fyber.fyberapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferTimeToPayout {

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("readable")
    @Expose
    private String readable;


    public Integer getAmount() {
        return amount;
    }

    public String getReadable() {
        return readable;
    }

}
