package com.smilep.aws.lambda.service.impl;

import com.smilep.aws.lambda.service.PersonService;
import com.smilep.aws.lambda.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultPersonServiceImpl implements PersonService {

    @Override
    public String process(Person person) {
        log.info("Person processed : {}", person.toString());
        return "processed!!";
    }

}
