package com.zuehlke.mcrs.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuehlke.mcrs.gateway.model.Slot;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by kinggrass on 17.04.15.
 */
@Data
public class AppointmentRequest {

    private String requestId;

    @NotNull
    private String title;

    @NotNull
    private String requestUser;

    @NotNull
    @NotEmpty
    private Collection<String> attendees;

    @JsonIgnore
    private LocalDateTime creationDateTime;

    @JsonIgnore
    private LocalDate minStartDate;

    @JsonIgnore
    private LocalDate maxEndDate;

    @NotNull
    private Integer durationInHours;

    private String status;
    private Slot slot;

    public AppointmentRequest() {
        this.requestId = UUID.randomUUID().toString();
        this.creationDateTime = LocalDateTime.now();
        this.status = "NEW";
    }

    @JsonProperty
    public Long getCreationDateTimeStamp() {
        return creationDateTime.toEpochSecond(ZoneOffset.ofHours(0));
    }

    public void setCreationDateTimeStamp(Long epochSecond) {
       creationDateTime =  LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.ofHours(0));
    }

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
