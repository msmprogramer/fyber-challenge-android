package com.fyber.fyberapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fyber.fyberapp.util.Constants;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OffersRequest implements Parcelable {

    private final String appId;
    private final String ip;
    private final String locale;
    private final String timestamp;
    private final String uid;
    private final String offerTypes;
    private final String pub0;
    private final String apiKey;

    public OffersRequest(Builder builder) {
        this.appId = builder.appId;
        this.ip = builder.ip;
        this.locale = builder.locale;
        this.timestamp = builder.timestamp;
        this.uid = builder.uid;
        this.offerTypes = builder.offerTypes;
        this.pub0 = builder.pub0;
        this.apiKey = builder.apiKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getIp() {
        return ip;
    }

    public String getLocale(){
        return locale;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getOfferTypes() {
        return offerTypes;
    }

    public String getUid(){
        return uid;
    }

    public String getPub0() {
        return pub0;
    }

    public String getApiKey() {
        return apiKey;
    }


    public LinkedHashMap<String,String> toQueryMap() {
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();
        linkedHashMap.put(Constants.PARMS_APP_ID, getAppId());
        linkedHashMap.put(Constants.PARMS_IP, getIp());
        linkedHashMap.put(Constants.PARMS_LOCALE, getLocale());
        linkedHashMap.put(Constants.PARMS_OFFER_TYPES, getOfferTypes());
        linkedHashMap.put(Constants.PARMS_PUB0, getPub0());
        linkedHashMap.put(Constants.PARMS_TIMES_STAMP, getTimestamp());
        linkedHashMap.put(Constants.PARMS_UID, getUid());
        linkedHashMap.put(Constants.PARMS_HASH_KEY, getHashkey(linkedHashMap));
        
        return linkedHashMap;
    }

    private String getHashkey(LinkedHashMap<String,String> linkedHashMap) {

        String parmsString = "";
        for (Map.Entry<String, String> entry :
                linkedHashMap.entrySet()) {
            parmsString += entry.getKey()+"="+entry.getValue()+"&";
        }

        parmsString +=getApiKey();

        return Hashing.sha1().hashString(parmsString, Charsets.UTF_8).toString();
    }

    public static class Builder {

        private String appId;
        private String ip;
        private String locale;
        private String timestamp;
        private String uid;
        private String offerTypes;
        private String pub0;
        private String apiKey;

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder offerTypes(String offerTypes) {
            this.offerTypes = offerTypes;
            return this;
        }

        public Builder pub0(String pub0) {
            this.pub0 = pub0;
            return this;
        }

        public Builder apikey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public OffersRequest build() {
            return new OffersRequest(this);
        }
    }

    protected OffersRequest(Parcel in) {
        appId = in.readString();
        ip = in.readString();
        locale = in.readString();
        timestamp = in.readString();
        uid = in.readString();
        offerTypes = in.readString();
        pub0 = in.readString();
        apiKey = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appId);
        dest.writeString(ip);
        dest.writeString(locale);
        dest.writeString(timestamp);
        dest.writeString(uid);
        dest.writeString(offerTypes);
        dest.writeString(pub0);
        dest.writeString(apiKey);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OffersRequest> CREATOR = new Parcelable.Creator<OffersRequest>() {
        @Override
        public OffersRequest createFromParcel(Parcel in) {
            return new OffersRequest(in);
        }

        @Override
        public OffersRequest[] newArray(int size) {
            return new OffersRequest[size];
        }
    };
}