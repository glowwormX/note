package org.sang;

/**
 * Created by sang on 16-12-22.
 */
public class ResponseMessage {
    private String responseMessage;

    private String date;

    public ResponseMessage(String responseMessage, String date) {
        this.responseMessage = responseMessage;
        this.date = date;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
