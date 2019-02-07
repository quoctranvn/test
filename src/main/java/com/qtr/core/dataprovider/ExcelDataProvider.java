package com.qtr.core.dataprovider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelDataProvider {
    private XSSFWorkbook excelWBook;
    private XSSFSheet excelWSheet;
    private XSSFCell cell;
    private XSSFRow row;
    private int rowNumber;
    private int columnNumber;

    public void setRowNumber(int pRowNumber) { rowNumber = pRowNumber; }

    public int getRowNumber() { return rowNumber; }

    public void setColumnNumber(int pColumnNumber) { columnNumber = pColumnNumber; }

    public int getColumnNumber() { return columnNumber; }

    public Object[][] getTableArrayByRow(String testDataExcelFileName, String sheetName, int rowNumber) {
        try {
            // Open the Excel file
            InputStream ExcelFile = ExcelDataProvider.class.getClassLoader().getResourceAsStream(testDataExcelFileName);
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
            return tabArray;
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return null;
    }

    public void getExcelFileSheet(String testDataExcelFileName, String sheetName) {
        try {
            // Open the Excel file
            InputStream ExcelFile = ExcelDataProvider.class.getClassLoader().getResourceAsStream(testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            throw (e);
        }
    }

    public XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    public void setCellData(String testDataExcelFileName, int RowNum, int ColNum, String value) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            ClassLoader cLoader = ExcelDataProvider.class.getClassLoader();
            String filePath = new String(Files.readAllBytes(Paths.get(cLoader.getResource(testDataExcelFileName).toURI())));
            FileOutputStream fileOut = new FileOutputStream(filePath);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
