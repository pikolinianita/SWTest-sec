/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author piko
 */
public interface SwHttpClientInt {
    
    public SomeKindOfResponse getAllResponse(SwRequest req);
    
    public Future<List<People>> getPeopleList(String query);
}
