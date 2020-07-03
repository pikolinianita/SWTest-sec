/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collection;
import java.util.List;
import lombok.Data;

/**
 *
 * @author piko
 */
@Data
public class ResultContainer<T>{
    
    
    String next;
    String count;
    
    @JsonProperty("results")
    List<Object> resultList;
    
//    public String next() {
//        return next();
//    }
//
//    public List<T> getList() {
//        return resultList;
//    }
    
    
}
