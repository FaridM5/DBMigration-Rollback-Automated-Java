package a1.db.migration.faridm5;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePrinter {
    public static void printTables(Statement statement) {
        printTable(statement, "STUDENTS");
        printTable(statement, "INTERESTS");
    }

    private static void printTable(Statement statement, String tableName) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("Table: " + tableName);
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = rs.getString(i);
                    System.out.print(columnName + ": " + value + ", ");
                }
                System.out.println(); // Move to the next line after printing a row
            }
            System.out.println("End of " + tableName + " table.");
        } catch (SQLException e) {
            System.out.println("Seen an error while printing the " + tableName + " table: " + e.getMessage());
        }
    }
}

