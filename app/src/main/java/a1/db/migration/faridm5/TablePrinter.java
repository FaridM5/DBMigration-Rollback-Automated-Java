package a1.db.migration.faridm5;

import java.sql.ResultSet;
import java.sql.Statement;

public class TablePrinter {
    public static void printTables(Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM STUDENTS");
            while (rs.next()) {
                System.out.println("STUDENTS - ID: " + rs.getInt("ST_ID") + ", Name: " + rs.getString("ST_NAME") + ", Last Name: " + rs.getString("ST_LAST"));
            }
            rs = statement.executeQuery("SELECT * FROM INTERESTS");
            while (rs.next()) {
                System.out.println("INTERESTS - Student ID: " + rs.getInt("STUDENT_ID") + ", Interest: " + rs.getString("INTEREST"));
            }
            System.out.println("Tables are printed!");
        } catch (Exception e) {
            System.out.println("Seen an error while printing the tables!");
        }
    }
}
