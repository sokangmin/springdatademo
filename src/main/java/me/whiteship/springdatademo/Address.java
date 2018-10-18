package me.whiteship.springdatademo;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
