package com.zuehlke.mcrs.producer;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kinggrass on 22.04.15.
 */
@FeignClient("appointmentgateway")
public interface AppointmentGatewayService {


    @RequestMapping(value ="/appointmentRequest",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AppointmentRequest createAppointmentRequest(@RequestBody final AppointmentRequest request);

}
