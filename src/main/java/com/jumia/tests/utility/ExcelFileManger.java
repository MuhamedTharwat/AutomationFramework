package com.jumia.tests.utility;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileManger {
    FileInputStream inputStream = null;
    XSSFWorkbook workbook = null;
    XSSFSheet sheet = null;
    XSSFRow row = null;

    public ExcelFileManger(String path) {
        try {
            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error with File");
            e.printStackTrace();
        }


    }
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        sheet = workbook.getSheetAt(index);
        int rowCount = sheet.getLastRowNum() + 1;
        return rowCount;
    }

    public int getColumnCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        sheet = workbook.getSheetAt(index);
        int columnCount = sheet.getRow(0).getLastCellNum();
        return columnCount;
    }

    public String getCellValue(String sheetName, int rowNum, int colNum) {
        int index = workbook.getSheetIndex(sheetName);
        sheet = workbook.getSheetAt(index);
        row = sheet.getRow(rowNum);
        String cellValue = row.getCell(colNum).toString();
        return cellValue;
    }


}
