// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
// More packages may be imported in the space below

import javax.swing.JFileChooser;

class CustomerSystem {
    static String fileExtention = ".csv";
    static Scanner reader;
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        // Please do not edit any of these variables
        reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        Customer customer = new Customer();
        customer.setFirstName("Sasha");
        customer.setLastName("Tkachenko");
        customer.setPostalCode("L5J");
        customer.setCity("Woodbridge");

        do {
            printMenu(); // Printing out the main menu
            userInput = reader.nextLine(); // User selection from the menu

            if (userInput.equals(enterCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you
                // design the method return
                // Any necessary variables may be added to this if section, but nowhere else in
                // the code
                enterCustomerInfo(customer);
            } else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you
                // design the method return
                generateCustomerDataFile(customer);
            } else {
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition)); // Exits once the user types

        reader.close();
        System.out.println("Program Terminated");
    }

    public static void printMenu() {
        System.out.println("Customer and Sales System\n".concat("1. Enter Customer Information\n")
                .concat("2. Generate Customer data file\n").concat("3. Report on total Sales (Not done in this part)\n")
                .concat("4. Check for fraud in sales data (Not done in this part)\n").concat("9. Quit\n")
                .concat("Enter menu option (1-9)\n"));
    }

    
    /**
     * This method may be edited to achieve the task however you like.
     * 
     * The method may not nesessarily be a void return type This method 
     * may also be broken down further depending on your algorithm 
     * 
     * @param customer class Customer consists information 
     */
    public static void enterCustomerInfo(Customer customer) {

        // user suppose to enter first name , last name and postal code here ;



        //get credit card from user input 
        int[] cardEntered = getCustomerCreditCard();

        // validate card
        if (!validateCreditCard(cardEntered)) {
            System.out.println("The credit card is not valid . Try again");
            return;
        }

        System.out.println("The credit card IS GOOOD");
        customer.setCreditCard(cardEntered);
    }
    
    /** 
     * This method uses reader for get credit card from input
     * 
     * @return int[] credit card digits
     */
    public static int[] getCustomerCreditCard() {
        int[] creditCardNumber;
        String tempCard = "";
        do {
            System.out.println("Please enter credit card digits");
            tempCard = reader.nextLine();

            // check if customer enter space or nothing
            if (tempCard.trim().length() == 0) {
                System.out.println("Credit card can not be empty");
            } 
            // check if customer enter 9 or more digits
            if( tempCard.trim().length() < 9){
                System.out.println("Credit card must have at least 9 digits");
                tempCard = "";
            }
            // check if customer enter ONLY Digits
            if( !consistOnlyDigits(tempCard.trim())){
                System.out.println("Please enter only digits . NO letters or symbols");
                tempCard = "";
            }           

        } while (tempCard.trim().length() == 0);

        tempCard = tempCard.trim(); // no spaces
        creditCardNumber = new int[tempCard.length()];
        for (int i = 0; i < tempCard.length(); i++) {
            creditCardNumber[i] = Character.getNumericValue(tempCard.charAt(i));
        }
        return creditCardNumber;
    }
    
    /** 
     * This method check that customer input only numbers
     * 
     * @param card String user input from keyboard
     * @return boolean consists only numbers or not
     */

    public static boolean consistOnlyDigits(String card){
        // check them only digit
        for (int i = 0; i < card.length(); i++) {
            if (!Character.isDigit(card.charAt(i))) {
                return false;
            }
        }   
        return true;  
    }

    /*
     * This method may be edited to achieve the task however you like. The method
     * may not nesessarily be a void return type This method may also be broken down
     * further depending on your algorithm
     */
    public static void validatePostalCode() {
    }

    
    /** 
     * This method may be edited to achieve the task however you like. 
     * 
     * The method may not nesessarily be a void return type This method may also be broken down
     * further depending on your algorithm
     * 
     * @param creditCard array of digits present credit card
     * @return boolean if credit card valid or not
     */
    public static boolean validateCreditCard(int[] creditCard) {
        if (creditCard == null) {
            return false;
        }

        if (creditCard.length == 0) {
            return false;
        }

        CreditCardValidator validator = new CreditCardValidator();
        return validator.isValid(creditCard);
    }

    
    /** 
     * This method may be edited to achieve the task however you like.
     * 
     * The method may not nesessarily be a void return type This method may also be broken down
     * further depending on your algorithm
     * 
     * 
     * @param customer Class Customer consists information for saving
     */

    public static void generateCustomerDataFile(Customer customer) {
        String location = getSaveFileLocation();
        String filename = enterFileNameForDataFile(location);
        String filePathName = location + "\\" + filename + fileExtention;
        try {
            // save customer information in define location and file name
            if (saveCustomerToFile(customer, filePathName)) {
                System.out.println("Please check customer information in the file " + filePathName);
                System.out.println("");
            }
        } catch (Exception exception) {
            System.out.println("Something goes wrong. Try again. " + exception.getMessage());
        }
    }   
    /** 
     *  This method save the customer in specified location and file name
     * 
     * @param customer Customer class that consist customer information
     * @param filePathName Sting path to directory and file name
     * @return boolean success save information or not
     */

    private static boolean saveCustomerToFile(Customer customer, String filePathName) {
        // create file
        File fileOut = new File(filePathName);
        try {
            // java utils allows write to file
            PrintWriter out = new PrintWriter(fileOut);
            // call customer method for csv format
            String csvLine = customer.toCSV();
            out.write(csvLine);
            out.close();
            return true; // return true if successed
        } catch (FileNotFoundException e) {
            System.out.println("Something goes wrong. Try again. " + e.getMessage());
        }
        return false;
    }

    
    /** 
     * This method return location from chosen dialog .Does not allow cancel .always
     * suppose to chose location
     * 
     * @return String path to directory where save file
     */

    private static String getSaveFileLocation() {
        File selectedFile = null;
        do {
            // java utils helps to chose location on disk
            JFileChooser locationChooser = new JFileChooser();
            locationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (locationChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                selectedFile = locationChooser.getSelectedFile();
        } while (selectedFile == null);
        return selectedFile.getAbsolutePath(); // return path to the directory
    }

    /*******************************************************************
     * ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY *
     *******************************************************************/
    /*
     * This method allows to enter file name where save customer information
     *  
     * @param savedDirectory String - path to directory where save file
     * @return String - file name entered by customer
     */
    private static String enterFileNameForDataFile(String savedDirectory) {
        String fileName = "";
        do {
            System.out.println("Please enter file name for storing the information");
            fileName = reader.nextLine();

            // validate if file name is not empty
            if (fileName.trim().length() == 0) {
                System.out.println("The file name can not be empty");
            }
            // validate if File exist and customer allow to override

            if (fileName.trim().length() > 0) {
                String filePath = savedDirectory + "\\" + fileName + fileExtention;
                File file = new File(filePath);
                if (!file.exists()) {
                    break; // we can write to file
                }

                // ask customer about override the file
                if (!canOverrideFile()) {
                    fileName = "";
                }
            }
        } while (fileName.trim().length() == 0);

        return fileName;
    }

    
    /** 
     * This method asks customer if file can be override . the method does not exit
     * if user does not give right answer. y for yes and n for no
     *  
     * @return boolean - allow override or not
     */
    public static boolean canOverrideFile() {
        String answer = "";
        do {
            System.out.println("File already exists, ok to overwrite (y/n)? ");
            answer = reader.nextLine();
            // validate answer from customer
            if (answer.trim().length() == 0) {
                System.out.println("Please answer y or n ");
            } else if (!answer.startsWith("y") && !answer.startsWith("n")) {
                System.out.println("Please answer y or n ");
                answer = "";
            }
        } while (answer.trim().length() == 0);

        return answer.startsWith("y");
    }
}