import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
    Menu menu = new Menu();

    private final String name;
    private String address;
    private String currencyMoney;

    public Account(String name, String address, String currencyMoney) {
        this.name = name;
        this.address = address;
        this.currencyMoney = currencyMoney;
    }

    public Account(String name) {
        this.name = name;
    }

    public void registrationRequest() {
        System.out.println(currencyMoney);
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE  name='"
                    + getName() + "';");
            while (resultSet.next()) {
                System.out.println("Пользователь с таким именем уже существует");
                System.out.println("Придумайте себе другое имя(логин)");
                menu.startMenu();
            }

            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Users(name, address)" +
                    "VALUES('" + getName() + "', '" + getAddress() + "')");
            resultSet = statement.executeQuery("SELECT userId FROM Users" +
                    " WHERE userId=last_insert_rowid();");
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                statement.executeUpdate("INSERT INTO Accounts(userId,  balance, currency)" +
                        "VALUES (" +
                        "(SELECT userId FROM Users WHERE  userId='" + userId + "') ,'" + 0 + "', '" +
                        getCurrencyMoney() + "');");
                resultSet = statement.executeQuery("SELECT * FROM Accounts WHERE  currency='"
                        + getCurrencyMoney() + "' AND userId='" + userId + "';");
                while (resultSet.next()) {
                    int accountId = resultSet.getInt("accountId");
                    statement.executeUpdate("INSERT INTO Transactions(accountId, amount)" +
                            "VALUES (" +
                            "(SELECT accountId FROM Accounts WHERE  accountId='" + accountId + "') ,'" + 0 + "');");
                    AccountActions accountActions = new AccountActions(accountId, getName(), getCurrencyMoney());
                    statement.close();
                    connection.close();
                    accountActions.mainMenu();
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void userVerificationRequest() {
        Connection connection = new ConnectionSQL().connection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Accounts " +
                    "WHERE userId=(SELECT userId FROM Users " +
                    "WHERE name='" + getName() + "');");
            while (resultSet.next()) {
                int accountId = resultSet.getInt("accountId");
                String currency = resultSet.getString("currency");
                System.out.println(getName() + " вы успешно вошли в свой аккаунт");
                System.out.println(currency + " валюта, которая хранится на данном аккаунте");
                AccountActions accountActions = new AccountActions(accountId, getName(), currency);
                statement.close();
                connection.close();
                accountActions.mainMenu();
            }
            System.out.println("У нас нет такого пользователя!");
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCurrencyMoney() {
        return currencyMoney;
    }
}
