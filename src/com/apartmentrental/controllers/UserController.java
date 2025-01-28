package com.apartmentrental.controllers;

import com.apartmentrental.controllers.interfaces.UserControllerInterface;
import com.apartmentrental.models.User;
import com.apartmentrental.repositories.interfaces.IUserRepository;

public class UserController implements UserControllerInterface {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String firstName, String lastName, String phone, String password, double walletBalance) {
        User user = new User(0, firstName, lastName, phone, password, walletBalance);
        boolean success = userRepository.addUser(user);
        return success ? "User registered successfully!" : "Registration failed.";
    }

    @Override
    public User loginUser(String phone, String password) {
        return userRepository.getUserByPhoneAndPassword(phone, password);
    }

    @Override
    public double getWalletBalance(int userId) {
        User user = userRepository.getUserById(userId);
        return user != null ? user.getWalletBalance() : 0.0;
    }

    @Override
    public boolean updateWalletBalance(int userId, double newBalance) {
        return userRepository.updateWalletBalance(userId, newBalance);
    }
}
