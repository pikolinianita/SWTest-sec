/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.Planet;
import pl.sobczak.swapp.httpconsume.data.ResultContainer;
import pl.sobczak.swapp.httpconsume.data.TestContainer;
import pl.sobczak.swapp.httpconsume.data.PlanetContainer;

/**
 *
 * @author piko
 */
@CommonsLog
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HttpConsumerConfiguration.class, SwHttpClient.class})
public class SwHttpClientTest {

    @Autowired
    SwHttpClient client;

   
    /**
     * Test of getPeopleList method, of class SwHttpClient.
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @Disabled
    @Test
    public void testGetOnePlanetList() throws InterruptedException, ExecutionException {
        var template = new RestTemplate();
        var client = new SwHttpClient(template);
        System.out.println("aaa");
        var result = client.<String>getListOf(SwapiUrls.PLANET, "Tatooine");
        System.out.println("bbb");
        var list = result.get();
        System.out.println(list);

    }

    @Disabled
    @Test
    public void testGetManyPlanetsList() throws InterruptedException, ExecutionException {
        var template = new RestTemplate();
        var client = new SwHttpClient(template);
        System.out.println("aaa");
        var result = client.getListOf(SwapiUrls.PLANET, "T");
        System.out.println("bbb");
        var list = result.get();
        System.out.println(list);

    }

    @Test
    public void GetOneFilmTest() throws ExecutionException, InterruptedException {

        // given
        // when
        var film = client.getFilm("1").get();

        // then
        assertThat(film).as("Ep 4: New Hope")
                .hasFieldOrPropertyWithValue("name", "A New Hope")
                .hasFieldOrPropertyWithValue("swapiId", "1");
    }

    @Test
    public void GetManyFilmsTest() {

        // given - sorted by creation time
        List<String> episodes = List.of("1", "2", "3", "4", "5");

        // when
        var futures = episodes.stream()
                .map(client::getFilm)
                .map(future -> {
                    try {
                        return future.get(5, TimeUnit.SECONDS);
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(Film::getName)
                .collect(Collectors.toList());

        // then
        assertThat(futures).as("five films")
                .hasSize(5)
                .containsExactlyInAnyOrder(
                        "A New Hope",
                        "The Empire Strikes Back",
                        "Return of the Jedi",
                        "The Phantom Menace",
                        "Attack of the Clones");
    }

    @Test
    public void getPlanetListTest() throws InterruptedException, ExecutionException {
                
        // given
        String query = "t";
        
        // when
        var planetList = client.getPlanetList(query).get();
        
        // then
        assertThat(planetList).as("List of T").hasSize(24).extracting("name").doesNotContainNull();
                
        }
    
    @Test
    public void getOnePlanetListTest() throws InterruptedException, ExecutionException {
        SoftAssertions softly = new SoftAssertions();
        
        // given
        String query = "Tatooine";
        
        // when
        var planetList = client.getPlanetList(query).get();
        
        // then
        softly.assertThat(planetList).as("List of Tatooine").hasSize(1);
        softly.assertThat(planetList.get(0)).as("Tatooine")
                .extracting("name", "swapiId").containsExactly("Tatooine", "1" );
        
        softly.assertAll();
    }
    
    @Test
    public void getPeopleListTest() throws InterruptedException, ExecutionException {
                
        // given
        String query = "t";
        
        // when
        var peopleList = client.getPeopleList(query).get();
        
        // then
        assertThat(peopleList).as("List of T").hasSize(34).extracting("name").doesNotContainNull();
                
        }
    
    @Test
    public void getOnePeopleListTest() throws InterruptedException, ExecutionException {
        SoftAssertions softly = new SoftAssertions();
        
        // given
        String query = "Luke Skywalker";
        
        // when
        var peopleList = client.getPeopleList(query).get();
        
        // then
        softly.assertThat(peopleList).as("List of Luke").hasSize(1);
        softly.assertThat(peopleList.get(0)).as("Luke")
                .extracting("name", "swapiId").containsExactly("Luke Skywalker", "1" );
        
        softly.assertAll();
    }
    
    @Test
    public void noPeopleTest() throws InterruptedException, ExecutionException {
        // given
        String query = "zzzzzzzz";
        
        // when
        var peopleList = client.getPeopleList(query).get();
        
        // then
        assertThat(peopleList).as("List of T").hasSize(0);
    }
    
//    /**
//     * Test of getPlanet method, of class SwHttpClient.
//     */
//    @Test
//    public void testGetPlanet() throws InterruptedException, ExecutionException {
//        var template = new RestTemplate();
//        var client = new SwHttpClient(template);
//        var result = client.getPlanet("Tatooine").get();
//
//        System.out.println(result);
//        assertEquals("Tatooine", result.getName());
//
//    }
}
