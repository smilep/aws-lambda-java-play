package com.smilep.aws.lambda.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import java.util.Map;

public class PersonEventHandler extends SpringBootRequestHandler<Map<String, Object>, String> {

}
