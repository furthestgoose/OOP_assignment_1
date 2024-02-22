import java.util.Random;
// imports the java random package to enable random number generation
import java.util.Scanner;
// imports the scanner package to allow user input


public class Record {
    private int RecordID;
    private String CustomerID;
    private String Loan_Type;
    private double Interest_Rate;
    private int Amount_Remaining;
    private int Time_Left;

    // Getter and setter methods
    public String getCustomerID() {
        return CustomerID;
    }

    public int getRecordID() {
        return RecordID;
    }

    public String getLoan_Type() {
        return Loan_Type;
    }

    public double getInterest_Rate() {
        return Interest_Rate;
    }

    public int getAmount_Remaining() {
        return Amount_Remaining;
    }
    public int getTime_Left() {
        return Time_Left;
    }

    public void setRecordID() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
        boolean validInput = false;
        // setting validInput flag to false
        while (!validInput) {
            // while loop is broken if validInput flag becomes true
            System.out.println("Enter Record ID (6 digits, leave blank for default):");
            String input = scanner.nextLine();
            // user is prompted to input recordID
            if (input.isEmpty()) {
                RecordID = (int) (Math.random() * 999999) + 1;
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
    }

    public void setCustomerID() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
        boolean validInput = false;
        // validInput flag set to false
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
    }
    public void setLoanType() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
       boolean validInput = false;
        // validInput flag is set to false
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

    }
    public void setInterestRate() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
        boolean validInput = false;
        // validInput flag is set to false
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
    }
    public void setAmountRemaining() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
        boolean validInput = false;
        // validInput flag is set to false
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
    }
    public void setTimeLeft() {
        Scanner scanner = new Scanner(System.in);
        // Scanner object declared
        boolean validInput = false;
        //validInput flag is set to false
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
}


