
/*
 * Date: May 6th 2021
 * Name: Sasha and Tisha
 * Teacher: Mr.Ho
 * Description: Part 2 of the methods assignment using Benford's Law and a Sales cvs file
*/
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

class BenfordsLaw extends JFrame {

    private ArrayList<Data> salesDataArray = new ArrayList<Data>();

    public BenfordsLaw() {

        // read data from the sales.csv
        try {
            initDataFromCSsvFile();
            initUI();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    private void initUI() {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);

        add(chartPanel);

        pack();
        setTitle("Figure 1");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() {

        var dataset = new DefaultCategoryDataset();
        dataset.setValue(30.1, "Frequency", "1");
        dataset.setValue(17.6, "Frequency", "2");
        dataset.setValue(12.5, "Frequency", "3");
        dataset.setValue(9.7, "Frequency", "4");
        dataset.setValue(7.9, "Frequency", "5");
        dataset.setValue(6.7, "Frequency", "6");
        dataset.setValue(5.8, "Frequency", "7");
        dataset.setValue(5.1, "Frequency", "8");
        dataset.setValue(4.6, "Frequency", "9");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart("Benfords Law Distribution Leading Digit", "Digits",
                "Frequency", dataset, PlotOrientation.VERTICAL, false, true, false);

        return barChart;
    }

    /**
     * @return ArrayList<Data>
     * @throws FileNotFoundException
     */
    private void initDataFromCSsvFile() throws FileNotFoundException {
        this.salesDataArray = new ArrayList<Data>();

        String appPath = Paths.get("").toAbsolutePath().toString();

        String filePath = appPath + "\\sales.csv";
        // open file sales.csv
        File salesFile = new File(filePath);
        if (!salesFile.exists()) {
            throw new FileNotFoundException("The sales file does not find.");
        }

        // make scanner for read from sales file
        Scanner readerFile = new Scanner(salesFile);

        while (readerFile.hasNext()) // returns a boolean value
        {
            String data = readerFile.next();
            // check if data is not empty
            if (!data.isEmpty()) {
                // split data for 2 strings
                String[] splitData = data.split(",");
                // check if strings array is length 2 and second string is number
                if (splitData.length == 2 && isNumeric(splitData[1])) {
                    // create instance of Data and add to array
                    Data salesData = new Data(splitData[0], Long.parseLong(splitData[1]));
                    salesDataArray.add(salesData);
                }
            }
        }

        readerFile.close();

    }

    /**
     * @param str
     * @return boolean check strin if it is numeric
     */
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new BenfordsLaw();
            ex.setVisible(true);
        });
    }
}
