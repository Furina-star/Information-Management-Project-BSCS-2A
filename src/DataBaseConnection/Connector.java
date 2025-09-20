package DataBaseConnection;
        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/learningtracker";
        String user = "root";
        String pass = "";

        return DriverManager.getConnection(url, user, pass);
    }
}

