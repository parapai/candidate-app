package com.vai.test.exception;

public class DataNotFoundException extends RuntimeException{
	public DataNotFoundException(String message) {
        super(message);
    }
}
