package com.smilep.aws.lambda.config;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.smilep.aws.lambda.model.Person;
import com.smilep.aws.lambda.model.WebsocketResponse;
import com.smilep.aws.lambda.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Configuration
public class FunctionsConfig {

    @Autowired
    private PersonService personService;

    @Bean
    public Function<Person, String> processPerson() {
        return person -> personService.process(person);
    }

    @Bean
    public Function<InputStream, WebsocketResponse> processStream() {
        return inputStream -> {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for(int length; (length = inputStream.read(buffer)) != -1;) {
                    byteArrayOutputStream.write(buffer,0,length);
                }
                String inputStr = byteArrayOutputStream.toString(StandardCharsets.UTF_8.toString());
                log.info("you sent : {}", inputStr);
                String outputStr = "You sent " + inputStr;
                WebsocketResponse websocketResponse = new WebsocketResponse(false, 200, outputStr);
                return websocketResponse;
            } catch (Exception e) {
                log.error("Error occurred {}", e);
            }
            return new WebsocketResponse(false, 500, "some wow custom error");
        };
    }

    @Bean
    public Function<Map<String, Object>, String> processPersonEvent() {
        return event -> personService.process(event);
    }

    @Bean
    public Function<DynamodbEvent, String> processDynamoDbEvent() {
        return event -> {
            log.info("Event received : {}", event);
            for (DynamodbEvent.DynamodbStreamRecord record : event.getRecords()) {
                log.info("Record received : {}", record);
                log.info("Image received : {}", record.getDynamodb().getNewImage());
            }
            return "processed";
        };
    }

}
