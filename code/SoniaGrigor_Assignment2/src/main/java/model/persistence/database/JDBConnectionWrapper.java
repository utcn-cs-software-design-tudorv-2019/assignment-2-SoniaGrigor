package model.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBConnectionWrapper {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost/test";

    private static final String USER = "postgres";
    private static final String PASS = "admmin";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBConnectionWrapper(String schema) {
        try {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
            props.setProperty("ssl", "true");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, props);
            //connection = DriverManager.getConnection(DB_URL + schema, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }
}
