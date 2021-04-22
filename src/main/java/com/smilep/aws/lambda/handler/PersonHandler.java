package com.smilep.aws.lambda.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilep.aws.lambda.model.Person;
import com.smilep.aws.lambda.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class PersonHandler extends SpringBootRequestHandler<Map<String, Object>, String> {

    @Autowired
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    public String handle(Map<String, Object> event) {
        event.entrySet().forEach(entry -> log.info("Key : {} Value : {}", entry.getKey(), entry.getValue()));
        Object personObj = event.get("data");
        Person person = null;
        try {
            person = objectMapper.convertValue(personObj, Person.class);
        } catch (Exception e) {
            log.info("Person not received : {}", e);

        }
        if (null == person) {
            return "Non-Person processed!";
        }
        return personService.process(person);
    }
}
