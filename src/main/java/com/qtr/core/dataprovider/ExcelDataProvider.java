package com.qtr.core.dataprovider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDataProvider {
    private static final String currentDir = System.getProperty("user.dir");
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    private static int rowNumber;
    private static int columnNumber;
    private static final String defaultTestDataPath = "\\src\\test\\resources\\";
    private static String testDataExcelPath = currentDir + defaultTestDataPath;

    public static void setRowNumber(int pRowNumber) { rowNumber = pRowNumber; }

    public static int getRowNumber() { return rowNumber; }

    public static void setColumnNumber(int pColumnNumber) { columnNumber = pColumnNumber; }

    public static int getColumnNumber() { return columnNumber; }

    public static Object[][] getTableArrayByRow(String testDataExcelFileName, String sheetName, int rowNumber) {
        try {
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int totalCols = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            String[][] tabArray = new String[1][totalCols];
            int ci = 0;
            int cj = 0;
            for (int i = 0; i < totalCols; i++, cj++) {
                tabArray[ci][cj] = getCellData(rowNumber -1, i);
                System.out.println(tabArray[ci][cj]);
            }
            return(tabArray);
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return null;
    }

    public static void getExcelFileSheet(String testDataExcelFileName, String sheetName) {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            throw (e);
        }
    }

    public static XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static void setCellData(String testDataExcelFileName, int RowNum, int ColNum, String value) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
