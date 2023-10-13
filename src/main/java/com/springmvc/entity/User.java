package com.springmvc.entity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user_information")
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @NotEmpty
    @Size(min = 4,max = 10,message = "name should be at least 4 chars and max 10 chars ")
    private String first_name;
    @NotEmpty
    @Size(min = 4,max = 10,message = "name should be at least 4 chars and max 10 chars ")
    private String last_name;
    @Column(unique = true)
    @Email(message = "email is not valid")
    private String email;
    @Size(min = 10,max = 10,message = "mobile number should be 10 digit")
    private String contact_number;
    @NotEmpty
    private String password;
    @NotEmpty
    private String role;
    @NotEmpty
    private String dob;

    public User() {
    }

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<Address> address;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }





    public List<Address> getAddressDaoList() {
        return address;
    }

    public void setAddressDaoList(List<Address> address) {
        this.address= address;
    }
}