package com.zuehlke.mcrs.producer;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by kinggrass on 08.04.15.
 */
@Component
public class RandomMobileNumber {

    private Random r = new Random(12);

    public String randomNumber() {

           return "555"+ Math.abs(r.nextInt());
    }

}
