package com.example.springdemo.exception;




public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String s) {
        super(s);
    }

    public ProjectNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProjectNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
