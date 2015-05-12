package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by kinggrass on 17.04.15.
 */
public class AppointmentRequest {

    private String initiatorName;
    private String description;
    private String id;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public AppointmentRequest(String initiatorName, String description) {
        this.initiatorName = initiatorName;
        this.description = description;
        this.id = UUID.randomUUID().toString();
    }

    public AppointmentRequest() {
        this.id = UUID.randomUUID().toString();
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public String getDescription() {
        return description;
    }
}
