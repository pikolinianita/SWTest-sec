/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.List;
import java.util.concurrent.Future;
import org.springframework.stereotype.Component;


/**
 *
 * @author piko
 */
@Component
public class SwHttpClient implements SwHttpClientInt{

    @Override
    public SomeKindOfResponse getAllResponse(SwRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Future<List<People>> getPeopleList(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
