package com.gmail.andreyzarazka.hotelbooking.domain;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
public enum Category {
    SINGLE("single"),
    DOUBLE("double"),
    DELUXE("deluxe"),
    PRESIDENTIAL("presidential");

    private String code;

    Category(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}