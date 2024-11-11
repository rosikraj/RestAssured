package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException { //getAllData is the method is defined with @Dataprovider annotation
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx"; //by using System.getProperty, this will give current project location
        XLUtility xl = new XLUtility(path); //here using XLUtility class , for that creating a object , add passing the path as part construct

        int rownum = xl.getRowCount("Sheet1"); //this two methods are used to count how many no.of rows and columns in the data
        int colcount = xl.getCellCount("Sheet1", 1);

        String apidata[][] = new String[rownum][colcount]; //we are creating a 2D array with the rows and columns based on sheet

        for (int i = 1; i < rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j); //this loop is responsible for storing the data into an array
            }
        }

        return apidata;
    }
    
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        // Getting the number of rows in the specified sheet
        int rownum = xl.getRowCount("Sheet1");

        // Creating an array to store usernames from the Excel sheet
        String[] apidata = new String[rownum];

        // Loop through the rows, retrieving usernames and storing them in the array
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }

        return apidata;
    }
    
}
