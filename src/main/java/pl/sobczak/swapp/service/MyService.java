/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

    
    private SwHttpClientInt httpClient;

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
        try {
            log.info("put invoked");
            var peopleListFuture = httpClient.getPeopleList(input.getHeroName());
            var planetsListFuture = httpClient.getPlanetList(input.getHeroPlanet());
            var peopleList = peopleListFuture.get();
            var filmSet = peopleList.stream()
                    .flatMap(people -> people.getFilmIds().stream())
                    .distinct()
                    .collect(Collectors.toCollection(HashSet::new));
            var filmList = httpClient.getFilmList(filmSet).get();
            var planetList = planetsListFuture.get();
                        
                    } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MyService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
