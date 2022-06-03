package com.web.services;

import com.exceptions.NotFoundException;
import com.exceptions.UserExistException;
import com.model.User;
import com.web.dao.RoleRepository;
import com.web.dao.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User getUserByLogin(String login) throws NotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundException("такого юзера не существует");
        }
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByLogin(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                authorities);
    }

    public boolean registerUser(String username, String password, String role, String name, String lastName) throws UserExistException {
        if(userRepository.findByLogin(username) != null) {
            throw new UserExistException("такой пользователь существует");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setLogin(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user.setLastName(lastName);
            String fullNameRole = "ROLE_" + role;
            user.setRole(roleRepository.findByRole(fullNameRole));
            userRepository.save(user);
            return true;
        }
    }
}
