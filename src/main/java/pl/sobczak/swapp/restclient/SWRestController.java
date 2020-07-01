/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.restclient;

import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.service.MyService;

/**
 *
 * @author piko
 */
@CommonsLog
@RestController
@RequestMapping("/report")
public class SWRestController {
    
    @Autowired
    MyService service;

    @GetMapping()
    public List<Object> getAll() {
        log.info("Get All invoked");
        return null;
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        log.info("Get invoked");
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody SwRequest input) {
        log.info(String.format("Put invoked with id %s and request body %s",id,input));
        service.putOrUpdate(id, input);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        log.info("Delete invoked");
        return null;
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll(@PathVariable String id) {
        log.info("Delete All invoked");
        return null;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
        log.info("Handle Error invoked");
    }

}
