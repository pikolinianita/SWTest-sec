/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczak.swapp.httpconsume.SwHttpClientInt;
import pl.sobczak.swapp.httpconsume.SwRequest;

/**
 *
 * @author piko
 * @
 * return true on success, false on failure
 */
@CommonsLog
@Service
public class MyService {

    @Autowired
    SwHttpClientInt httpClient;
    
    public boolean putOrUpdate(String id, SwRequest input) {
        log.info("putOrUpdate invoked");
        //if (idExist(id)) update else
        put(input);
        return false;
    }

    private void put(SwRequest input) {
        
    }
    
}
