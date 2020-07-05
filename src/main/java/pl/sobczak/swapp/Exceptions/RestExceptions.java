/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author piko Class With all Exceptions used by project - to have all
 * exceptions in one file (no sophisticated logic here)
 */
public class RestExceptions extends Exception {

    private RestExceptions(){
        //Use static classes only
    }
    
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public static class HttpClientNoConnectionException extends RuntimeException {

        public HttpClientNoConnectionException(String text) {
            super(text);

        }

        public HttpClientNoConnectionException(String text, Throwable cause) {
            super(text, cause);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class HttpNameNotCorrect extends RuntimeException {

        public HttpNameNotCorrect(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class HttpPlanetNotExist extends RuntimeException {

        public HttpPlanetNotExist(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class HttpNoCorrectCombination extends RuntimeException {

        public HttpNoCorrectCombination(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class NoSuchRecord extends RuntimeException {

        public NoSuchRecord(String text) {
            super(text);
        }
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public static class AkeitaException extends RuntimeException {

        public AkeitaException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadSwapiRequest extends RuntimeException {

        public BadSwapiRequest(String text) {
            super(text);
        }
    }

}
