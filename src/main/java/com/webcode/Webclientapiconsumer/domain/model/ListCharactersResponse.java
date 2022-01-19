package com.webcode.Webclientapiconsumer.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListCharactersResponse {
    private Map< Object, String > info;

}
