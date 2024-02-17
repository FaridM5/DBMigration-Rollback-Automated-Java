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
                System.out.println("The tables \"STUDENTS\" and \"INTERESTS\" already exist. Do you want to recreate the tables? (Y/N)");
                String input = scanner.nextLine().toUpperCase(); // Convert input to uppercase for case-insensitive comparison
                while (!input.equals("Y") && !input.equals("N")) {
                    System.out.println("Invalid input. Please enter 'Y' or 'N':");
                    input = scanner.nextLine().toUpperCase();
                }
                if ("Y".equals(input)) {
                    TableCreator.createTables(statement);
                    DataInserter.insertData(statement);
                }
            } else {
                TableCreator.createTables(statement);
                DataInserter.insertData(statement);
            }

            TablePrinter.printTables(statement);

            // Actual part
            System.out.println("What do you want to do?: migrate / rollback");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "migrate":
                    Migration.migrate(connection);
                    break;
                case "rollback":
                     System.out.println("Will be implemented!");
//                    if (OldTablesChecker.checkForOldTables(connection)) {
//                        Rollback.rollback(connection);
//                    } else {
//                        System.out.println("No migration has been performed previously to rollback.");
//                    }
                    break;
                default:
                    System.out.println("Invalid action. Please choose 'migrate' or 'rollback'.");
                    break;
            }

        } catch (Exception e) {
            System.out.println("Something went wrong. Please restart the app :)");
        } finally {
            scanner.close();
        }
    }
}
