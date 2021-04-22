package com.smilep.aws.lambda.service;

import com.smilep.aws.lambda.model.Person;

public interface PersonService {

    String process(Person person);
}
