package com.springmvc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    public int[] getAddressId() {
        return addressId;
    }

    public void setAddressId(int[] addressId) {
        this.addressId = addressId;
    }

    public Address(String addressValue) {
    }

    public Address(String street,String apartment, String city, String state, String postalCode, String country) {
    }

    public Address[] createArray(Address[] addresses) {
        return addresses;
    }



    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int i) {
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

    public Address(int address_id, String street, String apartment, String city, String pincode, String state, String country, User user) {

        this.address_id = address_id;
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
        this.user = user;
    }

    public Address() {

    }
}
