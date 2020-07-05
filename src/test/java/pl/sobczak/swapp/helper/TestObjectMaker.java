/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;

/**
 *
 * @author piko
 */
public class TestObjectMaker {

   static HashMap<String, Planet> planets;
   static HashMap<String, People> people;
   static HashMap<String, Film> films;

    public TestObjectMaker() {

        people = new HashMap<>();
        people.put("James T. Kirk", new People("Kirk", "100", Set.of("1", "3"), "10"));
        people.put("Spock", new People("Spock", "101", Set.of("1", "2", "3"), "12"));
        people.put("Uhura", new People("Uhura", "102", Set.of("1"), "10"));

        planets = new HashMap<>();
        planets.put("Earth", new Planet("Earth", "10"));
        planets.put("Bajor", new Planet("Bajor", "11"));
        planets.put("Vulcan", new Planet("Vulcan", "12"));

        films = new HashMap<>();
        films.put("Star Trek", new Film().setName("Star Trek").setSwapiId("1"));
        films.put("Next Gen", new Film().setName("Next Gen").setSwapiId("2"));
        films.put("DS9", new Film().setName("DS9").setSwapiId("3"));
    }

    public static Planet planet(String name){
        return planets.get(name);
    }
    
    public static People people(String name){
        return people.get(name);
    }
    
    public static Film film (String name){
        return films.get(name);
    }
    
    public static List<Planet> planets(){
        return new LinkedList(planets.values());
    }
    
     public static List<People> peoples(){
         return new LinkedList(people.values());
     }
     
      public static List<Film> films(){
          return new LinkedList(films.values());
      }
}

