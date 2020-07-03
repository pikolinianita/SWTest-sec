/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;
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
public class PlanetContainerTest {

    @Autowired
   private RestTemplate template;

    @Test    
    void deSerialisationTest() {

        //given
        SoftAssertions softly = new SoftAssertions();

        //when
        var response = template.getForObject(SwapiUrls.PLANET.getSearchUri() + "t", PlanetContainer.class);

        //then
        softly.assertThat(response.getNext()).as("NextPage")
                .isNotBlank()
                .startsWith("http")
                .endsWith("page=2");

        softly.assertThat(response.getCount()).as("response counts")
                .isEqualTo("24");

        softly.assertThat(response.getResultList().size()).as("List<Planet> size")
                .isEqualTo(10);

        softly.assertAll();
    }

}
