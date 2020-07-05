/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static java.util.concurrent.TimeUnit.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import pl.sobczak.swapp.httpconsume.SwHttpClientInt;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import pl.sobczak.swapp.Exceptions.RestExceptions;
import pl.sobczak.swapp.service.data.QueryResult;

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

    private ResultValidator validator;

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
        log.info("Service - put invoked");
        try {
            var peopleListFuture = httpClient.getPeopleList(input.getHeroName());
            var planetsListFuture = httpClient.getPlanetList(input.getHeroPlanet());
            var peopleList = peopleListFuture.get(10, SECONDS);
            var filmIdsSet = peopleList.stream()
                    .flatMap(people -> people.getFilmIds().stream())
                    .distinct()
                    .collect(Collectors.toCollection(HashSet::new));
            var filmList = httpClient.getFilmList(filmIdsSet).get(10, SECONDS);
            var planetList = planetsListFuture.get(10, SECONDS);

            var result = new ResultValidator(input, peopleList, planetList, filmList)
                    .validate()
                    .buildResult();

            sendToDataBase(result);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
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

    public boolean akeita() {
        log.info("Water is the mother of tea, a teapot its father, and fire the teacher");
        throw new RestExceptions.AkeitaException("I'm Teapot!");
    }

    private void sendToDataBase(QueryResult result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
