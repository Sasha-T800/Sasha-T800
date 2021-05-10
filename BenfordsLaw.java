
/*
 * Date: May 6th 2021
 * Name: Sasha and Tisha
 * Teacher: Mr.Ho
 * Description: Part 2 of the methods assignment using Benford's Law and a Sales cvs file
*/
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class BenfordsLaw {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Data> salesDataArray = readCSVDataFromFile();
        if (salesDataArray.size() == 0)
            System.out.println("Have problem to load the sales file ");

        System.out.println("load data from sales csv " + salesDataArray.size());

    }

    
    /** 
     * @return ArrayList<Data>
     */
    private static ArrayList<Data> readCSVDataFromFile() {
        ArrayList<Data> salesDataArray = new ArrayList<Data>();

        String appPath = Paths.get("").toAbsolutePath().toString();
        
        String filePath = appPath + "\\sales.csv";
        // open file sales.csv
        File salesFile = new File(filePath);
        if (!salesFile.exists()) {
            return salesDataArray;
        }

        try {
            // make scanner for read from sales file
            Scanner readerFile = new Scanner(salesFile);

            while (readerFile.hasNext()) // returns a boolean value
            {
                String data = readerFile.next();
                //check if data is not empty
                if (!data.isEmpty()) {
                    // split data for 2 strings
                    String[] splitData = data.split(",");
                    //check if strings array is length 2 and second string is number 
                    if (splitData.length == 2 && isNumeric(splitData[1])) {
                        // create instance of Data and add to array
                        Data salesData = new Data(splitData[0], Long.parseLong(splitData[1]));
                        salesDataArray.add(salesData);
                    }
                }
            }

            readerFile.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return salesDataArray;
    }

    
    /** 
     * @param str
     * @return boolean
     * check strin if it is numeric
     */
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
