package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class OldTablesChecker {

    public static boolean checkForOldTables(Connection connection) {
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet studentsOld = dbMetaData.getTables(null, null, "STUDENTS_OLD", null);
            ResultSet interestsOld = dbMetaData.getTables(null, null, "INTERESTS_OLD", null);
            return studentsOld.next() && interestsOld.next();
        } catch (Exception e) {
            System.out.println("An error occurred while checking for old tables: " + e.getMessage());
            return false;
        }
    }
}
