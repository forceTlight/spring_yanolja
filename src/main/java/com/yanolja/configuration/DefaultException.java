package com.yanolja.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultException extends Exception{
    private String message;
    private int statusCode;
}
