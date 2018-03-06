package com.gmail.andreyzarazka.hotelbooking.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "additional_options")
public class AdditionalOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_options")
    private TypeOptions options;

    private double price;

    public AdditionalOptions() {
    }

    public AdditionalOptions(TypeOptions options, double price) {
        this.options = options;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeOptions getOptions() {
        return options;
    }

    public void setOptions(TypeOptions options) {
        this.options = options;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalOptions)) return false;
        AdditionalOptions that = (AdditionalOptions) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                options == that.options;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, options, price);
    }

    @Override
    public String toString() {
        return "AdditionalOptions{" +
                "id=" + id +
                ", options=" + options +
                ", price=" + price +
                '}';
    }
}