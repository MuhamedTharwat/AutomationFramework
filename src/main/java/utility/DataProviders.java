package utility;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "JumiaCredentials")
    public Object[][] getAllRegisterData() throws IOException {
        ExcelFileManger excelFile = new ExcelFileManger("src/test/java/testdata/JumiaTestData.xlsx");
        int rows = excelFile.getRowCount("Credentials");
        int cols = excelFile.getColumnCount("Credentials");
        int actRows = rows - 1;
        String[][] excelData = new String[actRows][cols];
        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < cols; j++) {
                excelData[i][j] = excelFile.getCellValue("Credentials", i + 1, j);
            }
        }
        return excelData;
    }

    @DataProvider(name = "JumiaLogin")
    public Object[][] getOnlyLoginData() throws IOException {
        ExcelFileManger excelFile = new ExcelFileManger("src/test/java/testdata/JumiaTestData.xlsx");
        int rows = excelFile.getRowCount("Credentials");
        int cols = excelFile.getColumnCount("Credentials");
        int actRows = rows - 1;
        int actCols = cols - 4;
        String[][] excelData = new String[actRows][actCols];
        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < actCols; j++) {
                excelData[i][j] = excelFile.getCellValue("Credentials", i + 1, j);
            }
        }
        return excelData;
    }

    @DataProvider(name = "AmazonLogin")
    public Object[][] getAmazonLoginData() throws IOException {
        ExcelFileManger excelFile = new ExcelFileManger("src/test/java/testdata/AmazonTestData.xlsx");
        int rows = excelFile.getRowCount("Credentials");
        int cols = excelFile.getColumnCount("Credentials");
        int actRows = rows - 1;
        int actCols = cols;
        String[][] excelData = new String[actRows][actCols];
        for (int i = 0; i < actRows; i++) {
            for (int j = 0; j < actCols; j++) {
                excelData[i][j] = excelFile.getCellValue("Credentials", i + 1, j);
            }
        }
        return excelData;
    }
}
