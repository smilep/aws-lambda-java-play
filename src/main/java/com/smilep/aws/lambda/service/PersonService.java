package com.smilep.aws.lambda.service;

import com.smilep.aws.lambda.model.Person;

import java.util.Map;

public interface PersonService {

    String process(Person person);

    String process(Map<String, Object> event);
}
