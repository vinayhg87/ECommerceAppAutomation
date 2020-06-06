package main.java.store.ExcelOps;

import main.java.store.caller.Commons;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelOperations implements Commons {

    public String ReadExcel(String sheetName, int rowNum, int colNum) {

        String result = null;
        try {
            Workbook wb = WorkbookFactory.create(new FileInputStream(ExcelFilePath));
            result = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
        } catch (NullPointerException e) {
            result = " ";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void ReadWrite(String sheetName, int rowNum, int colNum, String data) {

        try {
            Workbook wb = WorkbookFactory.create(new FileInputStream(ExcelFilePath));
            wb.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(data);
            FileOutputStream fileWrite = new FileOutputStream(ExcelFilePath);
            wb.write(fileWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int RowCount(String SheetName) throws IOException, InvalidFormatException {

        Workbook wb = WorkbookFactory.create(new FileInputStream(ExcelFilePath));
        return wb.getSheet(SheetName).getLastRowNum();
    }
}
