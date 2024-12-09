package com.qa.stf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final Logger log = LogManager.getLogger(ExcelReader.class);

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelReader(String path) {
        this.path = path;
        try (FileInputStream fis = new FileInputStream(path)) { // Use try-with-resources
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0); // Default to the first sheet
        } catch (FileNotFoundException ex) {
            log.error("The excel file was not found at the given path: {}", path, ex);
            throw new ExceptionUtil.ConfigTypeException("File not found at path: " + path, ex);
        } catch (IOException ex) {
            log.error("Error occurred while loading the excel file from path: {}", path, ex);
            throw new ExceptionUtil.ConfigTypeException("Error occurred while loading excel file from path: " + path, ex);
        }
    }

    // Returns the row count in a sheet
    public int getRowCount(String sheetName) {
        try {
            XSSFSheet sheet = workbook.getSheet(sheetName); // Get sheet by name
            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return 0;
            }
            return sheet.getLastRowNum() + 1; // LastRowNum is zero-based
        } catch (Exception ex) {
            log.error("Error occurred while fetching row count for sheet '{}'", sheetName, ex);
            throw new ExceptionUtil.InvalidDataException(
                    String.format("Error fetching row count for sheet '%s'", sheetName), ex);
        }
    }

    // Returns the data from a cell
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0) {
                log.warn("Row number must be greater than 0. Provided: {}", rowNum);
                return "";
            }

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return "";
            }

            // Find the column index by column name
            XSSFRow headerRow = sheet.getRow(0);
            if (headerRow == null) {
                log.warn("Sheet '{}' is empty", sheetName);
                return "";
            }

            int colNum = findColumnIndex(headerRow, colName);
            if (colNum == -1) {
                log.warn("Column '{}' does not exist in sheet '{}'", colName, sheetName);
                return "";
            }

            // Retrieve cell data
            XSSFRow row = sheet.getRow(rowNum - 1);
            if (row == null) return "";

            XSSFCell cell = row.getCell(colNum);
            return getCellValueAsString(cell);

        } catch (Exception ex) {
            log.error("Error reading cell data for sheet '{}', column '{}', row {}", sheetName, colName, rowNum, ex);
            throw new ExceptionUtil.InvalidDataException(
                    String.format("Error reading cell data in sheet '%s', column '%s', row '%s'", sheetName, colName, rowNum), ex);
        }
    }

    // Helper to find the column index by name
    private int findColumnIndex(XSSFRow headerRow, String colName) {
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                return i;
            }
        }
        return -1; // Column not found
    }

    // Returns the data from a cell given the sheet name, column number, and row number
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0) {
                log.warn("Row number must be greater than 0. Provided: {}", rowNum);
                return "";
            }

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return "";
            }

            // Retrieve the specified row
            XSSFRow row = sheet.getRow(rowNum - 1); // Adjust for zero-based index
            if (row == null) return "";

            // Retrieve the specified cell
            XSSFCell cell = row.getCell(colNum);
            return getCellValueAsString(cell);

        } catch (Exception ex) {
            log.error("Error reading cell data for sheet '{}', column '{}', row {}", sheetName, colNum, rowNum, ex);
            throw new ExceptionUtil.InvalidDataException(
                    String.format("Error reading cell data in sheet '%s', column '%d', row '%d'", sheetName, colNum, rowNum), ex);
        }
    }

    // Helper to get cell value as a string
    private String getCellValueAsString(XSSFCell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield formatDateCell(cell.getNumericCellValue());
                }
                yield String.valueOf(cell.getNumericCellValue());
            }
            case FORMULA -> cell.getCellFormula();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    // Helper to format date cells
    private String formatDateCell(double numericValue) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.getJavaDate(numericValue));
        return String.format("%d/%d/%d",
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.YEAR));
    }

    // Updates the cell data in the specified sheet, column, and row
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        if (rowNum <= 0) {
            log.warn("Row number must be greater than 0. Provided: {}", rowNum);
            return false;
        }

        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return false;
            }

            // Determine the column index for the given column name
            int colNum = getColumnIndex(sheet, colName);
            if (colNum == -1) {
                log.warn("Column '{}' does not exist in sheet '{}'", colName, sheetName);
                return false;
            }

            // Auto-size column and get/create the row
            sheet.autoSizeColumn(colNum);
            XSSFRow row = sheet.getRow(rowNum - 1); // Adjust for zero-based index
            if (row == null) row = sheet.createRow(rowNum - 1);

            // Get/create the cell and set the value
            XSSFCell cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);
            cell.setCellValue(data);

            // Write changes back to the file
            workbook.write(fileOut);
            log.info("Data successfully updated in sheet '{}', column '{}', row {}", sheetName, colName, rowNum);

            return true;

        } catch (IOException ex) {
            log.error("Error occurred while updating the Excel sheet", ex);
            throw new ExceptionUtil.ExcelException("Error occurred while updating the Excel sheet", ex);
        }
    }

    // Updates the cell data in the specified sheet, column, and row with a hyperlink
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        if (rowNum <= 0) {
            log.warn("Row number must be greater than 0. Provided: {}", rowNum);
            return false;
        }

        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return false;
            }

            // Get the column index for the specified column name
            int colNum = getColumnIndex(sheet, colName);
            if (colNum == -1) {
                log.warn("Column '{}' does not exist in sheet '{}'", colName, sheetName);
                return false;
            }

            // Auto-size the column and get/create the row
            sheet.autoSizeColumn(colNum);
            XSSFRow row = sheet.getRow(rowNum - 1); // Adjust for zero-based index
            if (row == null) row = sheet.createRow(rowNum - 1);

            // Get/create the cell and set its value
            XSSFCell cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);
            cell.setCellValue(data);

            // Apply hyperlink if URL is provided
            if (url != null && !url.isEmpty()) {
                addHyperlink(cell, url);
            }

            // Write changes back to the file
            workbook.write(fileOut);
            log.info("Data successfully updated in sheet '{}', column '{}', row {}", sheetName, colName, rowNum);
            return true;

        } catch (IOException ex) {
            log.error("Error occurred while updating the Excel sheet", ex);
            throw new ExceptionUtil.ExcelException("Error occurred while updating the Excel sheet", ex);
        }
    }

    // Helper to get column index by column name
    private int getColumnIndex(XSSFSheet sheet, String colName) {
        XSSFRow headerRow = sheet.getRow(0);
        if (headerRow == null) {
            log.warn("Header row is missing in sheet '{}'", sheet.getSheetName());
            return -1;
        }

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            XSSFCell cell = headerRow.getCell(i);
            if (cell != null && cell.getCellType() == CellType.STRING &&
                    colName.equalsIgnoreCase(cell.getStringCellValue().trim())) {
                return i;
            }
        }
        return -1;
    }

    // Helper to add a hyperlink to a cell
    private void addHyperlink(XSSFCell cell, String url) {
        XSSFCreationHelper createHelper = workbook.getCreationHelper();
        XSSFHyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.FILE);
        hyperlink.setAddress(url);

        CellStyle linkStyle = workbook.createCellStyle();
        XSSFFont linkFont = workbook.createFont();
        linkFont.setUnderline(XSSFFont.U_SINGLE);
        linkFont.setColor(IndexedColors.BLUE.getIndex());
        linkStyle.setFont(linkFont);

        cell.setHyperlink(hyperlink);
        cell.setCellStyle(linkStyle);
    }

    // Adds a new sheet to the workbook and returns true if successful, else false
    public boolean addSheet(String sheetName) {
        if (sheetName == null || sheetName.trim().isEmpty()) {
            log.warn("Sheet name is null or empty. Cannot create sheet.");
            return false;
        }

        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            if (workbook.getSheet(sheetName) != null) {
                log.warn("Sheet '{}' already exists in the workbook.", sheetName);
                return false;
            }

            workbook.createSheet(sheetName);
            workbook.write(fileOut);
            log.info("Sheet '{}' successfully created and saved to workbook.", sheetName);
            return true;

        } catch (IOException ex) {
            log.error("Error occurred while adding a new sheet '{}' to the workbook.", sheetName, ex);
            throw new ExceptionUtil.ExcelException(
                    String.format("Error occurred while adding a new sheet '%s' to the workbook.", sheetName), ex);
        }
    }

    // Removes a sheet from the workbook and returns true if successful, else false
    public boolean removeSheet(String sheetName) {
        if (sheetName == null || sheetName.trim().isEmpty()) {
            log.warn("Sheet name is null or empty. Cannot remove sheet.");
            return false;
        }

        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            log.warn("Sheet '{}' does not exist in the workbook. Removal not possible.", sheetName);
            return false;
        }

        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.removeSheetAt(index);
            workbook.write(fileOut);
            log.info("Sheet '{}' successfully removed from the workbook.", sheetName);
            return true;

        } catch (IOException ex) {
            log.error("Error occurred while removing sheet '{}' from the workbook.", sheetName, ex);
            throw new ExceptionUtil.ExcelException(
                    String.format("Error occurred while removing sheet '%s' from the workbook.", sheetName), ex);
        }
    }

    // Adds a column to the specified sheet with the given column name
    public boolean addColumn(String sheetName, String colName) {
        if (sheetName == null || sheetName.trim().isEmpty()) {
            log.warn("Sheet name is null or empty. Cannot add column.");
            return false;
        }

        if (colName == null || colName.trim().isEmpty()) {
            log.warn("Column name is null or empty. Cannot add column.");
            return false;
        }

        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);

            if (index == -1) {
                log.warn("Sheet '{}' does not exist in the workbook. Column cannot be added.", sheetName);
                return false;
            }

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            if (row == null) {
                row = sheet.createRow(0);
            }

            // Determine the position for the new column
            int newColumnIndex = (row.getLastCellNum() == -1) ? 0 : row.getLastCellNum();
            cell = row.createCell(newColumnIndex);

            // Set the column name and style
            cell.setCellValue(colName);
            cell.setCellStyle(style);

            // Write changes to the file
            workbook.write(fileOut);
            log.info("Column '{}' successfully added to sheet '{}'.", colName, sheetName);

            return true;

        } catch (IOException ex) {
            log.error("Error occurred while adding column '{}' to sheet '{}'.", colName, sheetName, ex);
            throw new ExceptionUtil.ExcelException(
                    String.format("Error occurred while adding column '%s' to sheet '%s'.", colName, sheetName), ex);
        }
    }

    // Removes a column from the specified sheet by column index
    public boolean removeColumn(String sheetName, int colNum) {
        if (sheetName == null || sheetName.trim().isEmpty()) {
            log.warn("Sheet name is null or empty. Cannot remove column.");
            return false;
        }

        if (colNum < 0) {
            log.warn("Invalid column number: {}. Cannot remove column.", colNum);
            return false;
        }

        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            if (!isSheetExist(sheetName)) {
                log.warn("Sheet '{}' does not exist. Cannot remove column.", sheetName);
                return false;
            }

            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                log.warn("Sheet '{}' is empty. Cannot remove column.", sheetName);
                return false;
            }

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.NO_FILL);

            // Iterate through rows and remove the specified column
            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style); // Apply style before removing the cell
                        row.removeCell(cell); // Remove the cell from the row
                    }
                }
            }

            // Save the changes to the file
            workbook.write(fileOut);
            log.info("Column {} successfully removed from sheet '{}'.", colNum, sheetName);
            return true;

        } catch (IOException ex) {
            log.error("Error occurred while removing column {} from sheet '{}'.", colNum, sheetName, ex);
            throw new ExceptionUtil.ExcelException(
                    String.format("Error occurred while removing column %d from sheet '%s'.", colNum, sheetName), ex);
        }
    }


    // find whether sheets exists
    // Checks if the sheet exists in the workbook (case-insensitive search)
    public boolean isSheetExist(String sheetName) {
        if (sheetName == null || sheetName.trim().isEmpty()) {
            log.warn("Sheet name is null or empty. Cannot check for sheet existence.");
            return false;
        }

        try {
            int index = workbook.getSheetIndex(sheetName);

            // If sheet is not found by exact name, try case-insensitive search
            if (index == -1) {
                index = workbook.getSheetIndex(sheetName.toUpperCase());
                if (index == -1) {
                    log.warn("Sheet '{}' does not exist in the workbook.", sheetName);
                    throw new ExceptionUtil.ExcelException(String.format("Sheet '%s' does not exist in the excel workbook", sheetName));
                }
            }

            log.info("Sheet '{}' found in the workbook.", sheetName);
            return true;

        } catch (Exception ex) {
            log.error("Error occurred while checking if sheet '{}' exists in the workbook.", sheetName, ex);
            throw new ExceptionUtil.ExcelException(String.format("Error occurred while checking if sheet '%s' exists in the workbook", sheetName), ex);
        }
    }

    // Returns the number of columns in the specified sheet
    public int getColumnCount(String sheetName) {
        try {
            // Check if the sheet exists
            if (!isSheetExist(sheetName)) {
                log.warn("Sheet '{}' does not exist.", sheetName);
                return -1;
            }

            // Get the sheet and the first row
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);

            // If the row is null, return -1 indicating no columns
            if (row == null) {
                log.warn("First row is null in sheet '{}'. No columns available.", sheetName);
                return -1;
            }

            // Return the number of columns in the first row
            int columnCount = row.getLastCellNum();
            log.info("Sheet '{}' has {} columns.", sheetName, columnCount);
            return columnCount;

        } catch (Exception ex) {
            log.error("Error occurred while getting the column count for sheet '{}'.", sheetName, ex);
            throw new ExceptionUtil.ExcelException("Error occurred while getting the column count for the sheet", ex);
        }
    }

    // Adds a hyperlink in the specified sheet, associating it with a test case and screenshot column.
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        try {
            // Normalize the URL by replacing backslashes with forward slashes
            url = url.replace('\\', '/');

            // Check if the sheet exists; if not, log a warning and return false
            if (!isSheetExist(sheetName)) {
                log.warn("Sheet '{}' does not exist. Hyperlink cannot be added.", sheetName);
                return false;
            }

            // Get the sheet and iterate over its rows
            sheet = workbook.getSheet(sheetName);
            for (int i = 2; i <= getRowCount(sheetName); i++) {
                // Check if the test case name matches
                if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                    // Set the hyperlink data for the screenshot column
                    setCellData(sheetName, screenShotColName, i + index, message, url);
                    log.info("Hyperlink added for test case '{}' in sheet '{}'.", testCaseName, sheetName);
                    break;
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Error occurred while adding hyperlink for test case '{}' in sheet '{}'.", testCaseName, sheetName, ex);
            throw new ExceptionUtil.ExcelException("Error occurred while adding hyperlink for the test case", ex);
        }
    }

    // Returns the row number where the specified cell value is found in the given column.
    public int getCellRowNum(String sheetName, String colName, String cellValue) {
        try {
            // Check if the sheet exists
            if (!isSheetExist(sheetName)) {
                log.warn("Sheet '{}' does not exist. Cannot search for cell value '{}'.", sheetName, cellValue);
                return -1;
            }

            // Iterate through the rows of the sheet
            for (int i = 2; i <= getRowCount(sheetName); i++) {
                String currentCellValue = getCellData(sheetName, colName, i);
                if (currentCellValue != null && currentCellValue.equalsIgnoreCase(cellValue)) {
                    log.info("Cell value '{}' found at row {} in column '{}'.", cellValue, i, colName);
                    return i; // Return row number if match is found
                }
            }

            // Return -1 if the cell value was not found
            log.info("Cell value '{}' not found in column '{}' of sheet '{}'.", cellValue, colName, sheetName);
            return -1;
        } catch (Exception ex) {
            log.error("Error occurred while searching for cell value '{}' in column '{}' of sheet '{}'.", cellValue, colName, sheetName, ex);
            throw new ExceptionUtil.ExcelException("Error occurred while searching for cell value in the excel sheet", ex);
        }
    }

}

