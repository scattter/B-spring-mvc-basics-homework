package com.thoughtworks.capacity.gtb.mvc.Exception;

public class UserNameOrPasswordIsErrorException extends RuntimeException {
    public UserNameOrPasswordIsErrorException(String message) {
        super(message);
    }
}
