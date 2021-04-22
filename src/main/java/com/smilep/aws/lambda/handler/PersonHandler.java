package com.smilep.aws.lambda.handler;

import com.smilep.aws.lambda.model.Person;
import com.smilep.aws.lambda.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
//@Component
public class PersonHandler {

    @Autowired
    private PersonService personService;

    public String handle(Map<String, Object> event) {
        event.entrySet().forEach(entry -> log.info("Key : {} Value : {}", entry.getKey(), entry.getValue()));
        return "Temporary processed!";
    }
}