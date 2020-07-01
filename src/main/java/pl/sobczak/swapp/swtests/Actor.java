/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.swtests;

import java.util.LinkedList;
import lombok.Data;

/**
 *
 * @author piko
 */
@Data
public class Actor {
   String name;
   String gender;
   LinkedList<String> films;

    @Override
    public String toString() {
        return "Actor{" + "name=" + name + ",\n gender=" + gender + ",\n films=" + films + "}\n";
    }
   
   
}
