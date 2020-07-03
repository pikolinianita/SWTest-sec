/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pl.sobczak.swapp.SwTestApplication;

/**
 *
 * @author piko
 */
@SpringBootTest(classes = SwTestApplication.class)
public class HttpConsumerConfigurationTest {

    @Autowired
    RestTemplate restT;

    /**
     * Test of getRestTemplate method, of class HttpConsumerConfiguration.
     */
    @Test
    public void testGetRestTemplate() {

        var RT = new RestTemplate().getClass();
        var restTemplate = restT.getClass();

        assertEquals(RT, restTemplate, "Rest Template not autowired");

    }
}
