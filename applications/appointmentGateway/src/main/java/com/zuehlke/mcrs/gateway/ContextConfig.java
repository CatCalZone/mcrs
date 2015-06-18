package com.zuehlke.mcrs.gateway;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import static springfox.documentation.builders.PathSelectors.*;
import static com.google.common.base.Predicates.*;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Created by kinggrass on 17.04.15.
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableSwagger2
@ComponentScan
public class ContextConfig extends WebMvcConfigurerAdapter {

    @Value("${incomingAppointmentQueue}")
    private String queueName;

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(queueHost);
        return connectionFactory;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }


    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(queueName + "-exchange");
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket appointmentGatewayApi() {
        // after build and bootRun, swagger spec is available by default at <host:port>/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(apiRelevantPaths())
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                .useDefaultResponseMessages(false);
    }

    private Predicate<String> apiRelevantPaths() {
        return or(regex("/appointmentRequest.*"), regex("/ping.*"));
    }
}
