package net.suhel.springboot.todo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassswordEncoderImpl {

    public static void main ( String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("Suhel"));
        System.out.println(passwordEncoder.encode("Sanket"));

    }
}
