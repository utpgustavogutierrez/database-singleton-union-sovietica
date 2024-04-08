import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Sakila {
    private static Sakila instance;
    private Connection connection;

    private Sakila() {
        try {
           
            connection = DriverManager.getConnection("jdbc:sqlite:./sqlite-sakila.db");
            connection.createStatement().executeUpdate("PRAGMA foreign_keys = ON"); // Enable foreign key support
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public static synchronized Sakila getInstance() {
        if (instance == null) {
            instance = new Sakila();
        }
        return instance;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }

    

    public static void main(String[] args) {
        Sakila sakila = Sakila.getInstance();
        sakila.closeConnection();
    }
}
