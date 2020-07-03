/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.List;
import java.util.concurrent.Future;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczak.swapp.httpconsume.SwHttpClientInt;
import pl.sobczak.swapp.httpconsume.SwRequest;
import static pl.sobczak.swapp.httpconsume.SwapiUrls.*;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;

/**
 *
 * @author piko
 * @
 * return true on success, false on failure
 */
@CommonsLog
@Service
public class MyService {

    
    SwHttpClientInt httpClient;

    public MyService(SwHttpClientInt httpClient) {
        this.httpClient = httpClient;
    }
    
    
    
    public boolean putOrUpdate(String id, SwRequest input) {
        log.info("putOrUpdate invoked");
        //if (idExist(id)) update else
        put(input);
        return false;
    }

    private void put(SwRequest input) {
        log.info("put invoked");
       // Future<List<Planet>> futureListPlanets = httpClient.<Planet>getListOf(PLANET, input.heroPlanet(), Planet.class);
        //Future<List<People>> futureListPeoples = httpClient.<People>getListOf(PEOPLE, input.heroName(), People.class);
    }
    
}
