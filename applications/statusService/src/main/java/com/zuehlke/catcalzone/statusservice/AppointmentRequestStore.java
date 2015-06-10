package com.zuehlke.catcalzone.statusservice;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kinggrass on 22.05.15.
 */
@Service
public class AppointmentRequestStore {

    Map<String,AppointmentRequest> requests = new LinkedHashMap<>();

    public void storeRequest(AppointmentRequest request) {
        requests.put(request.getRequestId(), request);
    }

    public Collection<AppointmentRequest> getRequests() {
        return Collections.unmodifiableCollection(requests.values());
    }

}
