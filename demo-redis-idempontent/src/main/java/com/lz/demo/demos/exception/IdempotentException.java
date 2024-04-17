package com.lz.demo.demos.exception;

/**
 * @author lizhi
 * @create 2024-01-31
 **/
public class IdempotentException extends Exception{
    public IdempotentException(){
        super();
    }
    public IdempotentException(String message){
        super(message);
    }
}
