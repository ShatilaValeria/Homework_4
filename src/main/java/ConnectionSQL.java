import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection =
                    DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\test.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No sqlite driver");
        }
        return connection;
    }
}
