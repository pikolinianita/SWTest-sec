/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



/**
 *
 * @author piko
 */
@Data
public class Film {
      
    @JsonProperty("title")
    private String name;
    
    private String swapiId;
    
    @JsonProperty("url")
    private void getId(String url) {
        var tmp = url.substring(0, url.length()-1);
        swapiId =  tmp.substring(tmp.lastIndexOf('/')+1);
    }
}
