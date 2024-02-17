package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Rollback {

    public static void rollback(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            // Utilize OldTablesChecker to determine if rollback is feasible
            if (OldTablesChecker.checkForOldTables(connection)) {
                // Drop the current tables
                statement.execute("DROP TABLE IF EXISTS STUDENTS");
                statement.execute("DROP TABLE IF EXISTS INTERESTS");

                // Rename old tables back to their original names
                statement.execute("ALTER TABLE STUDENTS_OLD RENAME TO STUDENTS");
                statement.execute("ALTER TABLE INTERESTS_OLD RENAME TO INTERESTS");

                System.out.println("Rollback completed successfully. Tables restored to their original state.");
            } else {
                System.out.println("Rollback cannot be performed as the original tables do not exist or migration has not been done before.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during the rollback: " + e.getMessage());
        }
    }
}
