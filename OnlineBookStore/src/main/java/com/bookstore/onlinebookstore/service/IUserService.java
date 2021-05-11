package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.*;
import com.bookstore.onlinebookstore.exception.UserException;

public interface IUserService {
    boolean register(RegistrationDto registrationDto) throws UserException;

    String login(LoginDto loginDto) throws UserException;

    Response forgetPassword(ForgotPasswordDto emailId) throws UserException;

    boolean resetPassword(ResetPasswordDto resetPassword, String token) throws UserException;

}
