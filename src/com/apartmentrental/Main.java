package com.apartmentrental;

import com.apartmentrental.controllers.UserController;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.start();
    }
}
