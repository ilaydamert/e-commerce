package com.ecommerce.ecommerce.Services;
import java.util.ArrayList;

import com.ecommerce.ecommerce.DTOs.Customer.CustomerDto;
import com.ecommerce.ecommerce.DTOs.User.UserDto;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Models.DAOUser;
import com.ecommerce.ecommerce.Repositories.CustomerRepository;
import com.ecommerce.ecommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DAOUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
    public DAOUser save(UserDto user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
    public DAOUser save(String username,String password) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(username);
        newUser.setPassword(bcryptEncoder.encode(password));
        return userRepository.save(newUser);
    }
    public void delete(String username) {
        DAOUser user=userRepository.findByUsername(username);
        userRepository.delete(user);
    }
    public void update(String username,CustomerDto customer) {
        DAOUser user=userRepository.findByUsername(username);
        user.setUsername(customer.getUsername());
        user.setPassword(bcryptEncoder.encode(customer.getPassword()));
        userRepository.save(user);
    }


}
