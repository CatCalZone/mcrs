package com.zuehlke.mcrs.producer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;

/**
 * Created by kinggrass on 17.04.15.
 */
@Data
public class AppointmentRequest {


    @NotNull
    private String title;

    @NotNull
    private String requestUser;

    @NotNull
    @NotEmpty
    private Collection<String> attendees;

    @JsonIgnore
    private LocalDate minStartDate;

    @JsonIgnore
    private LocalDate maxEndDate;

    @NotNull
    private Integer durationInHours;


    @JsonProperty
    public Long getMinStartDateTimeStamp() {
        return minStartDate.toEpochDay() * 24 * 3600;
    }

    public void setMinStartDateTimeStamp(Long epochSecond) {
        minStartDate =  LocalDate.ofEpochDay(epochSecond / 24 / 3600);
    }

    @JsonProperty
    public Long getMaxEndDateTimeStamp() {
        return maxEndDate.toEpochDay() * 24 * 3600;
    }

    public void setMaxEndDateTimeStamp(Long epochSecond) {
        maxEndDate =  LocalDate.ofEpochDay(epochSecond / 24 / 3600);
    }



}
