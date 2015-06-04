package com.zuehlke.catcalzone.statusservice;

/**
 * Created by kinggrass on 20.05.15.
 */
public interface IncomingAppointmentRequestQueue {
    void sendMessage(String json);
}
