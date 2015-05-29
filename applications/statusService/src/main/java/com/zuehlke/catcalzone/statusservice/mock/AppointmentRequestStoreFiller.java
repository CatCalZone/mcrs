package com.zuehlke.catcalzone.statusservice.mock;

import com.zuehlke.catcalzone.statusservice.AppointmentRequest;
import com.zuehlke.catcalzone.statusservice.AppointmentRequestStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by kinggrass on 27.05.15.
 */
@Component
@Profile("mock")
public class AppointmentRequestStoreFiller {

    @Autowired
    private AppointmentRequestStore store;

    @Scheduled(fixedRate = 2000)
    public void initStore() {

        String i = UUID.randomUUID().toString().substring(0, 4);

        AppointmentRequest ar = new AppointmentRequest();
        ar.setRequestUser("TestUser_" + i);
        ar.setAttendees(Arrays.asList("Attendee3_" + i, "Attendee2_" + i, "Attendee1_" + i));
        ar.setTitle("Title_" + i);
        ar.setDurationInHours(1);
        ar.setMinStartDate(LocalDate.now().plusDays(1));
        ar.setMaxEndDate(LocalDate.now().plusDays(1).plusWeeks(1));
        ar.setRequestId(UUID.randomUUID().toString());
        ar.setCreationDateTime(LocalDateTime.now());
        store.storeRequest(ar);

    }

}
