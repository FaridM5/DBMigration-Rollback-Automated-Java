package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    public static Connection getConnection(String dbName, String user, String password) {
        try {
            String url = "jdbc:postgresql://localhost:5432/" + dbName;
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Seen an error while connecting to the database!");
            return null;
        }
    }
}
