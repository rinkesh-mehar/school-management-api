package com.rinkesh.schoolmanagementapi.controller;

import com.rinkesh.schoolmanagementapi.exceptions.InactiveUserException;
import com.rinkesh.schoolmanagementapi.exceptions.InvalidUserCredentialsException;
import com.rinkesh.schoolmanagementapi.model.CustomUserDetails;
import com.rinkesh.schoolmanagementapi.model.JwtRequest;
import com.rinkesh.schoolmanagementapi.model.Users;
import com.rinkesh.schoolmanagementapi.service.JwtUserDetailsService;
import com.rinkesh.schoolmanagementapi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin("*")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Users usersObj = null;
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            usersObj = customUserDetails.getUser();
            usersObj.setToken(token);
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("user", usersObj);

        return ResponseEntity.ok(dataMap);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new InactiveUserException("InactiveUserException"/*APIConstants.RESPONSE_INACTIVE_USER*/);
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("InvalidUserCredentialsException"/*APIConstants.RESPONSE_INVALID_CREDENTIALS*/);
        }
    }
}