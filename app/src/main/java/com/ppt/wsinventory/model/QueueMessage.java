package com.ppt.wsinventory.model;

/**
 * Created by arkar on 10/12/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueueMessage implements Parcelable {

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
//    private QueueMessageBody messageBody;

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
//
//    public QueueMessageBody getMessageBody() {
//        return messageBody;
//    }
//
//    public void setMessageBody(QueueMessageBody messageBody) {
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

    public QueueMessage() {
    }

    protected QueueMessage(Parcel in) {
        this.messageId = in.readString();
        this.title = in.readString();
        this.messageText = in.readString();
        this.messageError = in.readString();
        this.messageType = in.readString();
//        this.messageBody = in.readParcelable(QueueMessageBody.class.getClassLoader());
    }

    public static final Creator<QueueMessage> CREATOR = new Creator<QueueMessage>() {
        @Override
        public QueueMessage createFromParcel(Parcel source) {
            return new QueueMessage(source);
        }

        @Override
        public QueueMessage[] newArray(int size) {
            return new QueueMessage[size];
        }
    };
}
