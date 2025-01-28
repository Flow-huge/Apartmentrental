package com.apartmentrental.controllers.interfaces;

import com.apartmentrental.models.User;

public interface UserControllerInterface {
    String registerUser(String firstName, String lastName, String phone, String password, double walletBalance);

    User loginUser(String phone, String password);

    double getWalletBalance(int userId);

    boolean updateWalletBalance(int userId, double newBalance);
}
