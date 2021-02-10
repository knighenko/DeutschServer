package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreDB {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mydb";
    static final String USER = "myuser";
    static final String PASS = "123";
    /**
     * Create DB connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            // log.log(Level.INFO, "Connected to Database: " + DB_URL);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * Close DB connection
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
        // log.log(Level.INFO, "Closed connection to Database: " + DB_URL);
    }
    /**
     * Method create e_mail in table USERS
     * return true when create
     */
    public static boolean createUser(String e_mail, String password, String deviceToken) {
        boolean flag = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into ACCOUNTS (e_mail,password,devicetoken,push) values (?,?,?,?)");
            statement.setString(1, e_mail);
            statement.setString(2, password);
            statement.setString(3, deviceToken);
            statement.setBoolean(4, false);
            statement.executeUpdate();
            statement.close();
            closeConnection(connection);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return flag;
    }
}
