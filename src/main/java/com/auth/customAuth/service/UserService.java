package com.auth.customAuth.service;


import com.auth.customAuth.model.GenerateJWT;
import com.auth.customAuth.model.Users;
import com.auth.customAuth.repo.UserRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication.*;

import java.util.List;

@Service
public class UserService {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    GenerateJWT generateJWT;



    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users registerUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepo.save(user);
    }

    public List<Users> listOfusers() {
        return userRepo.findAll();
    }

    public String verifyUserinDB(Users user) {
        org.springframework.security.core.Authentication auth =
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth.isAuthenticated()) {
            return generateJWT.getJWT(user.getUsername());
        }else{
            return "Login failed";
        }
    }
}
