
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
        Scanner reader = new Scanner(System.in);

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
        ;
        String filePath = appPath + "\\sales.csv";
        File salesFile = new File(filePath);
        if (!salesFile.exists()) {
            return salesDataArray;
        }

        try {
            Scanner readerFile = new Scanner(salesFile);

            // readerFile.useDelimiter(",");
            while (readerFile.hasNext()) // returns a boolean value
            {
                String data = readerFile.next();
                if (!data.isEmpty()) {
                    String[] splitData = data.split(",");
                    if (splitData.length == 2 && isNumeric(splitData[1])) {
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
