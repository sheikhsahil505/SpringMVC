package com.springmvc.dao;

import com.springmvc.entity.Address;
import com.springmvc.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {
    }

    @Override
    @Transactional
    public void saveUser(User user,Address address){
               hibernateTemplate.save(user);
        String[] street = address.getStreet().split(",");
        String[] apartment = address.getApartment().split(",");
        String[] city = address.getCity().split(",");
        String[] state = address.getState().split(",");
        String[] country = address.getCountry().split(",");
        String[] pincode = address.getPincode().split(",");
        if (street != null && street.length > 0) {
            for (int i = 0; i < street.length; i++) {
                Address address1 = new Address();
                address1.setStreet(street[i]);
                address1.setApartment(apartment[i]);
                address1.setPincode(pincode[i]);
                address1.setState(state[i]);
                address1.setCity(city[i]);
                address1.setCountry(country[i]);
                address1.setUser(user);

                hibernateTemplate.save(address1);
            }

        }

        }




    @Override
    public boolean checkEmailExists(String email) {
        try {
            String hql = "SELECT 1 FROM User WHERE email = :email";
            Query<?> query = sessionFactory.openSession().createQuery(hql);
            query.setParameter("email", email);

            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
            return false;
        }
    }

    @Override
    public List<User> userDetails(String email, String password) {
        // Create a query using Hibernate Query Language (HQL)
        String hql = "FROM User WHERE email = :email AND password = :password";

        // Execute the query
        List<User> users = (List<User>) hibernateTemplate
                .findByNamedParam(hql, new String[]{"email", "password"}, new Object[]{email, password});

        return users;
    }

    @Override
    public List<Address> getAllAddressByUserId(int userId) {
        try {
            String hql = "FROM Address WHERE user_id = :userId";
            return sessionFactory.openSession().createQuery(hql, Address.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            String hql = "FROM User WHERE role = :role";
            List<User> users = sessionFactory.openSession().createQuery(hql, User.class)
                    .setParameter("role", "user")
                    .list();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getUserById(int userId) {
        try {
            // Use hibernateTemplate to retrieve the user by userId
            User user = hibernateTemplate.get(User.class, userId);

            // Create a list and add the retrieved user (or null if not found)
            List<User> userList = new ArrayList<>();
            userList.add(user);

            return userList;
        } catch (Exception ex) {
            // Handle any exceptions that may occur during retrieval
            ex.printStackTrace(); // Log the exception or handle it as needed
            return Collections.emptyList(); // Return an empty list on error
        }
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user != null) {
            // Delete the entity if it exists
            hibernateTemplate.delete(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user, Address address) {
        int userId = user.getUser_id();
        User existingUser = hibernateTemplate.get(User.class, userId);
        String password = existingUser.getPassword();
        user.setPassword(password);
        hibernateTemplate.merge(user);

        int[] addressId =address.getAddressId();
        String[] street = address.getStreet().split(",");
        String[] apartment = address.getApartment().split(",");
        String[] city = address.getCity().split(",");
        String[] state = address.getState().split(",");
        String[] country = address.getCountry().split(",");
        String[] pincode = address.getPincode().split(",");

        if (street != null && street.length > 0) {
            for (int i = 0; i < street.length; i++) {
                if (i < addressId.length) {
                    // Update the existing Address with provided address_id
                    Address existingAddress = hibernateTemplate.get(Address.class, addressId[i]);
                    existingAddress.setAddress_id(addressId[i]);
                    existingAddress.setStreet(street[i]);
                    existingAddress.setApartment(apartment[i]);
                    existingAddress.setPincode(pincode[i]);
                    existingAddress.setState(state[i]);
                    existingAddress.setCity(city[i]);
                    existingAddress.setCountry(country[i]);

                    existingAddress.setUser(user);
                    hibernateTemplate.update(existingAddress);
                } else {
                    // Create a new Address for a null address_id (generating it)
                    Address newAddress = new Address();
                    newAddress.setStreet(street[i]);
                    newAddress.setApartment(apartment[i]);
                    newAddress.setPincode(pincode[i]);
                    newAddress.setState(state[i]);
                    newAddress.setCity(city[i]);
                    newAddress.setCountry(country[i]);
                    newAddress.setUser(user);
                    hibernateTemplate.save(newAddress);
                }
            }
        }
    }




    @Override
    @Transactional
    public void deleteAddress(String deleteAd) {

        String[] split = deleteAd.split(",");
        if(!"".equals(deleteAd)) {
            int[] id = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                // Validate if the string can be parsed as an integer before parsing
                try {
                    id[i] = Integer.parseInt(split[i]);
                } catch (NumberFormatException e) {
                    // Handle invalid input (non-numeric value)
                    // You can log the error, skip the element, or perform other error handling here
                    e.printStackTrace(); // Example: Print the exception to the console
                }
            }

// Now loop through the validated IDs and delete corresponding Address records
            for (int i = 0; i < id.length; i++) {
                if (id[i] > 0) { // Check if the ID is valid (positive)
                    Address address = hibernateTemplate.get(Address.class, id[i]);
                    if (address != null) {
                        hibernateTemplate.delete(address);
                    }
                }
            }
        }

    }
}