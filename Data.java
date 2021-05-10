/*
 * Date: May 6th 2021
 * Name: Sasha and Tisha
 * Teacher: Mr.Ho
 * Description: class Data for incapsulate postal and sales value
*/

public class Data {
    private String postalCode;
    private long sales;
    public Data(String postal , long sale ){
        postalCode = postal;
        sales = sale;
    }

    
    /** 
     * @return String
     * get postal code
     */
    public String getPostalCode(){
        return postalCode;
    }
    
    /** 
     * @return long
     * get sales value
     */
    public long getSalesValue(){
        return sales;
    }
    
    /** 
     * @return String
     * class values to Sting 
     */
    public String toString(){
        return postalCode + "," +  Long.toString(sales);
    }
}
