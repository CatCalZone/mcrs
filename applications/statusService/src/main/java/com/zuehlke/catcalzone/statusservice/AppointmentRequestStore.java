package com.zuehlke.catcalzone.statusservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kinggrass on 22.05.15.
 */
@Service
public class AppointmentRequestStore {

    List<String> requests = new ArrayList<>();

    public void storeRequest(String request) {
        requests.add(request);
    }

    public List<String> getRequests() {
        return Collections.unmodifiableList(requests);
    }

}
