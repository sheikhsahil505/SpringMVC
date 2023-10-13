package com.springmvc.service;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.Address;
import com.springmvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private HttpSession session;
    @Override
    public boolean saveUser(User user, Address address) {
        boolean flag;
        String password = user.getPassword();
        String encoder = Base64.getEncoder().encodeToString(password.getBytes(Charset.forName("UTF-8")));
        user.setPassword(encoder);
        String email = user.getEmail();
        boolean status = userDao.checkEmailExists(email);
        if(status){
           flag = true;
        }else{
        userDao.saveUser(user,address);
            flag = false;
    }
        return flag;
    }

    @Override
    public List<User> login(String email, String password) {
        String encoder = Base64.getEncoder().encodeToString(password.getBytes(Charset.forName("UTF-8")));
        List<User> users = userDao.userDetails(email, encoder);
        if(users!=null){
        for (User use:users){
            String role = use.getRole();
            int userId = use.getUser_id();
            session.setAttribute("username",email);
            session.setAttribute("password",encoder);
            session.setAttribute("admin_id",userId);
            session.setAttribute("role",role);
        }
        }
         return users;

    }
    @Override
    public List<User> userDetails(String email, String password) {
         return  this.userDao.userDetails(email, password);
    }

    @Override
    public List<Address> getAllAddressByUserId(int userId) {
        return this.userDao.getAllAddressByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getUserById(int id) {
        List<User> userById = userDao.getUserById(id);
        for (User us:userById
             ) {
            String email = us.getEmail();
            session.setAttribute("userEmail",email);
        }
        return userById;
    }



    @Override
    public boolean updateUser(User user, Address address,String addressDeleteIds) {
      boolean flag;
        String oldEmail =(String) session.getAttribute("userEmail");
        String email = user.getEmail();
        boolean status = userDao.checkEmailExists(email);
        if (Objects.equals(oldEmail, email)) {
            flag = false;
            userDao.deleteAddress(addressDeleteIds);
            userDao.updateUser(user,address);
        }  else if (status==true) {
            flag=true;
        }else{
            flag= false;
            userDao.deleteAddress(addressDeleteIds);
            userDao.updateUser(user,address);
        }
       return flag;
    }

    @Override
    public void deleteAddress(String deleteAd) {
        userDao.deleteAddress(deleteAd);
    }
}
