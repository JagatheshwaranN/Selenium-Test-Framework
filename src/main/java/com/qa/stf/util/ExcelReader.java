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

/**
 * The ExcelReader class provides utility methods for reading data from Excel files
 * and converting it into a format that can be easily used in Java applications.
 *
 * <p>Features:
 * <ul>
 *     <li>Read data from an Excel file in `.xlsx` or `.xls` format.</li>
 *     <li>Retrieve data from specific rows, columns, or entire sheets.</li>
 *     <li>Support for custom cell types, including strings, numbers, and dates.</li>
 *     <li>Option to handle missing or null values gracefully.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions are thrown in case of invalid file paths, unsupported file
 *      formats, or errors while reading the data.</li>
 *   <li>Detailed logging is provided for successful data extraction and error
 *      scenarios.</li>
 * </ul>
 *
 * <p>Note:
 * The class assumes that the Apache POI library is included in the project for handling
 * Excel files. Users must ensure proper setup of the Apache POI dependencies and error
 * handling for file operations.
 *
 * <p>Example:
 * <pre>
 * {@code
 * ExcelReader excelReader = new ExcelReader("path/to/excel/file.xlsx");
 * List<String> data = excelReader.readColumn("Sheet1", 0); // Read first column from Sheet1
 * System.out.println(data);
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.2
 */
public class ExcelReader {

    // Logger instance for the ExcelReader class to enable logging during the execution
    private static final Logger log = LogManager.getLogger(ExcelReader.class);

    // Path to the Excel file
    public String path;

    // FileInputStream to read data from the Excel file
    public FileInputStream fis = null;

    // FileOutputStream to write data to the Excel file
    public FileOutputStream fileOut = null;

    // XSSFWorkbook to represent the Excel workbook in memory
    private XSSFWorkbook workbook = null;

    // XSSFSheet to represent a specific sheet in the Excel workbook
    private XSSFSheet sheet = null;

    // XSSFRow to represent a row within a specific sheet in the workbook
    private XSSFRow row = null;

    // XSSFCell to represent a cell within a specific row in the sheet
    private XSSFCell cell = null;

