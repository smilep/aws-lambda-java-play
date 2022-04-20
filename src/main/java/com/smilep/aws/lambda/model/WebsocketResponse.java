package com.smilep.aws.lambda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketResponse {

    @JsonProperty("isBase64Encoded")
    private boolean isBase64Encoded = false;

    private int statusCode;

    private String body;
}
