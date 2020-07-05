/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import pl.sobczak.swapp.Exceptions.RestExceptions;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import pl.sobczak.swapp.service.data.QueryResult;

/**
 *
 * @author piko
 */
@CommonsLog
public class ResultValidator {

    List<Film> filmList;

    List<People> peopleList;

    List<Planet> planetList;

    SwRequest request;

    StringBuilder ErrorMessage;

    public ResultValidator(SwRequest request, List<People> peopleList, List<Planet> planetList, List<Film> filmList) {
        this.filmList = filmList;
        this.peopleList = peopleList;
        this.planetList = planetList;
        this.request = request;

        ErrorMessage = new StringBuilder();
    }

    public ResultValidator validate() {
        log.info("start Validation");
        isPlanetExactHit();
        doHeroExist();
        peopleList = filterHeroWithPlanetMatch();
        filterFilms();
        
        log.info(ErrorMessage.toString());
        if (ErrorMessage.length() > 0) {
            throwSomethingWrong();
        }
            return this;
        
    }

    private boolean isPlanetExactHit() {
        
        if (planetList.size() == 1
                && planetList.get(0).getName().equalsIgnoreCase(request.getHeroPlanet())) {
            return true;
        }

        ErrorMessage.append("Something wrong with Planet name - exact Match Required")
                .append(System.lineSeparator());
        return false;
    }

    private boolean doHeroExist() {
        log.debug("Do Exist");
        if (!peopleList.isEmpty()) {
            return true;
        }
        ErrorMessage.append("No hero found")
                .append(System.lineSeparator());
        return false;
    }

    private List<People> filterHeroWithPlanetMatch() {
        log.debug("filter match");
        
        if (ErrorMessage.length() > 0) {
            return Collections.emptyList();
        }

        var filteredPeopleList = peopleList.stream()
                .filter(hero -> hero.getPlanetId().equals(planetList.get(0).getSwapiId()))
                .collect(Collectors.toList());
        if (filteredPeopleList.isEmpty()) {
            ErrorMessage.append("Cannot match Hero and homeworld")
                    .append(System.lineSeparator());
        }
        return filteredPeopleList;

    }

    // if there is one people result has to be positive
    private void filterFilms() {
        log.debug("filter film");
        
        if (ErrorMessage.length() > 0) {
            return;
        }

        var filmIdSet = peopleList.stream()
                .flatMap(people -> people.getFilmIds().stream())
                .collect(Collectors.toCollection(HashSet::new));

        log.debug("Id Set " + filmIdSet);
        log.debug("Film List" + filmList);
        filmList.removeIf(film -> !filmIdSet.contains(film.getSwapiId()));
        log.debug("Film List" + filmList);
    }

    public QueryResult buildResult() {
        return new QueryResult(request, peopleList, planetList, filmList);
    }

    private void throwSomethingWrong() {
        throw new RestExceptions.BadSwapiRequest(ErrorMessage.toString());
    }

}
