package pl.jawegiel.utility;

import java.sql.Connection;
import java.sql.SQLException;

public class BooksTableCreator {

    public static void createBooksTable(Connection connection) throws SQLException {
        connection.createStatement().execute("CREATE TABLE books " +
                "(ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "TITLE VARCHAR(100) NOT NULL, " +
                "\"YEAR\" INTEGER, " +
                "AUTHOR VARCHAR(100), " +
                "AVAILABLE BOOLEAN, " +
                "PERSON_WHO_LENT VARCHAR(100), " +
                "CONSTRAINT primary_key PRIMARY KEY (ID))");
    }
}
