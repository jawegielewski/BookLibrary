package pl.jawegiel.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    String driverClassName = "org.apache.derby.jdbc.EmbeddedDriver";
    String connectionUrl = "jdbc:derby:library;create=true";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        try {
            // required if we want to set AUTO_INCREMENT value to 1
            System.setProperty("derby.language.sequence.preallocator", "1");
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) 
            connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }
}