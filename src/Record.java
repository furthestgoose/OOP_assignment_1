import java.util.Random;
import java.util.Scanner;
// import scanner to get user input
import java.text.NumberFormat;
// import number format for display of values later
import java.util.Locale;
// import locale package to display currency later

public class Record {
    private int RecordID;
    private String CustomerID;
    private String Loan_Type;
    private double Interest_Rate;
    private int Amount_Remaining;
    private int Time_Left;
    /* declaring private variables for record ID,
    customer ID, loan type, interest rate,
    amount remaining and time left
    */

    // Getter and setter methods for RecordID
    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int recordID) {
        RecordID = recordID;
    }

    // Getter and setter methods for CustomerID
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    // Getter and setter methods for Loan_Type
    public String getLoan_Type() {
        return Loan_Type;
    }

    public void setLoan_Type(String loan_Type) {
        Loan_Type = loan_Type;
    }

    // Getter and setter methods for Interest_Rate
    public double getInterest_Rate() {
        return Interest_Rate;
    }

    public void setInterest_Rate(double interest_Rate) {
        Interest_Rate = interest_Rate;
    }

    // Getter and setter methods for Amount_Remaining
    public int getAmount_Remaining() {
        return Amount_Remaining;
    }

    public void setAmount_Remaining(int amount_Remaining) {
        Amount_Remaining = amount_Remaining;
    }

    // Getter and setter methods for Time_Left
    public int getTime_Left() {
        return Time_Left;
    }

    public void setTime_Left(int time_Left) {
        Time_Left = time_Left;
    }

    // Method to input custom values and validate them
    public void inputCustomValues() {
        Scanner scanner = new Scanner(System.in);

        // Input Record ID
        boolean validInput = false;
        // setting validInput flag to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Record ID (6 digits, leave blank for default):");
            String input = scanner.nextLine();
            // user is prompted to input recordID
            if (input.isEmpty()) {
                RecordID = (int)(Math.random() * 999999) + 1;
                validInput = true;
                // if input is empty validInput flag is set to true and random values are assigned
            } else if (input.length() == 6 && input.matches("\\d+")) {
                RecordID = Integer.parseInt(input);
                validInput = true;
                /* if the input is 6 characters long and all numbers (validated using regex)
                the custom value is assigned and
                validInput flag is set to true */
            } else {
                System.out.println("Invalid input. Record ID must be 6 digits.");
                // if neither of these conditions are true an error message is displayed
            }
        }

        // Input Customer ID
        validInput = false;
        // validInput flag reset to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Customer ID (3 capital letters followed by 3 digits, leave blank for default):");
            String input = scanner.nextLine();
            //User is prompted to input customer ID
            if (input.isEmpty()) {
                CustomerID = generateRandomCustomerID();
                validInput = true;
                // if the input is empty the validInput flag is set to true and random values are assigned
            } else if (input.length() == 6 && input.substring(0, 3).matches("[A-Z]+") && input.substring(3).matches("\\d+")) {
                CustomerID = input;
                validInput = true;
                /* if the input is 6 characters long,
                 the first 3 characters are Capital letters and the last 3 characters are numbers (validated using regex)
                 the custom value is assigned and validInput flag is set to true */
            } else {
                System.out.println("Invalid input. Customer ID must be 3 capital letters followed by 3 digits.");
                // if neither of these conditions are true an error message is displayed
            }
        }

        // Input Loan Type
        validInput = false;
        // validInput flag is reset to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Loan Type (Auto, Builder, Mortgage, Personal, Other, leave blank for default):");
            String input = scanner.nextLine();
            // user is prompted to enter loan type
            if (input.isEmpty() || input.equals("Auto") || input.equals("Builder") || input.equals("Mortgage") || input.equals("Personal") || input.equals("Other")) {
                if (input.isEmpty()){
                    int random = (int)(Math.random() * 5) + 1;
                    switch (random){
                        case 1:
                            Loan_Type = "Auto";
                            break;
                        case 2:
                            Loan_Type = "Builder";
                            break;
                        case 3:
                            Loan_Type = "Mortgage";
                            break;
                        case 4:
                            Loan_Type = "Personal";
                            break;
                        case 5:
                            Loan_Type = "Other";
                            break;
                    }
                }else{
                    Loan_Type = input;
                }
                validInput = true;
                /* if the input is one of the valid options it will be assigned to Loan_type
                otherwise a random value is set. as well as the validInput is set to true */
            } else {
                System.out.println("Invalid input. Loan Type must be Auto, Builder, Mortgage, Personal, or Other.");
                // if the input is not valid an error message is displayed
            }
        }

        // Input Interest Rate
        validInput = false;
        // validInput flag is reset to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Interest Rate (leave blank for default):");
            String input = scanner.nextLine();
            // the user is prompted to enter an interest rate
            if (input.isEmpty() || input.matches("\\d*\\.?\\d+")) {
                if (input.isEmpty()){
                    Interest_Rate = (Math.random() * 50.00) + 0.01;
                }else {
                    Interest_Rate = Double.parseDouble(input);
                }
                validInput = true;
                /* if the input is a double it is assigned to interest rate and if it is empty a random value is assigned
                and then the validInput flag changes to true*/
            } else {
                System.out.println("Invalid input. Interest Rate must be a valid number.");
                // if the input is not valid an error message is displayed
            }
        }

        // Input Amount Remaining
        validInput = false;
        // validInput flag is reset to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Amount Remaining (leave blank for default):");
            String input = scanner.nextLine();
            // the user is prompted to enter the amount remaining
            if (input.isEmpty() || input.matches("\\d+")) {
                if (input.isEmpty()){
                    Amount_Remaining = (int) (Math.random() * 300) + 1;
                }else{
                    Amount_Remaining = Integer.parseInt(input);
                }
                validInput = true;
                /* if the input is a number it is assigned to the amount remaining variable and if it is empty a random value is assigned
                and then the validInput flag is changed to true*/
            } else {
                System.out.println("Invalid input. Amount Remaining must be a valid number.");
                // if the input is not valid and error message is displayed
            }
        }

        // Input Time Left
        validInput = false;
        //validInput flag is reset to false
        while (!validInput) {
            // while loop is broken if the validInput flag becomes true
            System.out.println("Enter Time Left (leave blank for default):");
            String input = scanner.nextLine();
            // the user is prompted to enter the time remaining
            if (input.isEmpty() || input.matches("\\d+")) {
                if (input.isEmpty()){
                    Time_Left = (int)(Math.random() * 50)+ 1;
                }else{
                    Time_Left = Integer.parseInt(input);
                }
                validInput = true;
                /* if the input is a number it is assigned to the time left variable and if it is empty a random value is assigned
                and then the validInput flag is changed to true*/
            } else {
                System.out.println("Invalid input. Time Left must be a valid number.");
                // if the input is not valid and error message is displayed
            }
        }
    }
    // Method to generate a random customer ID with 3 uppercase letters followed by 3 digits
    private String generateRandomCustomerID() {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        // Generate 3 random uppercase letters
        for (int i = 0; i < 3; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }

        // Generate 3 random digits
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
    // Method to output record information
    public void output() {
        String formattedRecordID = String.format("%06d", RecordID);
        // this adds leading zeros to the record ID if necessary
        String formattedInterestRate = String.format("%.2f%%", Interest_Rate);
        // this adds a % to the end of the interest rate
        long amountInThousands = Amount_Remaining * 1000;
        // this multiplies amount remaining by 1000
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        // this sets the currency format to the UK locale
        String formattedAmountRemaining = currencyFormat.format(amountInThousands);
        // this sets the currency format to be in the thousands
        String timeLeftString = Time_Left + " years";
        // this adds the year string to the end of time left
        System.out.printf("%-9s %-12s %-10s %-8s %-13s %-9s\n", formattedRecordID,
                CustomerID, Loan_Type, formattedInterestRate, formattedAmountRemaining, timeLeftString);
        // this outputs the data with specific column widths
    }
}


