package com.web.services;

import com.model.User;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private static final Logger userAuthenticationServiceLogger = LogManager.getLogger(UserAuthenticationService.class);

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        userAuthenticationServiceLogger.info("start loadUserByUsername");
        User user = userService.getUserByLogin(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        userAuthenticationServiceLogger.info("end loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                authorities);
    }
}
