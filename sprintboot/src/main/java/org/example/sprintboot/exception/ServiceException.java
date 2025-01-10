package org.example.sprintboot.exception;

public class ServiceException  extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
