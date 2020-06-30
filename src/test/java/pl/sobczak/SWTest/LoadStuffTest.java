/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.SWTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author piko
 */
@SpringBootTest
public class LoadStuffTest {
    
    @Autowired
    LoadStuff instance;
    
    public LoadStuffTest() {
    }

    /**
     * Test of loadGuys method, of class LoadStuff.
     */
    @Test
    public void testLoadGuys() {
        System.out.println("loadGuys");
        String search = "";
        
        String expResult = "";
        String result = instance.loadGuys(search);
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
     @Test
    public void testLoadActor() {
        System.out.println("loadActor");
        String search = "";
        
        String expResult = "";
        String result = instance.loadActor(search);
        System.out.println(result);
        //assertEquals(expResult, result);
    }
}
