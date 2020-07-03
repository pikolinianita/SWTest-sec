/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

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

    public <T> Future<List<T>> getListOf(SwapiUrls link, String query);    
  
    public SomeKindOfResponse getAllResponse(SwRequest req);
    
    public Future<List<People>> getPeopleList(String query);
    
    public Future<List<Planet>> getPlanetList(String query);
    
    public Future<Film> getFilm(String id);
    
    public Future<Planet> getPlanet(String queryPlanet);
}
