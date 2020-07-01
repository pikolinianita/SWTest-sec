/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.swtests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 *
 * @author piko
 */
public class MiscTests {
    
    
    @Test
    public void UriTest(){
        var factory = new DefaultUriBuilderFactory("https://swapi.dev/api/people/?search={name}");
        var Uri = factory.expand("https://swapi.dev/api/{ooo}/?search={name}", "Luke", "Leia", "Polot");
        System.out.println(Uri);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
