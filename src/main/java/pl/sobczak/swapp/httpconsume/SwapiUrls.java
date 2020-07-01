/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

/**
 *
 * @author piko
 */
public enum SwapiUrls {
    
    PEOPLE ("https://swapi.dev/api/people/?search={}"),
    PLANET ("https://swapi.dev/api/planets/?search={}"),
    FILMS ("https://swapi.dev/api/films/?search={}");
    
    private SwapiUrls(String str){
        UrlAddress = str;
    }
    private final String UrlAddress;
}
