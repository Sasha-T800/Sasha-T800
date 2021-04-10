import java.util.Random;

public class Customer {
    private int[] customerNumber;
    private String lastName;
    private String fistName;
    private String cityName;
    private char[] postalCode;
    private char[] creditCard;
    
    public Customer(String fName, String lName, String city,String code,String card){
        lastName = lName;
        fistName = fName;
        cityName = city;
        setPostalCode(code);
        setCreditCard(card);
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

    public String getCreditCard() {
        return new String(creditCard);
    }
    public void setCreditCard(String card) {
        this.creditCard = new char[card.length()];
        for( int i=0;i<card.length();i++){
            this.creditCard[i] = card.charAt(i);
        }
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
               + getCreditCard();
    }    
}
