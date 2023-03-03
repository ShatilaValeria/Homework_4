import java.util.Scanner;

public class AccountActions {
    Scanner scanner = new Scanner(System.in);
    OperationMenu operationMenu = new OperationMenu();

    private final String name;
    private final int accountId;
    private final String currency;

    public AccountActions(int accountId, String name, String currency) {
        this.accountId = accountId;
        this.name = name;
        this.currency = currency;
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("Введите номер операции");
            System.out.println("1. Пополнение счёта");
            System.out.println("2. Снятие ");
            System.out.println("3. Баланс");
            System.out.println("4. Выйти");
            System.out.println();
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    this.enrollmentMenu();
                    return;
                }
                case 2 -> {
                    this.withdrawalOfFoundsMenu();
                    return;
                }
                case 3 -> {
                    this.checkingTheBalanceMenu();
                    return;
                }
                case 4 -> {
                    System.out.println("Выход");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод данных");
            }
        } while (true);
    }

    public void enrollmentMenu() {
        System.out.print("Введите сумму пополнения\n" +
                "Разделение целого от дробного при помощи ',' \n" +
                "Напоминаем, что размер суммы не может привышать 100'000'000");
        double money = scanner.nextDouble();
        if (money >= 100000000) {
            System.out.println("Операция невозможна \n" +
                    "Размер суммы не может привышать 100'000'000");
        } else if ((money * 10000) % 10 != 0) {
            System.out.println("Дробная часть чисел может быть ограничена 3 знаками\n" +
                    "У вас больше\n" +
                    "Разделение целого от дробного при помощи ','");
        } else if (money < 0) {
            System.out.println("Вы ввели отрицательное число");
        } else {
            operationMenu.requestForReplenishment(accountId, name, money);
        }
        this.mainMenu();
    }

    public void withdrawalOfFoundsMenu() {
        System.out.print("Введите сумму снятия \n" +
                "Разделение целого от дробного при помощи ',' \n" +
                "Напоминаем, что размер суммы не может привышать 100'000'000");
        double money = scanner.nextDouble();
        if (money >= 100000000) {
            System.out.println("Операция невозможна \n" +
                    "Размер суммы не может привышать 100'000'000");
        } else if ((money * 10000) % 10 != 0) {
            System.out.println("Дробная часть чисел может быть ограничена 3 знаками \n" +
                    "У вас больше\n" +
                    "Разделение целого от дробного при помощи ','");
        } else if (money < 0) {
            System.out.println("Вы ввели отрицательное число");
        } else {
            operationMenu.withdrawalOfFounds(accountId, name, money);
        }
        this.mainMenu();
    }

    public void checkingTheBalanceMenu() {
        operationMenu.checkingTheBalance(accountId, currency);
        this.mainMenu();
    }
}
