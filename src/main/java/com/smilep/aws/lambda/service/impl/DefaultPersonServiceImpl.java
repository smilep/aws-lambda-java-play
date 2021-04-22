package com.smilep.aws.lambda.service.impl;

import com.smilep.aws.lambda.service.PersonService;
import com.smilep.aws.lambda.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DefaultPersonServiceImpl implements PersonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String process(Person person) {
        log.info("Person processed : {}", person.toString());
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/1", Object.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            log.info("HTTP call successful with response : {}", responseEntity.getBody());
        }
        return "Person processed!!";
    }

}
