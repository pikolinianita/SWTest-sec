/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

/**
 *
 * @author piko
 */
@Data
public class PeopleContainer {

    private String next;

    private String count;

    List<People> resultList = new LinkedList<>();

    @JsonProperty("next")
    public void withHttpsFix(String str) {
        if (str != null) {
            next = str.replace("http://", "https://");
        }
    }

    @JsonProperty("results")
    public void setTheRes(List<Map<String, Object>> result) {

        resultList.addAll(
                result.stream()
                        .map(record
                                -> new People((String) record.get("name"),
                                getId((String) record.get("url")),
                                makeSet(record.get("films")))
                        )
                        .collect(Collectors.toCollection(LinkedList::new)));
    }

    private String getId(String url) {
        var tmp = url.substring(0, url.length() - 1);
        return tmp.substring(tmp.lastIndexOf('/') + 1);
    }

    private Set<String> makeSet(Object value) {
        List<String> filmsList = (List) value;
        return filmsList.stream()
                .map(this::getId)
                .collect(Collectors.toCollection(HashSet::new));
    }

}
