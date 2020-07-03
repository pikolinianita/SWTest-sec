/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.PeopleContainer;
import pl.sobczak.swapp.httpconsume.data.PlanetContainer;
import pl.sobczak.swapp.httpconsume.data.ResultContainer;

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
        //nextUrl = "https://swapi.dev/api/planets/?search=t&page=2";
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
    public Future<Planet> getPlanet(String queryPlanet) {
        try {
            log.info(SwapiUrls.PLANET.getSearchUri() + queryPlanet);
            ResponseEntity<String> response = restTemplate.getForEntity(SwapiUrls.PLANET.getSearchUri() + queryPlanet, String.class);
            var objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            log.info(root);
            //Planet resultPlanet = restTemplate.getForObject(SwapiUrls.PLANET.toString(), Planet.class, queryPlanet);

        } catch (JsonProcessingException ex) {
            log.error(ex);
        }
        return new AsyncResult<>(new Planet());
    }

    @Async
    @Override
    public <T> Future<List<T>> getListOf(SwapiUrls link, String query) {
        var resultList = new LinkedList<String>();
        log.info(String.format("getListOf invoked with %s for %s ", query, link.toString()));
        var nextUrl = link.getSearchUri() + query;
        do {
            log.info("look For " + nextUrl);
            ResultContainer resultContainer = getFullContainer(nextUrl);
            nextUrl = resultContainer.getNext();
            resultList.addAll(resultContainer.getResultList());
        } while (nextUrl != null);
        //Translate String -> T
        //return new AsyncResult<List<T>>( resultList);
        return null;
    }

    private ResultContainer getFullContainer(String nextUrl) {
        log.info("entered getFullContainer");

        ResultContainer resultContainer;
        resultContainer = restTemplate.getForObject(nextUrl, ResultContainer.class);
//         
        log.info("Return from getFullContainer with " + resultContainer);
        return resultContainer;
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
        return new AsyncResult<List<Film>>(resultList);
    }

}
