package com.gmail.andreyzarazka.hotelbooking.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author <a href="mailto:andreyzarazka@gmail.com">Andrew Zarazka</a>
 * @since 08.03.2018
 */
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdditionalOptions)) return false;
        AdditionalOptions that = (AdditionalOptions) obj;
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