package com.airbnb;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Demo {
    public static void main(String[] args) {
//        PasswordEncoder en = new BCryptPasswordEncoder();
//        System.out.println(en.encode("testing"));
        String encodedPassword = BCrypt.hashpw("Testing", BCrypt.gensalt(5));
        System.err.println(encodedPassword);
    }
}
