/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * simple class for transfer query from REST service to HttpConsumer module
 *
 * @author piko
 */
@Accessors(fluent = true)
@Getter()
@Setter()
@NoArgsConstructor
@ToString
public class SwRequest {

    @JsonProperty("query_criteria_character_phrase")
    String heroName;

    @JsonProperty("query_criteria_planet_name")
    String heroPlanet;

}
