/* Author: Sasha Tkachenko
/* Class Customer
/* collect customer information in the object
*/

import java.util.Random;

public class Customer {
    private int[] customerNumber;
    private String lastName;
    private String fistName;
    private String cityName;
    private char[] postalCode;
    private int[] creditCard;
    
    public Customer(){
        createCustomerNumber();
    }

    private void createCustomerNumber() {
        Random random = new Random();
        customerNumber = new int[6] ;
        customerNumber[0] = 1;
        for (int i = 1 ; i < customerNumber.length; i++) {
            customerNumber[i] = random.nextInt(9) + 1;
        }         
    }

    public String getCreditCardasString() {
        String card = "";
        for (int c : creditCard) {
            card = card + Integer.toString(c);
        }
        return card;
    }
    public void setCreditCard(int[] card) {
        this.creditCard = new int[card.length];
        this.creditCard = card;
    }
    public void setPostalCode(String code) {
        this.postalCode = new char[code.length()];
        for( int i=0;i<code.length();i++){
            this.postalCode[i] = code.charAt(i);
        }
    }
    public String getPostalCode(){
        return new String(postalCode);
    }
    public String getCustomerNumberAsString(){
        String customerId = "";
        for (int c : customerNumber) {
            customerId = customerId + Integer.toString(c);
        }
        return customerId;
    }

    public String toCSV() {
        String csvDelimeter = ",";
        return getCustomerNumberAsString() + csvDelimeter + fistName + csvDelimeter + lastName
               + csvDelimeter + cityName + csvDelimeter + getPostalCode() + csvDelimeter 
               + getCreditCardasString();
    }

    public void setFirstName(String fName) {
        fistName = fName;
    }

    public void setLastName(String lname) {
        lastName = lname;
    }

    public void setCity(String city) {
        cityName = city;
    }    
}
