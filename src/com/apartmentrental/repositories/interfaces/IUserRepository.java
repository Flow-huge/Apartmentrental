package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.User;

public interface IUserRepository {
    void registerUser(String firstName, String lastName, String phone, String username, String password);
    User loginUser(String username, String password);
}
