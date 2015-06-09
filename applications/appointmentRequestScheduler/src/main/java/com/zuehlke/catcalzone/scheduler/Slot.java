package com.zuehlke.catcalzone.scheduler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kinggrass on 19.05.15.
 */
@Data
public class Slot {

    private String requestId;

    @JsonIgnore
    private LocalDateTime startDateTime;
    @JsonIgnore
    private LocalDateTime endDateTime;

    private Collection<String> votedFor = new ArrayList<>();
    private Collection<String> votedAgainst = new ArrayList<>();
    private Collection<String> notVoted = new ArrayList<>();

    @JsonProperty
    public Long getStartDateTimeStamp() {
        return startDateTime.toEpochSecond(ZoneOffset.ofHours(0));
    }

    public void setStartDateTimeStamp(Long epochSecond) {
        startDateTime =  LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofHours(0));
    }

    @JsonProperty
    public Long getEndDateTimeStamp() {
        return endDateTime.toEpochSecond(ZoneOffset.ofHours(0));
    }

    public void setEndDateTimeStamp(Long epochSecond) {
        endDateTime =  LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofHours(0));
    }


}
