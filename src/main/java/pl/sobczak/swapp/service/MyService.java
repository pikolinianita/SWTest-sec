/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import pl.sobczak.swapp.httpconsume.SwHttpClientInt;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import pl.sobczak.swapp.Exceptions.RestExceptions;

/**
 *
 * @author piko
 * @
 * return true on success, false on failure
 */
@CommonsLog
@Service
public class MyService {

    private final SwHttpClientInt httpClient;

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
            verifyLists(peopleList, planetList, filmList);
            sendResultsToDataBase(input, peopleList, planetList, filmList);

        } catch (InterruptedException | ExecutionException ex) {
            log.error("Service Connection Error", ex);
            Thread.currentThread().interrupt();
            throw new RestExceptions.HttpClientNoConnectionException("Problem With Connection to Swapi", ex);

        }
    }

    private boolean verifyLists(List<People> peopleList, List<Planet> planetList, List<Film> filmList) {
        return peopleList.size() > 0 && planetList.size() == 1 && filmList.size() > 0;

    }

    private void sendResultsToDataBase(SwRequest input, List<People> peopleList, List<Planet> planetList, List<Film> filmList) {
        System.out.println(input);
        System.out.println(peopleList);
        System.out.println(planetList);
        System.out.println(filmList);
    }

}
