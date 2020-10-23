package com.rinkesh.schoolmanagementapi.service;

import com.rinkesh.schoolmanagementapi.exceptions.InactiveUserException;
import com.rinkesh.schoolmanagementapi.model.CustomUserDetails;
import com.rinkesh.schoolmanagementapi.model.Users;
import com.rinkesh.schoolmanagementapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users foundUsersDetail = userRepository.findByEmail(email.strip());
//		User foundUserDetail = null;
        Users usersDetail = null;
        if (foundUsersDetail != null) {
            usersDetail = new Users();
//			appFarmerDetail.setId(foundUserDetail.getId());
            usersDetail.setEmail(foundUsersDetail.getEmail());
            usersDetail.setPassword(foundUsersDetail.getPassword());
            usersDetail.setUserRole(foundUsersDetail.getUserRole());

//			if (!usersDetail.getStatus().equals("Active")) {
//				throw new InactiveUserException("User '" + email + "' is not active.");
//			}

            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(usersDetail.getUserRole()));

            User user = new User(usersDetail.getEmail(), usersDetail.getPassword(), grantedAuthorities);

            return new CustomUserDetails(user, usersDetail);

        } else {
            throw new UsernameNotFoundException("User not found with : " + email);
        }

    } // loadUserByUsername

}