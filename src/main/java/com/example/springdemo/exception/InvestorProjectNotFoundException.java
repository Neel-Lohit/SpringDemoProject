package com.example.springdemo.exception;




public class InvestorProjectNotFoundException extends RuntimeException{
    public InvestorProjectNotFoundException(String s) {
        super(s);
    }

    public InvestorProjectNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvestorProjectNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
