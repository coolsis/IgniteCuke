package com.ignite.step_defs;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelSamples {

    public static void main(String[] args) {

        String path = "test_resources/EIF-TestCases.xlsx";
        String sheetName = "users";

        Sheet data = getSheet(path,sheetName);

    }

    public static Sheet getSheet(String path, String sheetName){
        Workbook workbook = null;
        Sheet worksheet = null;
        try {
            FileInputStream excelFile = new FileInputStream(path);

            workbook = WorkbookFactory.create(excelFile);
            worksheet = workbook.getSheet(sheetName);

            Cell cell = worksheet.getRow(1).getCell(2);

            String value = cell.toString();


            System.out.println(value);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return worksheet;
    }
}
