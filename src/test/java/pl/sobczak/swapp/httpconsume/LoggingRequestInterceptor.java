/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.swapp.httpconsume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 * @author - sofiene zaghdoudi @ StackOverflow with minor modifications ( convert to commons log)
 */
@CommonsLog
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    //final static Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("===========================request begin================================================");
        log.info("URI         : " + request.getURI());
        log.info("Method      : " + request.getMethod());
        log.info("Headers     : " + request.getHeaders());
        log.info("Request body: " + new String(body, "UTF-8"));
        log.info("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.info("============================response begin==========================================");
        log.info("Status code  : " + response.getStatusCode());
        log.info("Status text  : " + response.getStatusText());
        log.info("Headers      : " + response.getHeaders());
        log.info("Response body: " + inputStringBuilder.toString());
        log.info("=======================response end=================================================");
    }

}
