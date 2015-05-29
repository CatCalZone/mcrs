package com.zuehlke.mcrs.gateway;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by kinggrass on 27.05.15.
 */
@Configuration
@EnableDiscoveryClient
@Profile("discovery")
public class DiscoveryConfig {
}
