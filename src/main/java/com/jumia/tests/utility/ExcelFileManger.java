package com.jumia.tests.utility;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileManger {
    FileInputStream inputStream = null;
    XSSFWorkbook workbook = null ;
    XSSFSheet sheet = null;
    XSSFRow row = null;
    public ExcelFileManger(String path) {
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found plz Check the path");
            e.printStackTrace();
        }

    }
    public Object[][] getExcelFullData() throws IOException {
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        int rowCount= sheet.getLastRowNum()+1;
        int colCount= 6;

        String [][] excelData = new String[rowCount][colCount];
        for (int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                row = sheet.getRow(i);
                excelData[i][j]=row.getCell(j).toString();
            }
        }
        return excelData;

    }
    public Object[][] getExcelPartialData() throws IOException {
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        int rowCount= sheet.getLastRowNum()+1;
        int colCount= 2;

        String [][] excelData = new String[rowCount][colCount];
        for (int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                row = sheet.getRow(i);
                excelData[i][j]=row.getCell(j).toString();
            }
        }
        return excelData;

    }


}
