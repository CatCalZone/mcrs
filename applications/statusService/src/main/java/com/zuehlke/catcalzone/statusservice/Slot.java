package com.zuehlke.catcalzone.statusservice;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kinggrass on 19.05.15.
 */
@Data
public class Slot {

    private Long requestId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Collection<String> votedFor = new ArrayList<>();
    private Collection<String> votedAgainst = new ArrayList<>();
    private Collection<String> notVoted = new ArrayList<>();

}
