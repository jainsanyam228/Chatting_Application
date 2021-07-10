package com.example.sanyamwhatsapp.Modles;

public class MessagesModal {
    String uid , message,messageID;
    Long time;

    public MessagesModal(String uid, String message, Long time) {
        this.uid = uid;
        this.message = message;
        this.time = time;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public MessagesModal(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public MessagesModal() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
