package com.demo.selenium.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataReader {
    private static final String FILE_PATH = "src/test/resources/login_credentials.xlsx";

    public static List<LoginData> readValidLogins() throws IOException {
        return readRowsByScenario("VALID");
    }

    public static List<LoginData> readInvalidLogins() throws IOException {
        return readRowsByScenario("INVALID", "MISSING_USERNAME");
    }

    private static List<LoginData> readRowsByScenario(String... allowedScenarios) throws IOException {
        List<LoginData> loginDataList = new ArrayList<>();
        Path filePath = Paths.get(FILE_PATH);
        String baseUrl = ConfigReader.getBaseUrl();

        try (InputStream inputStream = Files.newInputStream(filePath); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }

                String username = getCellValue(row.getCell(0));
                String password = getCellValue(row.getCell(1));
                String scenario = getCellValue(row.getCell(2));
                String expectedMessage = getCellValue(row.getCell(3));

                if (scenario == null || scenario.isBlank()) {
                    continue;
                }

                boolean isAllowed = false;
                for (String allowedScenario : allowedScenarios) {
                    if (allowedScenario.equalsIgnoreCase(scenario.trim())) {
                        isAllowed = true;
                        break;
                    }
                }

                if (isAllowed) {
                    loginDataList.add(new LoginData(baseUrl, username, password, scenario, expectedMessage));
                }
            }
        }

        return loginDataList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> cell.toString().trim();
        };
    }
}