    /**
     * Initializes an instance of ExcelReader to read data from an Excel file.
     * <p>
     * This constructor loads the specified Excel file, defaults to the first sheet,
     * and initializes the workbook and sheet objects. It uses try-with-resources
     * to ensure the file input stream is properly closed.
     * </p>
     *
     * @param path The file path of the Excel workbook to load.
     * @throws ExceptionHub.ConfigTypeException If the file is not found or cannot
     *                                           be loaded.
     */
    public ExcelReader(String path) {
        this.path = path;
        try (FileInputStream fis = new FileInputStream(path)) { // Use try-with-resources
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0); // Default to the first sheet
        } catch (FileNotFoundException ex) {
            log.error("The excel file was not found at the given path: {}", path, ex);
            throw new ExceptionHub.ConfigTypeException("File not found at path: " + path, ex);
        } catch (IOException ex) {
            log.error("Error occurred while loading the excel file from path: {}", path, ex);
            throw new ExceptionHub.ConfigTypeException("Error occurred while loading excel file from path: " + path, ex);
        }
    }

    /**
     * Retrieves the row count in a specified sheet.
     * <p>
     * This method fetches the total number of rows in the given sheet of the workbook.
     * If the sheet does not exist, it logs a warning and returns 0. The row count includes
     * all rows, even if some are empty.
     * </p>
     *
     * @param sheetName The name of the sheet for which to retrieve the row count.
     * @return The number of rows in the sheet (1-based index).
     * @throws ExceptionHub.InvalidDataException If an error occurs while accessing the sheet.
     */
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
            throw new ExceptionHub.InvalidDataException(
                    String.format("Error fetching row count for sheet '%s'", sheetName), ex);
        }
    }

    /**
     * Retrieves data from a specific cell in the sheet by column name and row number.
     * <p>
     * This method finds the column index based on the column name and retrieves the
     * corresponding cell value in the specified row. If the row number is invalid, the
     * column or sheet does not exist, or any other issue occurs, appropriate warnings
     * are logged and an empty string is returned.
     * </p>
     *
     * @param sheetName The name of the sheet containing the cell.
     * @param colName   The name of the column containing the cell.
     * @param rowNum    The 1-based row number containing the cell.
     * @return The cell data as a string, or an empty string if the cell is empty or not found.
     * @throws ExceptionHub.InvalidDataException If an error occurs while retrieving the cell
     *                                            data.
     */
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
            throw new ExceptionHub.InvalidDataException(
                    String.format("Error reading cell data in sheet '%s', column '%s', row '%s'", sheetName, colName, rowNum), ex);
        }
    }

    /**
     * Finds the index of a column in the header row by column name.
     * <p>
     * This method iterates over the cells in the header row and checks if any cell
     * matches the specified column name (case-insensitive). If no match is found,
     * it returns -1.
     * </p>
     *
     * @param headerRow The header row to search.
     * @param colName   The name of the column to locate.
     * @return The zero-based index of the column, or -1 if the column is not found.
     */
    private int findColumnIndex(XSSFRow headerRow, String colName) {
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                return i;
            }
        }
        return -1; // Column not found
    }

    /**
     * Retrieves data from a specific cell in the sheet by column index and row number.
     * <p>
     * This method retrieves the cell value based on the specified zero-based column index
     * and 1-based row number. If the row number is invalid, the column or sheet does not
     * exist, or any other issue occurs, appropriate warnings are logged and an empty string
     * is returned.
     * </p>
     *
     * @param sheetName The name of the sheet containing the cell.
     * @param colNum    The zero-based column index containing the cell.
     * @param rowNum    The 1-based row number containing the cell.
     * @return The cell data as a string, or an empty string if the cell is empty or not found.
     * @throws ExceptionHub.InvalidDataException If an error occurs while retrieving the cell
     *                                            data.
     */
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
            throw new ExceptionHub.InvalidDataException(
                    String.format("Error reading cell data in sheet '%s', column '%d', row '%d'", sheetName, colNum, rowNum), ex);
        }
    }

    /**
     * Converts a cell value to a string representation.
     * <p>
     * This method determines the type of the cell and converts its value to a string
     * accordingly. It handles different cell types such as STRING, NUMERIC, FORMULA,
     * BOOLEAN, and defaults to an empty string for unsupported or null cells.
     * If the cell contains a date, it is formatted as a string using the helper method
     * {@link #formatDateCell(double)}.
     * </p>
     *
     * @param cell The {@link XSSFCell} object representing the cell.
     * @return A string representation of the cell value, or an empty string if the cell
     * is null or unsupported.
     */
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

    /**
     * Formats a numeric value representing a date into a string representation.
     * <p>
     * This method converts a numeric value obtained from a date-formatted cell
     * into a Java {@link Calendar} object and formats it as a string in the
     * format "DD/MM/YYYY".
     * </p>
     *
     * @param numericValue The numeric value representing the date.
     * @return A string representation of the date in "DD/MM/YYYY" format.
     */
    private String formatDateCell(double numericValue) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.getJavaDate(numericValue));
        return String.format("%d/%d/%d",
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.YEAR));
    }

    /**
     * Updates the cell data in the specified sheet, column, and row.
     * <p>
     * This method updates the value of a cell identified by its sheet name,
     * column name, and row number in the Excel workbook. If the row or cell
     * does not exist, it creates them. After updating, the changes are written
     * back to the file.
     * </p>
     *
     * @param sheetName The name of the sheet where the cell resides.
     * @param colName   The name of the column where the cell resides.
     * @param rowNum    The row number (1-based index) where the cell resides.
     * @param data      The data to set in the cell.
     * @return {@code true} if the cell data was successfully updated, {@code false}
     * otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while updating the
     *                                      Excel sheet.
     */
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
            throw new ExceptionHub.ExcelException("Error occurred while updating the Excel sheet", ex);
        }
    }

    /**
     * Updates the cell data in the specified sheet, column, and row with a hyperlink.
     * <p>
     * This method updates the value of a cell identified by its sheet name,
     * column name, and row number in the Excel workbook. If the row or cell
     * does not exist, it creates them. A hyperlink is added to the cell if a URL
     * is provided. After updating, the changes are written back to the file.
     * </p>
     *
     * @param sheetName The name of the sheet where the cell resides.
     * @param colName   The name of the column where the cell resides.
     * @param rowNum    The row number (1-based index) where the cell resides.
     * @param data      The data to set in the cell.
     * @param url       The URL to hyperlink the cell, or {@code null} if no hyperlink
     *                  is required.
     * @throws ExceptionHub.ExcelException If an error occurs while updating the
     *                                      Excel sheet.
     */
    public void setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        if (rowNum <= 0) {
            log.warn("Row number must be greater than 0. Provided: {}", rowNum);
            return;
        }

        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                log.warn("Sheet '{}' does not exist in the workbook", sheetName);
                return;
            }

            // Get the column index for the specified column name
            int colNum = getColumnIndex(sheet, colName);
            if (colNum == -1) {
                log.warn("Column '{}' does not exist in sheet '{}'", colName, sheetName);
                return;
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

        } catch (IOException ex) {
            log.error("Error occurred while updating the Excel sheet", ex);
            throw new ExceptionHub.ExcelException("Error occurred while updating the Excel sheet", ex);
        }
    }

    /**
     * Retrieves the index of a column by its name in the specified sheet.
     * <p>
     * This method searches the header row (first row) of the sheet for a column
     * with the specified name and returns its zero-based index. If the column
     * is not found, it returns {@code -1}.
     * </p>
     *
     * @param sheet   The sheet where the column is to be searched.
     * @param colName The name of the column to find.
     * @return The zero-based index of the column, or {@code -1} if not found.
     */
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

    /**
     * Adds a hyperlink to the specified cell.
     * <p>
     * This method sets a hyperlink pointing to the provided URL for the specified
     * cell. It also applies a hyperlink style (blue and underlined text) to the
     * cell.
     * </p>
     *
     * @param cell The cell to which the hyperlink will be added.
     * @param url  The URL to set as the hyperlink.
     */
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

    /**
     * Adds a new sheet to the workbook.
     * <p>
     * This method creates a new sheet with the specified name in the Excel workbook.
     * If a sheet with the given name already exists, it logs a warning and returns
     * {@code false}. The method writes the updated workbook back to the file after
     * successfully adding the sheet.
     * </p>
     *
     * @param sheetName The name of the new sheet to be created.
     * @return {@code true} if the sheet was successfully added, {@code false} otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while adding the sheet or
     *                                      saving the workbook.
     */
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
            throw new ExceptionHub.ExcelException(
                    String.format("Error occurred while adding a new sheet '%s' to the workbook.", sheetName), ex);
        }
    }

    /**
     * Removes a sheet from the workbook.
     * <p>
     * This method removes a sheet with the specified name from the Excel workbook.
     * If the sheet does not exist or the name is invalid, it logs a warning and
     * returns {@code false}. The updated workbook is saved back to the file after
     * removing the sheet.
     * </p>
     *
     * @param sheetName The name of the sheet to remove.
     * @return {@code true} if the sheet was successfully removed, {@code false}
     * otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while removing the
     *                                      sheet or saving the workbook.
     */
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
            throw new ExceptionHub.ExcelException(
                    String.format("Error occurred while removing sheet '%s' from the workbook.", sheetName), ex);
        }
    }

    /**
     * Adds a new column to the specified sheet.
     * <p>
     * This method adds a new column with the given name to the header row of the
     * specified sheet.
     * If the sheet does not exist or the column name is invalid, it logs a warning
     * and returns {@code false}. The updated workbook is saved back to the file after
     * adding the column. The new column header cell is styled with a grey background.
     * </p>
     *
     * @param sheetName The name of the sheet where the column is to be added.
     * @param colName   The name of the new column to add.
     * @return {@code true} if the column was successfully added, {@code false} otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while adding the column or
     *                                      saving the workbook.
     */
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
            throw new ExceptionHub.ExcelException(
                    String.format("Error occurred while adding column '%s' to sheet '%s'.", colName, sheetName), ex);
        }
    }

    /**
     * Removes a column from the specified sheet by its column index.
     * <p>
     * This method removes all cells in the specified column from each row of the
     * given sheet. If the sheet or column index is invalid, it logs a warning and
     * returns {@code false}. The updated workbook is saved back to the file after
     * removing the column.
     * </p>
     *
     * @param sheetName The name of the sheet where the column is to be removed.
     * @param colNum    The index of the column to remove (0-based index).
     * @return {@code true} if the column was successfully removed, {@code false}
     * otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while removing the
     *                                      column or saving the workbook.
     */
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
            throw new ExceptionHub.ExcelException(
                    String.format("Error occurred while removing column %d from sheet '%s'.", colNum, sheetName), ex);
        }
    }


    /**
     * Checks if a sheet exists in the workbook (case-insensitive search).
     * <p>
     * This method verifies the existence of a sheet in the workbook by its name. It
     * performs both case-sensitive and case-insensitive searches. If the sheet does
     * not exist, it logs a warning and throws an exception. This method can also
     * handle empty or null sheet names.
     * </p>
     *
     * @param sheetName The name of the sheet to check.
     * @return {@code true} if the sheet exists in the workbook, {@code false} otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs during the sheet existence
     *                                      check.
     */
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
                    throw new ExceptionHub.ExcelException(String.format("Sheet '%s' does not exist in the excel workbook", sheetName));
                }
            }

            log.info("Sheet '{}' found in the workbook.", sheetName);
            return true;

        } catch (Exception ex) {
            log.error("Error occurred while checking if sheet '{}' exists in the workbook.", sheetName, ex);
            throw new ExceptionHub.ExcelException(String.format("Error occurred while checking if sheet '%s' exists in the workbook", sheetName), ex);
        }
    }

    /**
     * Retrieves the number of columns in the specified sheet.
     * <p>
     * This method checks if the sheet exists and retrieves the count of columns in
     * the first row.
     * If the first row is empty or the sheet does not exist, it returns -1.
     * </p>
     *
     * @param sheetName The name of the sheet to get the column count from.
     * @return The number of columns in the first row of the sheet, or -1 if the sheet
     * or row is empty.
     * @throws ExceptionHub.ExcelException If an error occurs while retrieving the
     *                                      column count.
     */
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
            throw new ExceptionHub.ExcelException("Error occurred while getting the column count for the sheet", ex);
        }
    }

    /**
     * Adds a hyperlink to a specified test case in the given sheet.
     * <p>
     * This method searches for a test case name in the specified sheet and adds a hyperlink
     * to a given column, associating it with a screenshot URL and message.
     * </p>
     *
     * @param sheetName        The name of the sheet where the hyperlink is to be added.
     * @param screenShotColName The name of the column to which the hyperlink will be added.
     * @param testCaseName      The test case name to search for.
     * @param index             An offset index to adjust the row position.
     * @param url               The URL to be added as the hyperlink.
     * @param message           The message to be displayed for the hyperlink.
     * @return {@code true} if the hyperlink was successfully added, {@code false} otherwise.
     * @throws ExceptionHub.ExcelException If an error occurs while adding the hyperlink.
     */
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
            throw new ExceptionHub.ExcelException("Error occurred while adding hyperlink for the test case", ex);
        }
    }

    /**
     * Finds the row number of a cell in the specified column with a given value.
     * <p>
     * This method searches for a cell with the specified value in the given column of
     * the specified sheet. If a match is found, it returns the row number. If the sheet
     * does not exist or no match is found, it returns -1.
     * </p>
     *
     * @param sheetName The name of the sheet to search in.
     * @param colName   The name of the column to search for the cell value.
     * @param cellValue The value to search for in the column.
     * @return The row number of the cell with the specified value, or -1 if not found.
     * @throws ExceptionHub.ExcelException If an error occurs while searching for the
     *                                      cell value.
     */
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
            throw new ExceptionHub.ExcelException("Error occurred while searching for cell value in the excel sheet", ex);
        }
    }

}

