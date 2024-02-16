package a1.db.migration.faridm5;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username in postgres: ");
        String user = scanner.nextLine();

        System.out.println("Enter password in postgres: ");
        String password = scanner.nextLine();

        System.out.println("Enter database name to connect: ");
        String dbName = scanner.nextLine();

        try {
            Connection connection = DatabaseUtils.getConnection(dbName, user, password);
            Statement statement = connection.createStatement();

            if (TableChecker.checkTables(statement)) {
                System.out.println("The tables \"STUDENTS\" and \"INTERESTS\" are already exist. Do you want to recreate the tables? (Y/N)");
                String input = scanner.nextLine();
                if ("Y".equalsIgnoreCase(input)) {
                    TableCreator.createTables(statement);
                    DataInserter.insertData(statement);
                }
            } else {
                TableCreator.createTables(statement);
                DataInserter.insertData(statement);
            }

            TablePrinter.printTables(statement);

        } catch (Exception e) {
            System.out.println("Something went wrong. Please restart the app :)");
        } finally {
            scanner.close();
        }
    }
}
