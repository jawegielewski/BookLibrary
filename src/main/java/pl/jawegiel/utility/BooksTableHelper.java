package pl.jawegiel.utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksTableHelper {

    public static boolean isTableExist(Connection connection, String tablename) throws SQLException {
        boolean isTableExist = false;
        if (connection != null) {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, tablename.toUpperCase(), null);
            if (rs.next()) {
                isTableExist = true;
            } else
                isTableExist = false;
            
            rs.close();
        }
        return isTableExist;
    }
}
