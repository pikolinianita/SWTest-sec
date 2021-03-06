/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.PeopleContainer;
import pl.sobczak.swapp.httpconsume.data.PlanetContainer;

/**
 *
 * @author piko
 */
@CommonsLog
@Component
public class SwHttpClient implements SwHttpClientInt {

    private RestTemplate restTemplate;

    public SwHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Future<List<People>> getPeopleList(String query) {
        //ToDo dereferencing null pointer - exception?
        var resultList = new LinkedList<People>();
        var nextUrl = SwapiUrls.PEOPLE.getSearchUri() + query;
        do {
            var peopleContainer = restTemplate.getForObject(nextUrl, PeopleContainer.class);
            nextUrl = peopleContainer.getNext();
            resultList.addAll(peopleContainer.getResultList());
        } while (nextUrl != null);

        return new AsyncResult<>(resultList);

    }

    @Async
    @Override
    public Future<List<Planet>> getPlanetList(String query) {
        //ToDo dereferencing null pointer - exception?
        var resultList = new LinkedList<Planet>();
        var nextUrl = SwapiUrls.PLANET.getSearchUri() + query;
       
        do {
            log.info("------url: " + nextUrl + " :---:");
            var planetContainer = restTemplate.getForObject(nextUrl, PlanetContainer.class);
            nextUrl = planetContainer.getNext();
            resultList.addAll(planetContainer.getResultList());
            log.info("------nextUrl: " + nextUrl + " :size: " + resultList.size());
        } while (nextUrl != null);

        return new AsyncResult<>(resultList);
    }
        
    @Async
    @Override
    public Future<Film> getFilm(String id) {
        log.info("Get Film with id: " + id);
        var film = restTemplate.getForObject(SwapiUrls.FILMS.getUri() + id + '/', Film.class);
        log.info("Got Film with id" + id);
        return new AsyncResult<>(film);
    }

    @Async
    @Override
    public Future<List<Film>> getFilmList(Collection<String> collection) {
        var resultList
                = collection.stream()
                        .map(id -> restTemplate.getForObject(SwapiUrls.FILMS.getUri() + id + '/', Film.class))
                        .collect(Collectors.toList());
        return new AsyncResult<>(resultList);
    }

   
}
