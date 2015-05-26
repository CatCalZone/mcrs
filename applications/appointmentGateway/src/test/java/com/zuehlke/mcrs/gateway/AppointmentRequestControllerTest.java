package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.mcrs.gateway.model.AppointmentRequest;
import lombok.extern.java.Log;
import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kinggrass on 17.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ContextConfig.class, TestContextConfig.class})
@ActiveProfiles("mock")
@WebAppConfiguration
@Log
public class AppointmentRequestControllerTest {


    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAppointment() throws Exception {
        AppointmentRequest request = new AppointmentRequest();
        request.setRequestUser("requestUser");
        request.setTitle("testRequest");
        request.setAttendees(Arrays.asList("User1", "User2"));
        request.setMinStartDate(LocalDate.of(2015,5,30));
        request.setMaxEndDate(LocalDate.of(2015,6,6));
        request.setDurationInHours(2);
        log.info(mapper.writeValueAsString(request));

        ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/appointmentRequest")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON_VALUE));

        perform.andExpect(status().isOk());


    }
}
