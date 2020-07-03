/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import lombok.extern.apachecommons.CommonsLog;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sobczak.swapp.httpconsume.data.Film;

/**
 *
 * @author piko
 */
@CommonsLog
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HttpConsumerConfiguration.class, SwHttpClient.class})
public class SwHttpClientTest {

    @Autowired
    private SwHttpClient client;

    @Test
    void getOneFilmTest() throws ExecutionException, InterruptedException {

        // given autowired
        // when
        var film = client.getFilm("1").get();

        // then
        assertThat(film).as("Ep 4: New Hope")
                .hasFieldOrPropertyWithValue("name", "A New Hope")
                .hasFieldOrPropertyWithValue("swapiId", "1");
    }

    @Test
    void getManyFilmsTest() {

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
    void getPlanetListTest() throws InterruptedException, ExecutionException {

        // given
        String query = "t";

        // when
        var planetList = client.getPlanetList(query).get();

        // then
        assertThat(planetList).as("List of T").hasSize(24).extracting("name").doesNotContainNull();

    }

    @Test
    void getOnePlanetListTest() throws InterruptedException, ExecutionException {
        SoftAssertions softly = new SoftAssertions();

        // given
        String query = "Tatooine";

        // when
        var planetList = client.getPlanetList(query).get();

        // then
        softly.assertThat(planetList).as("List of Tatooine").hasSize(1);
        softly.assertThat(planetList.get(0)).as("Tatooine")
                .extracting("name", "swapiId").containsExactly("Tatooine", "1");

        softly.assertAll();
    }

    @Test
    void getPeopleListTest() throws InterruptedException, ExecutionException {

        // given
        String query = "t";

        // when
        var peopleList = client.getPeopleList(query).get();

        // then
        assertThat(peopleList).as("List of T").hasSize(34).extracting("name").doesNotContainNull();

    }

    @Test
    void getOnePeopleListTest() throws InterruptedException, ExecutionException {
        SoftAssertions softly = new SoftAssertions();

        // given
        String query = "Luke Skywalker";

        // when
        var peopleList = client.getPeopleList(query).get();

        // then
        softly.assertThat(peopleList).as("List of Luke").hasSize(1);
        softly.assertThat(peopleList.get(0)).as("Luke")
                .extracting("name", "swapiId").containsExactly("Luke Skywalker", "1");

        softly.assertAll();
    }

    @Test
    void noPeopleTest() throws InterruptedException, ExecutionException {
        // given
        String query = "zzzzzzzz";

        // when
        var peopleList = client.getPeopleList(query).get();

        // then
        assertThat(peopleList).as("List of T").isEmpty();
    }

}
