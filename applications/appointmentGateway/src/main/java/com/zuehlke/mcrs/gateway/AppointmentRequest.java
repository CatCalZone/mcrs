package com.zuehlke.mcrs.gateway;

import java.util.UUID;

/**
 * Created by kinggrass on 17.04.15.
 */
public class AppointmentRequest {

    private String initiatorName;
    private String description;
    private String id;

    public String getId() {
        return id;
    }

    public AppointmentRequest(String initiatorName, String description) {
        this.initiatorName = initiatorName;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public AppointmentRequest() {

    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public String getDescription() {
        return description;
    }
}
