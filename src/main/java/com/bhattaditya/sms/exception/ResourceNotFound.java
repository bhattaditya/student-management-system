package com.bhattaditya.sms.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String resource, String field, String value) {
        super(String.format("%s not found %s: %s", resource, field, value));
    }
    public ResourceNotFound(String resource, String field, long value) {
        super(String.format("%s not found %s: %s", resource, field, value));
    }
}
