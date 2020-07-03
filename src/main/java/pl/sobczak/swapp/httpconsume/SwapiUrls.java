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

    PEOPLE("https://swapi.dev/api/people/"),
    PLANET("https://swapi.dev/api/planets/"),
    FILMS("https://swapi.dev/api/films/");

    private final String urlAddress;

    private SwapiUrls(String str) {
        urlAddress = str;
    }

    public String getSearchUri() {

        return urlAddress + "?search=";
    }

    public String getUri() {
        return urlAddress;
    }

}
