package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Migration {

    public static void migrate(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Create new STUDENTS table with updated schema
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS STUDENTS_NEW (STUDENT_ID SERIAL PRIMARY KEY, ST_NAME VARCHAR(30), ST_LAST VARCHAR(30))");
            System.out.println("Created new STUDENTS table with updated schema.");

            // Create new INTERESTS table with updated schema (using array for INTERESTS)
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS INTERESTS_NEW (ST_ID INTEGER, INTERESTS TEXT[])");
            System.out.println("Created new INTERESTS table with updated schema.");

            // Copy data from old STUDENTS to new STUDENTS_NEW table
            statement.executeUpdate("INSERT INTO STUDENTS_NEW (STUDENT_ID, ST_NAME, ST_LAST) SELECT ST_ID, ST_NAME, ST_LAST FROM STUDENTS ON CONFLICT DO NOTHING");
            System.out.println("Copied data to new STUDENTS table.");

            // Aggregate and copy data from old INTERESTS to new INTERESTS_NEW table
            statement.executeUpdate("INSERT INTO INTERESTS_NEW (ST_ID, INTERESTS) SELECT ST_ID, ARRAY_AGG(INTEREST) AS INTERESTS FROM INTERESTS GROUP BY ST_ID ON CONFLICT (ST_ID) DO NOTHING");
            System.out.println("Aggregated and copied data to new INTERESTS table.");

            // Rename old tables to *_OLD
            statement.executeUpdate("ALTER TABLE STUDENTS RENAME TO STUDENTS_OLD");
            statement.executeUpdate("ALTER TABLE INTERESTS RENAME TO INTERESTS_OLD");

            // Rename new tables to the original names
            statement.executeUpdate("ALTER TABLE STUDENTS_NEW RENAME TO STUDENTS");
            statement.executeUpdate("ALTER TABLE INTERESTS_NEW RENAME TO INTERESTS");

            System.out.println("Migration completed successfully. Old tables are renamed with _OLD suffix.");
        } catch (Exception e) {
            System.out.println("An error occurred during the migration: " + e.getMessage());

        }
    }
}
