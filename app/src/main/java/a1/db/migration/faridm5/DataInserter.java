package a1.db.migration.faridm5;

import java.sql.Statement;

public class DataInserter {
    public static void insertData(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO STUDENTS VALUES (1, 'Konul', 'Gurbanova'), (2, 'Shahnur', 'Isgandarli'), (3, 'Natavan', 'Mammadova');");
            statement.executeUpdate("INSERT INTO INTERESTS VALUES (1, 'Tennis'), (1, 'Literature'), (2, 'Math'), (2, 'Tennis'), (3, 'Math'), (3, 'Music'), (2, 'Football'), (1, 'Chemistry'), (3, 'Chess');");
            System.out.println("The values are successfully inserted into the \"STUDENTS\" and \"INTERESTS\" table!");
        } catch (Exception e) {
            System.out.println("Seen an error while inserting data to the tables!");
        }
    }
}
