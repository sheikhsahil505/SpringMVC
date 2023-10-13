package com.springmvc.util;

public interface EmailService {
    public boolean sendEmail(String subject,String message,String to);
}
