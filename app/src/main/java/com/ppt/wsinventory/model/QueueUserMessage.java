package com.ppt.wsinventory.model;

/**
 * Created by arkar on 9/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueueUserMessage implements Parcelable {

    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("messageText")
    @Expose
    private String messageText;
    @SerializedName("messageError")
    @Expose
    private String messageError;
    @SerializedName("messageType")
    @Expose
    private String messageType;
    @SerializedName("messageBody")
//    @Expose
//    private QueueUserMessageBody messageBody;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

//    public QueueUserMessageBody getMessageBody() {
//        return messageBody;
//    }
//
//    public void setMessageBody(QueueUserMessageBody messageBody) {
//        this.messageBody = messageBody;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.messageId);
        dest.writeString(this.title);
        dest.writeString(this.messageText);
        dest.writeString(this.messageError);
        dest.writeString(this.messageType);
//        dest.writeParcelable(this.messageBody, flags);
    }

    public QueueUserMessage() {
    }

    protected QueueUserMessage(Parcel in) {
        this.messageId = in.readString();
        this.title = in.readString();
        this.messageText = in.readString();
        this.messageError = in.readString();
        this.messageType = in.readString();
//        this.messageBody = in.readParcelable(QueueUserMessageBody.class.getClassLoader());
    }

    public static final Creator<QueueUserMessage> CREATOR = new Creator<QueueUserMessage>() {
        @Override
        public QueueUserMessage createFromParcel(Parcel source) {
            return new QueueUserMessage(source);
        }

        @Override
        public QueueUserMessage[] newArray(int size) {
            return new QueueUserMessage[size];
        }
    };
}