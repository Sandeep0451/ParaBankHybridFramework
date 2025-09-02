package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class GetData {

    public static String filePath = System.getProperty("user.dir")+ "/src/test/resource/ParaBankTestData.xlsx";

    public static List<Map<String, String>> getDataForTestCase(
            String sheetName,
            String testCaseName,
            String transaction) {

        List<Map<String, String>> resultList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0); // header row
            int totalColumns = headerRow.getLastCellNum();
            int totalRows = sheet.getLastRowNum();

            // find column indexes dynamically
            int testCaseColIndex = -1;
            int transactionColIndex = -1;

            for (int col = 0; col < totalColumns; col++) {
                String headerName = headerRow.getCell(col).getStringCellValue().trim();
                if (headerName.equalsIgnoreCase("TestCaseID")) {
                    testCaseColIndex = col;
                } else if (headerName.equalsIgnoreCase("Transaction")) {
                    transactionColIndex = col;
                }
            }

            if (testCaseColIndex == -1 || transactionColIndex == -1) {
                throw new RuntimeException("Required columns 'testCaseName' or 'transaction' not found in sheet: " + sheetName);
            }

            // loop through rows to find all matches
            for (int i = 1; i <= totalRows; i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Cell testCaseCell = currentRow.getCell(testCaseColIndex);
                Cell transactionCell = currentRow.getCell(transactionColIndex);

                if (testCaseCell == null || transactionCell == null) continue;

                String tcName = testCaseCell.getStringCellValue().trim();
                String transValue = transactionCell.getStringCellValue().trim();

                if (tcName.equalsIgnoreCase(testCaseName) && transValue.equalsIgnoreCase(transaction)) {
                    Map<String, String> dataMap = new LinkedHashMap<>();

                    // extract all column data for this row
                    for (int j = 0; j < totalColumns; j++) {
                        String key = headerRow.getCell(j).getStringCellValue().trim();
                        Cell valueCell = currentRow.getCell(j);
                        String value = (valueCell == null) ? "" : valueCell.toString().trim();
                        dataMap.put(key, value);
                    }

                    resultList.add(dataMap);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static String generateNumber(int digits) {
        if (digits <= 0) {
            throw new IllegalArgumentException("Digits must be greater than 0");
        }
        SecureRandom random = new SecureRandom();
        int max = (int) Math.pow(10, digits);
        int number = random.nextInt(max);
        return String.format("%0" + digits + "d", number);
    }



    public static List<String> getRegressionIDs() {
        List<String> regressionIds = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Get the sheet "Regressions"
            Sheet sheet = workbook.getSheet("Regressions");
            if (sheet == null) {
                throw new RuntimeException("Sheet 'Regressions' not found in file: " + filePath);
            }

            // Get header row (first row)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row missing in sheet 'Regressions'");
            }

            // Find column index of "RegressionID"
            int regressionIdColIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().trim().equalsIgnoreCase("RegressionID")) {
                    regressionIdColIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (regressionIdColIndex == -1) {
                throw new RuntimeException("Column 'RegressionID' not found in sheet 'Regressions'");
            }

            // Iterate over rows and get values from RegressionID column
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(regressionIdColIndex);

                if (cell != null) {
                    String value = cell.toString().trim();
                    if (!value.isEmpty()) {
                        regressionIds.add(value);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return regressionIds;
    }

    public static List<String> readRowData( String sheetName, int rowNumber) {
        List<String> rowData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " not found in " + filePath);
            }

            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                throw new RuntimeException("Row " + rowNumber + " not found in sheet " + sheetName);
            }

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowData.add(getCellValueAsString(cell));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }



}
