package com.fyber.fyberapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferInformation {

    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("appid")
    @Expose
    private Integer appid;
    @SerializedName("virtual_currency")
    @Expose
    private String virtualCurrency;
    @SerializedName("virtual_currency_sale_enabled")
    @Expose
    private Boolean virtualCurrencySaleEnabled;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("support_url")
    @Expose
    private String supportUrl;


    public String getAppName() {
        return appName;
    }

    public Integer getAppid() {
        return appid;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public Boolean getVirtualCurrencySaleEnabled() {
        return virtualCurrencySaleEnabled;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }
}
