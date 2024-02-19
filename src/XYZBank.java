import java.util.InputMismatchException;
import java.util.Scanner;

// Main class for XYZ Bank management
public class XYZBank {
    // Main method to run the program
    public static void main(String[] args) {
        int max_records = 0; // Maximum number of records
        Record[] records = null; // Array to hold records
        int num_records = 0; // Number of created records

        // Main menu loop
        while (true) {
            Scanner userinput = new Scanner(System.in);
            // Display main menu options
            System.out.println("------ MAIN MENU ------");
            System.out.println("1 - SET MAXIMUM RECORDS");
            System.out.println("2 - CREATE NEW RECORD");
            System.out.println("3 - DISPLAY RECORDS");
            System.out.println("4 - EXIT");

            try {
                int option = userinput.nextInt(); // Get user input for menu option

                // Switch statement to handle menu options
                switch (option) {
                    case 1:
                        max_records = SetMax_Records(); // Set maximum number of records
                        break;
                    case 2:
                        if (max_records > 0) {
                            records = New_record(max_records); // Create new records
                            num_records = records.length; // Update number of records
                        } else {
                            System.out.println("Please set the maximum number of records first.");
                        }
                        break;
                    case 3:
                        if (records != null) {
                            Display(records, num_records, max_records); // Display records
                        } else {
                            System.out.println("Please create records first.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0); // Exit the program
                    default:
                        System.out.println("Please enter a valid input");
                }
            } catch (InputMismatchException e) {
                // catches invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
                userinput.nextLine(); // Consume the invalid input
            }
        }
    }


    // Method to set maximum number of records
    public static int SetMax_Records() {
        while (true) {
            Scanner userinput = new Scanner(System.in);
            System.out.println("Please enter the max amount of records: ");
            if (userinput.hasNextInt()) {
                int record_num = userinput.nextInt();
                if (record_num > 0) {
                    return record_num; // Return the max records
                } else {
                    System.out.println("Input must be above 0");
                }
            } else {
                System.out.println("Input must be an integer");
            }
        }
    }

    // Method to create new records
    public static Record[] New_record(int max_records) {
        Scanner scanner = new Scanner(System.in);
        int num_records = 0; // Number of records to create
        boolean validInput = false; // Flag for valid input

        // Prompt for number of records until valid input
        while (!validInput) {
            System.out.println("How many records would you like to create?");
            if (scanner.hasNextInt()) {
                num_records = scanner.nextInt(); // Read user input
                if (num_records > 0 && num_records <= max_records) {
                    validInput = true; // Valid input
                } else {
                    System.out.println("Number of records must be between 1 and " + max_records + ".");
                }
            } else {
                System.out.println("Please enter a valid integer value.");
                scanner.next(); // Consume invalid input
            }
        }

        Record[] records = new Record[num_records]; // Array to hold records
        for (int i = 0; i < num_records; i++) {
            Record record = new Record(); // Create a new record
            record.inputCustomValues(); // Input custom values for the record
            records[i] = record; // Store the record in the array
        }
        System.out.println("Records created successfully.");
        return records; // Return the array of records
    }

    // Method to display records
    public static void Display(Record[] records, int num_records, int max_records) {
        System.out.println("Maximum number of records: " + max_records);
        System.out.println("Registered Records: " + num_records);
        // Print header for record display
        System.out.printf("%-9s %-12s %-10s %-8s %-13s %-9s\n", "RecordID", "CustomerID", "LoanType", "IntRate", "AmountLeft", "TimeLeft");
        // Loop through each record and print its details
        for (int i = 0; i < num_records; i++) {
            records[i].output(); // Output each record with formatted output
        }
        System.out.println(" ");
    }
}





