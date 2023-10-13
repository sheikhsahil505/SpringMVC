package com.springmvc.dao;

public interface ForgetPasswordDao {
     boolean verifyEmailandDob(String email, String dob);
     void updatePassword(String email, String dob, String newPassword);
}
