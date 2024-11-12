package com.auth.customAuth.service;


import com.auth.customAuth.model.UserPrinciple;
import com.auth.customAuth.repo.UserRepo;
import com.auth.customAuth.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Users user = new Users();
        Users user  = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrinciple(user);
    }
}
