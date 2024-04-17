package com.lz.demo.demos.exception;

import com.lz.demo.demos.anno.Idempotent;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lizhi
 * @create 2024-01-31
 **/
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotentException.class)
    public String Idempotent(IdempotentException e){
        return e.getMessage();
    }
}
