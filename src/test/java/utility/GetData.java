package utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    Map<String, String> dataMap = new HashMap<>();

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

}
