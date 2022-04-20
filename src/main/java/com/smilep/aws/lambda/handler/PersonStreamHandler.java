package com.smilep.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.aws.SpringBootStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PersonStreamHandler extends SpringBootStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for(int length; (length = input.read(buffer)) != -1;) {
            byteArrayOutputStream.write(buffer,0,length);
        }
        String inputStr = byteArrayOutputStream.toString(StandardCharsets.UTF_8.toString());
        log.info("you sent : {}", inputStr);
        String outputStr = "You sent " + inputStr + ". This is output. And lambda ARN : " + context.getInvokedFunctionArn();
        output.write(outputStr.getBytes(StandardCharsets.UTF_8));
    }
}
