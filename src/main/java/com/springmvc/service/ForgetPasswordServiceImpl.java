package com.springmvc.service;

import com.springmvc.dao.ForgetPasswordDao;
import com.springmvc.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;
@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService{
    @Autowired
    private ForgetPasswordDao forgetPasswordDao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private HttpSession session;
    Random random= new Random(1000);
    @Override
    public boolean verifyEmailandDob(String email, String dob) {
        boolean status = forgetPasswordDao.verifyEmailandDob(email, dob);
        boolean flag=false;
        if (status) {
            int otp = random.nextInt(9999999);
            String subject = "OTP from Spring MVC task";
            session.setAttribute("email", email);
            session.setAttribute("dob", dob);
            String message = " Your  OTP is " + otp;
            session.setAttribute("otp", otp);
        boolean f = emailService.sendEmail(subject, message, email);
            flag=f;
        }

        return flag;
    }

    @Override
    public void getNewPassword(String newPassword) {
        String email =(String)   session.getAttribute("email");
        String dob =(String) session.getAttribute("dob");
        String encoder = Base64.getEncoder().encodeToString(newPassword.getBytes(Charset.forName("UTF-8")));
        forgetPasswordDao.updatePassword(email,dob,encoder);
    }

}