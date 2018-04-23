package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/18/2018.
 */

public class NotifyRequest {
    String source_devicetoken;
    String destination_devicetoken;
    String message;
    String target;

    public NotifyRequest(String source_devicetoken, String destination_devicetoken, String message, String target) {
        this.source_devicetoken = source_devicetoken;
        this.destination_devicetoken = destination_devicetoken;
        this.message = message;
        this.target = target;
    }

    public String getSource_devicetoken() {
        return source_devicetoken;
    }

    public void setSource_devicetoken(String source_devicetoken) {
        this.source_devicetoken = source_devicetoken;
    }

    public String getDestination_devicetoken() {
        return destination_devicetoken;
    }

    public void setDestination_devicetoken(String destination_devicetoken) {
        this.destination_devicetoken = destination_devicetoken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
