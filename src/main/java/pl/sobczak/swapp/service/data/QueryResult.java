/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.Film;
import pl.sobczak.swapp.httpconsume.data.People;
import pl.sobczak.swapp.httpconsume.data.Planet;

/**
 *
 * @author piko
 */
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class QueryResult {

    SwRequest request;

    List<People> peopleList;

    List<Planet> planetList;

    List<Film> filmList;

}
