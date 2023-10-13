package com.springmvc.util;

public interface EmailService {
     boolean sendEmail(String subject,String message,String to);
}
