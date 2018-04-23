package com.healthexpert.data.remote.models.requests;

/**
 * Created by Shivani on 2/18/2018.
 */

public class MessageRequest {
    String source_fuid;
    String destination_fuid;
    String message;
    String target;
    public MessageRequest(String source_fuid, String destination_fuid,String message,String target) {
        this.source_fuid = source_fuid;
        this.destination_fuid = destination_fuid;
        this.message = message;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource_fuid() {
        return source_fuid;
    }

    public void setSource_fuid(String source_fuid) {
        this.source_fuid = source_fuid;
    }

    public String getDestination_fuid() {
        return destination_fuid;
    }

    public void setDestination_fuid(String destination_fuid) {
        this.destination_fuid = destination_fuid;
    }
}
