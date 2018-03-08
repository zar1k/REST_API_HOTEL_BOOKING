package com.gmail.andreyzarazka.hotelbooking.domain;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public enum Status {
    OCCUPIED("occupied"),
    UNOCCUPIED("unoccupied");
    private String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}