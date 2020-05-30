package com.api2.contacts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // tell spring security that this is a configuration class
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    // we configure the authentication mechanism here using our own user detail service:
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService); // user authentication method
    }

    // authorization mechanism (I can use specific roles here also):
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // setup authorization for specific routes:
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/contacts").hasAuthority("USER") // role USER specified in user details
                // .antMatchers("/contacts").hasAnyAuthority()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Bean
    // do not use password encoder (use clear-text password):
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}