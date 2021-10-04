package com.example.springdemo.exception;




public class InvestorNotFoundException extends RuntimeException{
    public InvestorNotFoundException(String s) {
        super(s);
    }

    public InvestorNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvestorNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
