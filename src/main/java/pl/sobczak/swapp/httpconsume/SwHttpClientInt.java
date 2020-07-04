/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.Collection;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.Planet;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author piko
 */
public interface SwHttpClientInt {
    
    public Future<List<People>> getPeopleList(String query);

    public Future<List<Planet>> getPlanetList(String query);

    public Future<Film> getFilm(String id);

    public Future<List<Film>> getFilmList(Collection<String> collection);
}
