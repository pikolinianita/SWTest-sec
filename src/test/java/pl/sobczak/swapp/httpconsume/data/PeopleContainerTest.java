/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import pl.sobczak.swapp.httpconsume.HttpConsumerConfiguration;
import pl.sobczak.swapp.httpconsume.SwapiUrls;

/**
 *
 * @author piko
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HttpConsumerConfiguration.class)
class PeopleContainerTest {

    @Autowired
    private RestTemplate template;

    /**
     * Test of setTheRes method, of class PeopleContainer.
     */
    @Test
     void deSerialisationTest() {
        SoftAssertions softly = new SoftAssertions();
        
        // given
        
        // when
        var response = template.getForObject(SwapiUrls.PEOPLE.getSearchUri() + "t", PeopleContainer.class);
        
        // then
        softly.assertThat(response.getNext()).as("NextPage")
                .isNotBlank()
                .startsWith("http")
                .endsWith("page=2");
        
        softly.assertThat(response.getCount()).as("response counts")
                .isEqualTo("34");
        
        softly.assertThat(response.getResultList().size()).as("List<Planet> size")
                .isEqualTo(10);
        
        var shouldBeVader = response.getResultList().get(0);
        
                softly.assertThat(shouldBeVader).as(" vader")
                        .hasFieldOrPropertyWithValue("name","Darth Vader");
                
        var vadersFilms = shouldBeVader.getFilmIds();
        
        softly.assertThat(vadersFilms).as("Films About Vader")
                .hasSize(4)
                .containsExactlyInAnyOrder("1","2","3","6");
                
        softly.assertThat(shouldBeVader).as("homeworld")
                .isEqualTo("1");
        
        softly.assertAll();
    }
    
    
    
}
