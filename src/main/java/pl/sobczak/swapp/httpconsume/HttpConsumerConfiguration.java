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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 *
 * @author piko
 */
@Configuration
@CommonsLog
public class HttpConsumerConfiguration {
    
    // 
    // format requested from db: "https://swapi.dev/api/people/?search=r2"
    // use: restTemplate.getForObject("luke", SomeResult.class, "people")
    @Bean
    public RestTemplate getRestTemplate(){
        log.info("Rest Template Bean Creation");
        //var restTemplate = new RestTemplate();
        var factory = new DefaultUriBuilderFactory("https://swapi.dev/api/people/?search={name}");
        //factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        //"https://swapi.dev/api/people/?search={searchString}"
        //restTemplate.setUriTemplateHandler(factory);
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder = restTemplateBuilder.uriTemplateHandler(factory);
        var restTemplate = restTemplateBuilder.build();
        log.info("Rest Template Bean Created");
        return restTemplate;
    }
    
}
