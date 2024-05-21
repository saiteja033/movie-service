package com.msa.tmdb.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}
