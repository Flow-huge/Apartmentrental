package com.apartmentrental.controllers;

import com.apartmentrental.models.User;
import com.apartmentrental.repositories.UserRepository;

import java.util.Scanner;

public class UserController {
    private final UserRepository userRepository = new UserRepository();
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;

    public void start() {
        while (true) {
            System.out.println("\nДобро пожаловать в систему аренды квартир!");
            System.out.println("1. Войти в аккаунт");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Выйти из программы");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    System.out.println("👋 Выход из программы. До свидания!");
                    return;
                }
                default -> System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // ✅ Добавлен метод login()
    public void login() {
        System.out.print("Введите ваш никнейм: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        loggedInUser = userRepository.loginUser(username, password);
        if (loggedInUser != null) {
            System.out.println("\n✅ Успешный вход!");
            showUserMenu();
        } else {
            System.out.println("\n❌ Ошибка входа: Неверный логин или пароль.");
        }
    }

    // ✅ Добавлен метод register()
    public void register() {
        System.out.print("Введите ваше имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите вашу фамилию: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите ваш номер телефона: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Введите ваш никнейм: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        boolean success = userRepository.registerUser(firstName, lastName, phoneNumber, username, password, "user");
        if (success) {
            System.out.println("✅ Регистрация успешна!");
        } else {
            System.out.println("❌ Ошибка регистрации. Попробуйте снова.");
        }
    }

    // Меню пользователя
    public void showUserMenu() {
        while (true) {
            System.out.println("\n🛠️ Ваш профиль:");
            System.out.println("Имя: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
            System.out.println("Телефон: " + loggedInUser.getPhoneNumber());
            System.out.println("💰 Баланс: " + loggedInUser.getBalance() + " KZT");
            System.out.println("🔐 Роль: " + loggedInUser.getRole());

            System.out.println("\nВыберите действие:");
            System.out.println("1. Пополнить кошелек");
            System.out.println("2. Посмотреть арендованные квартиры");
            System.out.println("3. Арендовать квартиру");
            System.out.println("4. Выйти из аккаунта");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println("💰 Функция пополнения кошелька в разработке.");
                case 2 -> System.out.println("🏠 Функция просмотра арендованных квартир в разработке.");
                case 3 -> System.out.println("🛒 Функция аренды квартиры в разработке.");
                case 4 -> {
                    System.out.println("👋 Вы вышли из аккаунта.");
                    loggedInUser = null;
                    return;
                }
                default -> System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
