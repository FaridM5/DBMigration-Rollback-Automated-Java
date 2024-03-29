package a1.db.migration.faridm5;
import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class App {
    private static final String CONFIG_FILE = "config.properties";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Load configuration
        Properties config = new Properties();
        try {
            config.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }

        String editConfig;
        do {
            System.out.println("Editing config file: (+/-)?");
            editConfig = scanner.nextLine().trim();
        } while (!editConfig.equals("+") && !editConfig.equals("-"));

        if (editConfig.equals("+")) {
            System.out.println("Enter username for the database connection:");
            String username = scanner.nextLine().trim();
            config.setProperty("username", username);

            System.out.println("Enter password for the database connection:");
            String password = scanner.nextLine().trim();
            config.setProperty("password", password);
            try {
                config.store(new FileOutputStream(CONFIG_FILE), null);
            } catch (IOException e) {
                System.out.println("Error saving configuration: " + e.getMessage());
            }
        }
        System.out.println("Enter database name to connect:");
        String dbName = scanner.nextLine().trim();

        try {
            String user = config.getProperty("username");
            String password = config.getProperty("password");
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

            String action;
            boolean end = false;

            while (!end) {
                System.out.println("What do you want to do?: migrate / rollback / end");
                action = scanner.nextLine().trim().toLowerCase();

                switch (action) {
                    case "migrate":
                        Migration.migrate(connection);
                        TablePrinter.printTables(statement);
                        break;
                    case "rollback":
                        Rollback.rollback(connection);
                        TablePrinter.printTables(statement);
                        break;
                    case "end":
                        System.out.println("Ending program.");
                        end = true; // Set end to true to exit the loop
                        break;
                    default:
                        System.out.println("Invalid action. Please choose 'migrate', 'rollback', or 'end'.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong. Please restart the app :)");
        } finally {
            scanner.close();
        }
    }
}
