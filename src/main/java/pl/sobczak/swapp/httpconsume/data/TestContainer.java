/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 *
 * @author piko
 */
@Data
public class TestContainer {

    Object wtf ;
    String next;
    String count;

    List<Object> resultList;

    @JsonProperty("results")
    public void setTheRes(Object name) {
        this.wtf = name;
    }
    
    public Object getWTF(){
        return wtf;
    }
    
    public String getData(){
        return wtf.getClass().toString() + " ---- " + System.lineSeparator() + wtf.toString() ;
    }
    
    public String getData0(){
        var wtf0 = ((List)wtf).get(0);
        return wtf0.getClass().toString() + " ---- " + System.lineSeparator() + wtf0.toString() ;
    }
}
