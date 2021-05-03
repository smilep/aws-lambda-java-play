package com.smilep.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class DynamoDbEventHandler extends SpringBootRequestHandler<DynamodbEvent, String> {

}
