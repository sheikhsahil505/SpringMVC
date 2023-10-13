package com.springmvc.service;

public interface ForgetPasswordService {
    boolean verifyEmailandDob(String email, String dob);

    void getNewPassword(String newPassword);
}
