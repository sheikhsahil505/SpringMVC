package com.springmvc.dao;

import com.springmvc.entity.Address;
import com.springmvc.entity.User;
import java.util.List;
public interface UserDao {
    void saveUser(User user,Address address);


    public boolean checkEmailExists(String email);

    List<User> userDetails(String email, String password);

    List<Address> getAllAddressByUserId(int userId);

    List<User> getAllUsers();

   List<User> getUserById(int userId);

    void deleteUser(int id);

    void updateUser(User user, Address address);

    void deleteAddress(String deleteAd);
}