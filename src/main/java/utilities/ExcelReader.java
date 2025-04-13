package utilities;

import helpers.LogHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import static helpers.PathHelper.projectPath;

public class ExcelReader {
    public static final String FILE_PATH = projectPath + "data" + File.separator + "DataTestOnLiveTv.xlsx";
    public static Logger logger = LogHelper.getLogger();
    private Workbook workbook;
    private Sheet sh;
    public void setSheet(Sheet sh) {
        this.sh = sh;
    }
    public int getRowCount() {
        return this.sh.getLastRowNum();
    }
    public int getColCount() {
        return this.sh.getRow(0).getLastCellNum();
    }
    public Object[][] getExcelData(){
        Object[][] obj = new Object[getRowCount()][1];
        try {
            for (int i = 1; i <= getRowCount(); i++) {
                HashMap<String, String> testData = getDataFromExcel(i);
                obj[i - 1][0] = testData;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
    public Workbook initWorkbook() {
        logger.info("Read excel file"   +  FILE_PATH);
        try {
            FileInputStream file = new FileInputStream(FILE_PATH);
            this.workbook = new XSSFWorkbook(file);
            return this.workbook;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Sheet readSheet(String sheetName){
        logger.info("Read sheet excel " + sheetName );
        this.sh = workbook.getSheet(sheetName);
        return this.sh;
    }
    public HashMap<String, String> getDataFromExcel(int rowNum) {
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < this.sh.getRow(0).getLastCellNum(); i++) {
            String value;
            if(this.sh.getRow(rowNum).getCell(i) != null) {
                this.sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
                value = this.sh.getRow(rowNum).getCell(i).toString();
            }
            else {
                value = "";
            }
            hm.put(this.sh.getRow(0).getCell(i).toString(), value);
        }
        Set<String> set = hm.keySet();
        for (String key : set) {
            System.out.println("key " + key + " value " + hm.get(key));
        }
        return hm;
    }
    public int getIndexRow(String key){
        int index = 0;
        System.out.println("Number rows: " + this.sh.getPhysicalNumberOfRows());
        for (int i = 0; i < this.sh.getPhysicalNumberOfRows(); i++) {
            System.out.println("Value colum " + this.sh.getRow(i).getCell(0).getStringCellValue());
            if(this.sh.getRow(i).getCell(0).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index row: " + index);
        return index;
    }
    public int getIndexCell(String key){
        int index = 0;
        System.out.println("Number colum: " + this.sh.getRow(0).getPhysicalNumberOfCells());
        for (int i = 0; i < this.sh.getRow(0).getPhysicalNumberOfCells(); i++) {
            if(this.sh.getRow(0).getCell(i).getStringCellValue().equals(key)){
                index = i;
                break;
            }
        }
        logger.info("Index cell: " + index);
        return index;
    }
    public void setCell(String value, int rowNumber, int cellNumber) {
        // Logging
        logger.info("Set cell: " + value + " On row: " + rowNumber + " On cell: " + cellNumber);
        try {
            // Get the row, create if null
            Row row = this.sh.getRow(rowNumber);
            if (row == null) {
                row = this.sh.createRow(rowNumber);
            }
            // Get the cell, create if null
            Cell cell = row.getCell(cellNumber);
            if (cell == null) {
                cell = row.createCell(cellNumber);
            }
            // Set the cell value
            cell.setCellValue(value);
            // Save changes to the file
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            this.workbook.write(fileOut);
            fileOut.close(); // Always close the stream to avoid file corruption
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFileXls(String id, String file) throws IOException {
        File F = new File(file);
        FileInputStream fis = new FileInputStream(F);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Object[][] bookData = {{id}};
        int rowCount = 0;
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }
    }
}
