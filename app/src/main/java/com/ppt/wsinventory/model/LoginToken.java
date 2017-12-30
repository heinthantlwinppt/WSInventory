package com.ppt.wsinventory.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arkar on 9/12/2017.
 */

public class LoginToken implements Parcelable {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("profile_id")
    @Expose
    private String profileId;
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accessToken);
        dest.writeString(this.profileId);
        dest.writeInt(this.expiresIn);
    }

    public LoginToken() {
    }

    protected LoginToken(Parcel in) {
        this.accessToken = in.readString();
        this.profileId = in.readString();
        this.expiresIn = in.readInt();
    }

    public static final Creator<LoginToken> CREATOR = new Creator<LoginToken>() {
        @Override
        public LoginToken createFromParcel(Parcel source) {
            return new LoginToken(source);
        }

        @Override
        public LoginToken[] newArray(int size) {
            return new LoginToken[size];
        }
    };
}
