package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.User;

public interface IUserRepository {
    boolean addUser(User user);
    User getUserById(int id);
    User getUserByPhoneAndPassword(String phone, String password);
    boolean updateWalletBalance(int userId, double newBalance);
    double getUserWalletBalance(int userId);
}
