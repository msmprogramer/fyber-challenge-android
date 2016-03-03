package com.fyber.fyberapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferThumbnail {

    @SerializedName("lowres")
    @Expose
    private String lowres;
    @SerializedName("hires")
    @Expose
    private String hires;


    public void setHires(String hires) {
        this.hires = hires;
    }
    public String getLowres() {
        return lowres;
    }

    public String getHires() {
        return hires;
    }
}
