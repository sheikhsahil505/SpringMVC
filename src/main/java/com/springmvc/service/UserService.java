package com.springmvc.service;

import com.springmvc.entity.Address;
import com.springmvc.entity.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user, Address address);
    List<User> login(String email, String password);

    List<User> userDetails(String email ,String password);
    List<Address> getAllAddressByUserId(int userId);
    List<User> getAllUsers();
    void deleteUser(int id);

    List<User> getUserById(int id);

    boolean updateUser(User user, Address address,String AddressdeleteIds);

    void deleteAddress(String deleteAd);
}
