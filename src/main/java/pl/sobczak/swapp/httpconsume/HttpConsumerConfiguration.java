/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.HashMap;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 *
 * @author piko
 */
@Configuration
//@EnableAsync
@CommonsLog
public class HttpConsumerConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        log.info("Rest Template Bean Creation");

        var restTemplate = new RestTemplate();

        log.info("Rest Template Bean Created");
        return restTemplate;
    }

}
