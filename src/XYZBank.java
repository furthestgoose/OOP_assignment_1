import java.text.NumberFormat;
// Importing the NumberFormat class for formatting numbers

import java.util.Arrays;
// Importing the Arrays class for array manipulation

import java.util.InputMismatchException;
// Importing the InputMismatchException class for handling input errors

import java.util.Locale;
// Importing the Locale class for specifying formatting conventions

import java.util.Scanner;
// Importing the Scanner class for user input

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
            System.out.println("4 - UPDATE RECORDS");
            System.out.println("5- DELETE RECORDS");
            System.out.println("6 - EXIT");

            try {
                int option = userinput.nextInt(); // Get user input for menu option

                // Switch statement to handle menu options
                switch (option) {
                    case 1:
                        max_records = SetMax_Records(); // Set maximum number of records
                        break;
                    case 2:
                        if (max_records > 0) {
                            Record[] newRecords = New_record(records, max_records); // Create new records if max records is set
                            if (newRecords != null) {
                                records = newRecords;
                                num_records = records.length; // Update number of records
                            } else {// if records returns null max records has been reached
                                System.out.println("Maximum records reached");
                            }
                        } else {// if max records has not been set display an error message
                            System.out.println("Please set the maximum number of records first.");
                        }
                        break;
                    case 3:
                        if (records != null) {
                            Display(records, num_records, max_records); // Display records if there are registered records
                        } else {// if there are no registered records display an error message
                            System.out.println("Please create records first.");
                        }
                        break;
                    case 4:
                        if (records != null) {// if there are records registered call the modify records method
                            ModifyRecords(records, num_records);
                        } else {
                            System.out.println("Please create records first");// if no records are registered show the user an error
                        }
                        break;
                    case 5:
                        if (records != null){// if there are records registered call the delete records method
                            int prev_record_len = records.length; // log the length of the records array before the method is called
                            records = DeleteRecord(records, num_records);
                            if (records.length < prev_record_len){/*if the new length of records is less than the length before the method was called,
                             decrement num of records by 1 */
                                num_records--;
                            }
                        } else {// if there are no records registered display an error message
                            System.out.println("Please create records first");
                        }
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0); // Exit the program
                    default:// if the user does not enter a valid input show an error message
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
                } else {// if input is below 0, display an error
                    System.out.println("Input must be above 0");
                }
            } else {// if input is not a number show an error
                System.out.println("Input must be an integer");
            }
        }
    }

    // Method to create new records
    public static Record[] New_record(Record[] existingRecords, int max_records) {
        Scanner scanner = new Scanner(System.in);
        int num_records = 0; // Number of records to create
        boolean validInput = false; // Flag for valid input

        if (existingRecords == null) {
            existingRecords = new Record[0]; // Initialize existingRecords to an empty array if there are no existing records
        }

        // Calculate the remaining capacity based on existing records
        int remainingCapacity = max_records - existingRecords.length;

        // Prompt for number of records until valid input
        while (!validInput) {
            if (remainingCapacity == 0){
                return null;// if max records has been reached return null
            }
            System.out.println("How many records would you like to create?");// prompt the user on how many records they would like to create
            if (scanner.hasNextInt()) {
                num_records = scanner.nextInt(); // Read user input
                if (num_records > 0 && num_records <= remainingCapacity) {
                    validInput = true; // Valid input
                } else {
                    System.out.println("Number of records must be between 1 and " + remainingCapacity + ".");
                    // if the user enters a number that is too high, display the range available
                }
            } else {// if an invalid input is entered show an error message
                System.out.println("Please enter a valid integer value.");
                scanner.next(); // Consume invalid input
            }
        }

        // Create a new array to hold all records
        Record[] records = Arrays.copyOf(existingRecords, existingRecords.length + num_records);

        // Create and add new records to the array
        for (int i = 0; i < num_records; i++) {
            Record record = new Record(); // Create a new record
            // methods to set record variables
            record.setRecordID();
            record.setCustomerID();
            record.setLoanType();
            record.setInterestRate();
            record.setAmountRemaining();
            record.setTimeLeft();
            records[i + existingRecords.length] = record; // Store the record in the array
        }
        System.out.println("Records created successfully.");
        return records; // Return the array of records
    }





    // Method to display records
    public static void Display(Record[] records, int num_records, int max_records) {
        System.out.println("Maximum number of records: " + max_records);// max records displayed
        System.out.println("Registered Records: " + num_records);// registered records displayed
        // Print header for record display
        System.out.printf("%-9s %-12s %-10s %-8s %-13s %-9s\n", "RecordID", "CustomerID", "LoanType", "IntRate", "AmountLeft", "TimeLeft");
        // Loop through each record and print its details
        for (int i = 0; i < num_records; i++) {
            Record record = records[i]; // Retrieve the current record
            String formattedRecordID = String.format("%06d", record.getRecordID());// adds leading zeros to recordID
            String formattedInterestRate = String.format("%.2f%%", record.getInterest_Rate());// add percentage sign to end of interest rate
            long amountInThousands = record.getAmount_Remaining() * 1000;// multiples amount owed by 1000 for output purposes
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);// formats amount owed in uk format
            String formattedAmountRemaining = currencyFormat.format(amountInThousands);// adds decimals to the end of amount owed
            String timeLeftString = record.getTime_Left() + " years";// appends the years string to the end of time left

            // Print the record information with specific column widths
            System.out.printf("%-9s %-12s %-10s %-8s %-13s %-9s\n", formattedRecordID,
                    record.getCustomerID(), record.getLoan_Type(), formattedInterestRate, formattedAmountRemaining, timeLeftString); // Output each record with formatted output
        }
        System.out.println(" ");// space added for aesthetic purposes
    }

    // Method to modify records
    public static void ModifyRecords(Record[] records, int num_records) {
        Scanner scanner = new Scanner(System.in);
        // Display a list of records for the user to select from
        System.out.println("Select a record to modify:");
        int i;
        for (i = 0; i < num_records; i++) {
            System.out.println((i + 1) + ". Record ID: " + records[i].getRecordID());
            // loops through records and displays the ID of each one
        }
        System.out.println((i + 1) + ". Exit To Main Menu");
        // Read user input for record selection
        int selection = scanner.nextInt();
        if (selection >= 1 && selection <= num_records) {
            // if selection is more than 1 and is less than or equal to max records new options are provided
            Record selectedRecord = records[selection - 1];
            // Display options for modifying specific fields
            System.out.println("Select a field to modify:");
            System.out.println("1. Customer ID");
            System.out.println("2. Loan Type");
            System.out.println("3. Interest Rate");
            System.out.println("4. Amount Remaining");
            System.out.println("5. Time Left");
            int fieldSelection = scanner.nextInt();
            switch (fieldSelection) {
                case 1:
                    selectedRecord.setCustomerID();
                    break;
                case 2:
                    selectedRecord.setLoanType();
                    break;
                case 3:
                    selectedRecord.setInterestRate();
                    break;
                case 4:
                    selectedRecord.setAmountRemaining();
                    break;
                case 5:
                    selectedRecord.setTimeLeft();
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
            /* uses a switch statement and print statements as well as record methods to allow for,
            modification of record values for the specifically chosen record
             */
            System.out.println("Record modified successfully.");
        } else if (selection == i + 1) {
            // if the exit option is chosen the method exits to the main menu
            System.out.println("Exiting To Main Menu");
        } else {
            // if an invalid input is detected an error message is shown
            System.out.println("Invalid record selection.");
        }
    }
    // Method to delete records
    public static Record[] DeleteRecord(Record[] records, int num_records) {
        Scanner scanner = new Scanner(System.in);
        // Create a new scanner object for user input

        // Display a list of records for the user to select from
        System.out.println("Select a record to delete:");
        int i;
        // Declare a variable to iterate over records
        for (i = 0; i < num_records; i++) {
            System.out.println((i + 1) + ". Record ID: " + records[i].getRecordID());
        }
        // Loop through records and display the ID of each one

        // Prompt for cancellation
        System.out.println((i + 1) + ". Cancel");

        // Read user input for record selection
        int selection = scanner.nextInt();

        if (selection >= 1 && selection <= num_records) {
            // If the selection is within the valid range
            Record[] updatedRecords = new Record[num_records - 1];
            // Create a new array to hold updated records
            int index = 0;
            // Initialize index for the updatedRecords array
            for (int j = 0; j < num_records; j++) {
                // Loop through records
                if (j != selection - 1) {
                    // If the current record is not the one to be deleted
                    updatedRecords[index++] = records[j];
                    // Copy the record to the updatedRecords array
                }
            }
            // Notify successful deletion
            System.out.println("Record deleted successfully.");
            return updatedRecords;
            // Return the updated array of records
        } else if (selection == i + 1) {
            // If the user selects to cancel
            System.out.println("Deletion cancelled.");
            return records;
            // Return the original array if deletion is cancelled
        } else {
            // If the selection is invalid
            System.out.println("Invalid record selection.");
            return records;
            // Return the original array if selection is invalid
        }
    }
}
