package com.gmail.andreyzarazka.hotelbooking.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "auth_customer")
public class AuthorizedCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public AuthorizedCustomer() {
    }

    public AuthorizedCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AuthorizedCustomer)) return false;
        AuthorizedCustomer that = (AuthorizedCustomer) obj;
        return id == that.id &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, customer);
    }

    @Override
    public String toString() {
        return "AuthorizedCustomer{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}