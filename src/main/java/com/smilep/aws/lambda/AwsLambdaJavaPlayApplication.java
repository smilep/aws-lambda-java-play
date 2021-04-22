package com.smilep.aws.lambda;

import com.smilep.aws.lambda.handler.PersonHandler;
import com.smilep.aws.lambda.model.Person;
import com.smilep.aws.lambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class AwsLambdaJavaPlayApplication {

    @Autowired
    private PersonService personService;

    //@Autowired
    private PersonHandler personHandler;

    public static void main(String[] args) {
        SpringApplication.run(AwsLambdaJavaPlayApplication.class, args);
    }

    @Bean
    public Function<Person, String> processPerson() {
        return person -> personService.process(person);
    }

    //@Bean
    public Function<Map<String, Object>, String> processPersonEvent() {
        return event -> personHandler.handle(event);
    }

}
