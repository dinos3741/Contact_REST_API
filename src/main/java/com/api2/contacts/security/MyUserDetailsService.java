package com.api2.contacts.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // create as service annotation so spring security can detect and use it
public class MyUserDetailsService implements UserDetailsService {
    @Override
    // This is used to provide user details. For now I provide them hard-coded, but I can alternatively
    // do user lookup from a DB (later).
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        // specify role "USER" for this user. Returns instance of this user details service
        return new User("user", "pass", Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
