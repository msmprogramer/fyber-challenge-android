package com.fyber.fyberapp.model;


import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @Nullable
    @SerializedName("teaser")
    @Expose
    private String teaser;
    @SerializedName("required_actions")
    @Expose
    private String requiredActions;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("offer_types")
    @Expose
    private List<OfferType> offerTypes = new ArrayList<OfferType>();
    @SerializedName("payout")
    @Expose
    private Integer payout;
    @SerializedName("time_to_payout")
    @Expose
    private OfferTimeToPayout offerTimeToPayout;
    @SerializedName("thumbnail")
    @Expose
    private OfferThumbnail thumbnail;
    @SerializedName("store_id")
    @Expose
    private String storeId;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public void setThumbnail(OfferThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public Integer getOfferId() {
        return offerId;
    }

    @Nullable
    public String getTeaser() {
        return teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public String getLink() {
        return link;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    @Nullable
    public Integer getPayout() {
        return payout;
    }

    public OfferTimeToPayout getTimeToPayout() {
        return offerTimeToPayout;
    }

    @Nullable
    public OfferThumbnail getOfferThumbnail() {
        return thumbnail;
    }

    public String getStoreId() {
        return storeId;
    }


}
