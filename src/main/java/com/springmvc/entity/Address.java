package com.springmvc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

@Entity
@Table(name = "user_addresses")
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    @NotEmpty
    private String street;
    private String  apartment;
    private String city;
    private String pincode;
    private String state;
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Transient
    private int[] addressId;

    public void setAddressId(int[] addressId) {
        // Create a defensive copy of the array
        this.addressId = Arrays.copyOf(addressId, addressId.length);
    }

    public int[] getAddressId() {
        // Return a copy of the internal array to prevent direct modification
        return Arrays.copyOf(addressId, addressId.length);
    }


    public Address[] createArray(Address[] addresses) {
        return addresses;
    }



    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address() {

    }
}
