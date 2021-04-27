package com.smilep.aws.lambda.service.impl;

import com.smilep.aws.lambda.service.PersonService;
import com.smilep.aws.lambda.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class DefaultPersonServiceImpl implements PersonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String process(Person person) {
        log.info("Person to be processed : {}", person.toString());
        try {
            CompletableFuture.runAsync(() -> {
                log.info("HTTP call in new thread");
                // TODO - handle issue of timeout in case of HTTPS URL below
                ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/todos/1", Object.class);
                if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                    log.info("HTTP call successful with response : {}", responseEntity.getBody());
                }
            }).get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.info("Error calling async task : {}", e);
        }
        return "Person processed!!";
    }

    @Override
    public String process(Map<String, Object> event) {
        log.info("Event received : {}", event);
        return process(new Person());
    }

}
