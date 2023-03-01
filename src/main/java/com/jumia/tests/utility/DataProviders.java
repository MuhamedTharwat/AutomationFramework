package com.jumia.tests.utility;

import java.io.IOException;
import org.testng.annotations.DataProvider;
public class DataProviders {

    @DataProvider(name = "NewUserData")
    public Object[][] fullUserData() throws IOException {
        ExcelFileManger excelFile = new ExcelFileManger("src/test/java/testdata/JumiaTestData.xlsx");
        return excelFile.getExcelFullData();
    }
    @DataProvider(name = "OldUserData")
    public Object[][] userData() throws IOException {
        ExcelFileManger excelFile = new ExcelFileManger("src/test/java/testdata/JumiaTestData.xlsx");
        return excelFile.getExcelPartialData();
    }
}
