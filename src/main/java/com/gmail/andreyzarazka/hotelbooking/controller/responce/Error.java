package com.gmail.andreyzarazka.hotelbooking.controller.responce;

public class Error extends Result {
    private final String error;

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}