package DB.operationRequest;

import connection.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountOperation implements OperationRequest {

    public void requestForReplenishment(int accountId, String name, double money) {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE accountId='"
                    + accountId + "';");
            while (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                statement.executeUpdate("UPDATE Transactions SET amount ='" + money + "'" +
                        "WHERE accountId ='" + accountId + "';");
                resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE accountId='"
                        + accountId + "';");
                while (resultSet.next()) {
                    double amount = resultSet.getDouble("amount");
                    double i = balance + amount;
                    System.out.println(i);
                    if (i <= 2000000000) {
                        statement.executeUpdate("UPDATE Accounts SET balance = '" + i + "'" +
                                "WHERE accountId = '" + accountId + "';");
                        statement.executeUpdate("UPDATE Transactions SET amount ='" + 0 + "'" +
                                "WHERE accountId ='" + accountId + "';");
                        System.out.println(name + " ваш баланс успешно пополнен");
                    } else {
                        System.out.println("Сбой операции\n" +
                                "Общая сумма на балансе недолжна привышать 2.000.000.000\n" +
                                "Попробуйте ещё раз");
                    }
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void withdrawalOfFounds(int accountId, String name, double money) {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE accountId='"
                    + accountId + "';");
            while (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                statement.executeUpdate("UPDATE Transactions SET amount ='" + money + "'" +
                        "WHERE accountId ='" + accountId + "';");
                resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE accountId='"
                        + accountId + "';");
                while (resultSet.next()) {
                    double amount = resultSet.getDouble("amount");
                    double moneyNow = balance - amount;
                    if (moneyNow >= 0) {
                        System.out.println(moneyNow);
                        statement.executeUpdate("UPDATE Accounts SET balance = '" + moneyNow + "'" +
                                "WHERE accountId = '" + accountId + "';");
                        System.out.println(name + " ваши деньги...");
                        statement.executeUpdate("UPDATE Transactions SET amount ='" + 0 + "'" +
                                "WHERE accountId ='" + accountId + "';");
                    } else {
                        System.out.println("Недостаточно средств!");
                    }
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public void checkingTheBalance(int accountId, String currency) {
        System.out.println(accountId);
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE accountId='"
                    + accountId + "';");
            while (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                System.out.println("Ваш баланс:" + balance + " " + currency);
            }
            statement.close();
            connection.close();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
