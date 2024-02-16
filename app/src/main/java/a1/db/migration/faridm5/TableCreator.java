package a1.db.migration.faridm5;

import java.sql.Statement;

public class TableCreator {
    public static void createTables(Statement statement) {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS INTERESTS, STUDENTS;");
            statement.executeUpdate("CREATE TABLE STUDENTS (ST_ID INTEGER, ST_NAME VARCHAR(20), ST_LAST VARCHAR(20));");
            statement.executeUpdate("CREATE TABLE INTERESTS (STUDENT_ID INTEGER, INTEREST VARCHAR(20));");
            System.out.println("Tables \"STUDENTS\" and \"INTERESTS\" are successfully created!");
        } catch (Exception e) {
            System.out.println("Seen an error while creating the table!");
        }
    }
}
