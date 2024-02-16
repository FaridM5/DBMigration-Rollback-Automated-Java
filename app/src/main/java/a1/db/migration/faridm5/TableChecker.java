package a1.db.migration.faridm5;

import java.sql.ResultSet;
import java.sql.Statement;

public class TableChecker {
    public static boolean checkTables(Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT to_regclass('public.STUDENTS'), to_regclass('public.INTERESTS');");
            if (rs.next()) {
                return rs.getString(1) != null || rs.getString(2) != null;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Seen an error while checking the table existence!");
            return false;
        }
    }
}
