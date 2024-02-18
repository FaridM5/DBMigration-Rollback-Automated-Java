package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class OldTablesChecker {

    public static boolean checkForOldTables(Connection connection) {
        String schema = null; // "public" for PostgreSQL default schema
        String studentsTableName = "STUDENTS_OLD".toLowerCase(); //
        String interestsTableName = "INTERESTS_OLD".toLowerCase(); //

        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            try (ResultSet studentsOld = dbMetaData.getTables(null, schema, studentsTableName, null);
                 ResultSet interestsOld = dbMetaData.getTables(null, schema, interestsTableName, null)) {
                return studentsOld.next() && interestsOld.next();
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking for old tables: " + e.getMessage());
            return false;
        }
    }
}
