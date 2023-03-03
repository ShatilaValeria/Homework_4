package menu.entryMenu;

import DB.loginRequest.LogInToYourAccount;

import java.util.Locale;
import java.util.Scanner;

public class StartMenu implements EntryMenu {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void startMenu() {
        int choice;
        System.out.println("Добро пожаловать");
        do {
            System.out.println();
            System.out.println("Введите номер операции");
            System.out.println("1. Войти в существующий аккаунт");
            System.out.println("2. Регистрация ");
            System.out.println("3. Выход ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> this.entryMenu();
                case 2 -> this.registrationMenu();
                case 3 -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    @Override
    public void registrationMenu() {
        int choice;
        System.out.println("Регистрация: ");
        System.out.print("Введите ваше имя: ");
        String name = scanner.next();
        String nameToLowerCase = name.toLowerCase(Locale.ROOT);
        do {
            System.out.println();
            System.out.println("Желаете при  регестрации указать адрес?");
            System.out.println("Введите номер операции");
            System.out.println("1. Да");
            System.out.println("2. Нет");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    String address = address();
                    String currency = currencyMoneyMenu();
                    LogInToYourAccount account = new LogInToYourAccount(nameToLowerCase, address, currency);
                    account.registrationRequest();
                    return;
                }
                case 2 -> {
                    String currency = currencyMoneyMenu();
                    LogInToYourAccount account = new LogInToYourAccount(nameToLowerCase, null, currency);
                    account.registrationRequest();
                    return;
                }
                case 3 -> {
                    System.out.println("Выход");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    @Override
    public void entryMenu() {
        System.out.println("Добро пожаловать");
        System.out.print("Введите ваше имя:");
        String name = scanner.next();
        String nameToLowerCase = name.toLowerCase(Locale.ROOT);
        LogInToYourAccount account = new LogInToYourAccount(nameToLowerCase);
        account.userVerificationRequest();
    }

    public String address() {
        System.out.print("Введите вашу улицу: ");
        String street = scanner.next();
        System.out.print("Введите номер вашего дома: ");
        String numHome = scanner.next();
        String address = "ул. " + street + " д. " + numHome;
        return address.toLowerCase(Locale.ROOT);
    }

    public String currencyMoneyMenu() {
        String currencyMoney;
        do {
            System.out.println();
            System.out.println("Выбор валюты на данном аккаунте:");
            System.out.println("1. BYN");
            System.out.println("2. USD ");
            System.out.println("3. EYR");
            System.out.println("4. RYB");
            int currency = scanner.nextInt();
            switch (currency) {
                case 1 -> {
                    currencyMoney = "BYN";
                    return currencyMoney;
                }
                case 2 -> {
                    currencyMoney = "USD";
                    return currencyMoney;
                }
                case 3 -> {
                    currencyMoney = "EYR";
                    return currencyMoney;
                }
                case 4 -> {
                    currencyMoney = "RYB";
                    return currencyMoney;
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }
}
