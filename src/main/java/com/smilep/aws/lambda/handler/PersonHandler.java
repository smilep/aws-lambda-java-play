package com.smilep.aws.lambda.handler;

import com.smilep.aws.lambda.model.Person;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class PersonHandler extends SpringBootRequestHandler<Person, String> {

}
