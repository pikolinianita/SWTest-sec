/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sobczak.swapp.Exceptions.RestExceptions;
import pl.sobczak.swapp.helper.TestObjectMaker;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;
import static pl.sobczak.swapp.helper.TestObjectMaker.*;
import pl.sobczak.swapp.service.data.QueryResult;

/**
 *
 * @author piko
 */
public class ResultValidatorTest {

    List<Film> filmList;

    List<People> peopleList;

    List<Planet> planetList;

    SwRequest request;

    @BeforeEach
    public void setUp() {
        new TestObjectMaker();
    }

    @Test
    public void tooManyPlanetsPathTest() {
        //given
        request = new SwRequest().setHeroName("Spock").setHeroPlanet("Vulcan");
        var validator = new ResultValidator(request, peoples(), planets(), films());

        //when
        var planetException = catchThrowable(() -> validator.validate());
        //then

        assertThat(planetException).as("wrong in planet")
                .isInstanceOf(RestExceptions.BadSwapiRequest.class)
                .hasMessageContaining("Something wrong with Planet");
    }

    @Test
    public void NoPlanetsPathTest() {
        request = new SwRequest().setHeroName("Spock").setHeroPlanet("Vulcan");
        var validator = new ResultValidator(request, peoples(), Collections.EMPTY_LIST, films());

        //when
        var planetException = catchThrowable(() -> validator.validate());
        //then

        assertThat(planetException).as("wrong in planet")
                .isInstanceOf(RestExceptions.BadSwapiRequest.class)
                .hasMessageContaining("Something wrong with Planet");
    }

    @Test
    public void WrongPlanetsPathTest() {
        request = new SwRequest().setHeroName("Spock").setHeroPlanet("Vulca");
        var validator = new ResultValidator(request, peoples(), List.of(planet("Vulcan")), films());

        //when
        var planetException = catchThrowable(() -> validator.validate());
        //then

        assertThat(planetException).as("wrong in planet")
                .isInstanceOf(RestExceptions.BadSwapiRequest.class)
                .hasMessageContaining("Something wrong with Planet");
    }

    @Test
    public void happyPathTest() {
        SoftAssertions softly = new SoftAssertions();
        // given
        request = new SwRequest().setHeroName("Spock").setHeroPlanet("Vulcan");
        var validator = new ResultValidator(request, peoples(), List.of(planet("Vulcan")), films());

        // when
        QueryResult result = validator.validate().buildResult();

        // then
        softly.assertThat(result).as("Spock, Vulcan")
                .isNotNull()
                .extracting("request")
                .hasFieldOrPropertyWithValue("heroName", "Spock")
                .hasFieldOrPropertyWithValue("heroPlanet", "Vulcan");

        softly.assertThat(result.getPlanetList()).as("Vulcan Only")
                .hasSize(1)
                .contains(planet("Vulcan"));

        softly.assertThat(result.getPeopleList()).as("Spock Only")
                .hasSize(1)
                .contains(people("Spock"));

        softly.assertThat(result.getFilmList()).as("all three films")
                .hasSize(3)
                .containsExactlyInAnyOrderElementsOf(films());

        softly.assertAll();
    }

    @Test
    public void noHeroHit() {

        // given
        request = new SwRequest().setHeroName("Luke").setHeroPlanet("Vulcan");
        var validator = new ResultValidator(request, Collections.EMPTY_LIST, List.of(planet("Vulcan")), Collections.EMPTY_LIST);
        // when
        var planetException = catchThrowable(() -> validator.validate());
        // then
        assertThat(planetException).as("No Hero Found")
                .isInstanceOf(RestExceptions.BadSwapiRequest.class)
                .hasMessageContaining("No hero found");
    }

    @Test
    public void AllWrong() {

        // given
        request = new SwRequest().setHeroName("Luke").setHeroPlanet("Tattoine");
        var validator = new ResultValidator(request, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        // when
        var planetException = catchThrowable(() -> validator.validate());
        // then
        assertThat(planetException).as("No Hero Found")
                .isInstanceOf(RestExceptions.BadSwapiRequest.class)
                .hasMessageContaining("No hero found")
                .hasMessageContaining("Something wrong with Planet");

    }

}
