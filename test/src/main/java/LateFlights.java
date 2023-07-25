import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LateFlights {

    public Map<String, Integer> getTotalMinutesLateByAirline() throws IOException {
        // Read in Chronic_Delay
        Workbook workbook = new XSSFWorkbook(new FileInputStream("src/main/resources/04_Apr_2023_Chronic_Delay.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);

        // Indices for the columns
        int carrierColumn = 1;
        int minutesLateColumn = 10;

        Map<String, Integer> totalMinutesLateByAirline = new HashMap<>();

        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String carrier = row.getCell(carrierColumn).getStringCellValue();
            int minutesLate = (int) row.getCell(minutesLateColumn).getNumericCellValue();

            totalMinutesLateByAirline.put(carrier, totalMinutesLateByAirline.getOrDefault(carrier, 0) + minutesLate);
        }

        workbook.close();
        // This was added as a check for NullPointerExceptions
        System.out.println("Total minutes late by airline: " + totalMinutesLateByAirline);
        return totalMinutesLateByAirline;
    }

}
