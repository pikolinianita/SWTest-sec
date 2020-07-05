/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author piko
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
public class People {

    private String name;

    private String swapiId;

    private Set<String> filmIds;
    
    private String planetId;
}
