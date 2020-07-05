/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.service;

import java.util.List;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.AsyncResult;
import pl.sobczak.swapp.Exceptions.RestExceptions;
import pl.sobczak.swapp.httpconsume.SwHttpClientInt;
import pl.sobczak.swapp.httpconsume.SwRequest;
import pl.sobczak.swapp.httpconsume.data.People;

/**
 *
 * @author piko
 */

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {
    
    @Mock
    SwHttpClientInt client;
    
    @InjectMocks
    MyService service;
      
    @Test
    public void TimeOutTest() {
        
//        // given
//        when(client.getPeopleList(any())).thenThrow(new TimeoutException("Mocked TimeOut Exception"));
//        doThrow(new TimeoutException("Mocked TimeOut Exception"))
//                .when(client).getPeopleList(any())
//                .thenReturn(null);
//        
//        var request = new SwRequest();
//        
//        // when
//        var ex = catchThrowable(() -> service.putOrUpdate("1", request));
//        // then
//        assertThat(ex).as("RestExceptions.HttpClientNoConnectionException")
//                .isInstanceOf(RestExceptions.HttpClientNoConnectionException.class)
//                .hasMessage("Problem With Connection to Swapi");
    }
}
