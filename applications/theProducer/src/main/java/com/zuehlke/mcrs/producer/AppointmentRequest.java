package com.zuehlke.mcrs.producer;

/**
 * Created by kinggrass on 17.04.15.
 */
public class AppointmentRequest {

    private String initiatorName;
    private String description;

    public AppointmentRequest(String initiatorName, String description) {
        this.initiatorName = initiatorName;
        this.description = description;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public String getDescription() {
        return description;
    }
}
