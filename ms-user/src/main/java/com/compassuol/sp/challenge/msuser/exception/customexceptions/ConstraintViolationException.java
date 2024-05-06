package com.compassuol.sp.challenge.msuser.exception.customexceptions;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(String message){
        super(message);
    }

}
