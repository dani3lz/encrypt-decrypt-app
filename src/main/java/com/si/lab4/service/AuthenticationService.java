package com.si.lab4.service;


import com.si.lab4.model.requests.LoginResponse;
import com.si.lab4.model.requests.UserRequest;

public interface AuthenticationService {

    LoginResponse registerNewUser(UserRequest request);

    LoginResponse loginUser(UserRequest request);

    void logoutUser(String token);
}