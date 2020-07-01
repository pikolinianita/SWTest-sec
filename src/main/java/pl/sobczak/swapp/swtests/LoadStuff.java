/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.swtests;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author piko
 */
@Component
public class LoadStuff {
    
    public String loadGuys(String search){
     
        String PeopleURI = "https://swapi.dev/api/people";
        var template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(PeopleURI + "/1/", String.class);
        return response.getBody();
    }
    
    public String loadActor(String search){
        
        String PeopleURI = "https://swapi.dev/api/people";
        var template = new RestTemplate();
        ResponseEntity<Actor> response = template.getForEntity(PeopleURI + "/1/", Actor.class);
        return response.getBody().toString();
    }
    
}
